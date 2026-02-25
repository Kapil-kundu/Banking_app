package com.kapil.digitalbank.Banking.App.Entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    @Column(unique = true,  length = 10)
    private String transaction_id;

    private String senderAcc;

    private String rceiverAcc;

    private Double amount;

    private String transactionType; // deposit, withdrawl , Transfer, payment, refund, interest credit, charges/fees

    private String transactionStatus; // success, failed or pending

    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fkTransactionAccount")
    )
    private BankAcc bankAcc;
}
