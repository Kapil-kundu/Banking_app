package com.kapil.digitalbank.Banking.App.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="Account_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,  length = 10)
    private String accountNumber;

    private Double balance = 0.0;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(
            name = "userId",
            nullable = false,
            foreignKey = @ForeignKey(name = "fkAccountUser")
    )
    private User user;

    @OneToMany
    private List<Transactions> transactions;
}
