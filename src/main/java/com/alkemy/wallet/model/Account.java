package com.alkemy.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
@SQLDelete(sql = "UPDATE accounts SET softDelete=true WHERE accountId = ?")
@Where(clause = "softDelete = false")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="accountId", nullable = false)
    private Long accountId;

    @Column(name="currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="transactionLimit", nullable = false)
    private Double transactionLimit;

    @Column(name="balance", nullable = false)
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;
    @Column(name="creationDate")
    private LocalDate creationDate;

    @Column(name="updateDate")
    private LocalDate updateDate;

    @Column(name="softDelete")
    private Boolean softDelete = false;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactionList = new ArrayList ();

    @OneToMany(mappedBy = "account")
    private List<FixedTermDeposit> fixedTermDepositList = new ArrayList<>();
}
