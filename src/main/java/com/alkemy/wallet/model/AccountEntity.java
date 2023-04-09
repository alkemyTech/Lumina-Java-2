package com.alkemy.wallet.model;

import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
@SQLDelete(sql = "UPDATE accounts SET soft_delete=true WHERE account_id = ?")
@Where(clause = "soft_delete = false")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_id", nullable = false)
    private Long accountId;

    @Column(name="currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name="transaction_limit", nullable = false)
    private Double transactionLimit;

    @Column(name="balance", nullable = false)
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(name="creation_date")
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name="update_date")
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name="soft_delete")
    @Builder.Default
    private Boolean softDelete = false;

    @OneToMany(mappedBy = "accountEntity")
    private List<TransactionEntity> transactionEntityList = new ArrayList ();

    @OneToMany(mappedBy = "accountEntity")
    private List<FixedTermDepositEntity> fixedTermDepositEntityList = new ArrayList<>();
}
