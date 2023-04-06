package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccion_id", nullable = false)
    private Long transactionId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "description", nullable = true)
    private String description;
    @ManyToOne
    @JoinColumn(name = "acoount_id")
    private AccountEntity accountEntity;

    @CreationTimestamp
    @Column(name = "creation_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

}
