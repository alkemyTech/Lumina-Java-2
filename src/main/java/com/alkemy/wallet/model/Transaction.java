package com.alkemy.wallet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaccionId", nullable = false)
    private Long transactionId;

    @Column(name = "Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "description", nullable = true)
    private String description;
    @ManyToOne
    @JoinColumn(name = "acoountId")
    private Account account;

    @Column(name = "creationDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate transactionDate;

}
