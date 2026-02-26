package com.kapil.digitalbank.Banking.App.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 10)
    private String accountNumber;

    @Column(nullable = false)
    private Double balance = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    // Many accounts → one user (OWNING SIDE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",   // better naming convention
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_account_user")
    )
    private AppUser appUser;

    // One account → many transactions
    @OneToMany(
            mappedBy = "bankAcc",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transactions> transactions;
}