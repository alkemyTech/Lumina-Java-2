package com.alkemy.wallet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fixed_term_deposits")
@SQLDelete(sql = "UPDATE fixed_term_deposits SET soft_delete=true WHERE fixed_term_deposits_id = ?")
@Where(clause = "soft_delete = false")
public class FixedTermDepositEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fixed_term_deposits_id", nullable = false)
    private Long fixedTermDepositId;
    @Column(name="amount", nullable = false)
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;
    @Column(name="interest", nullable = false)
    private Double interest;
    @Column(name="creation_date")
    private LocalDate creationDate;
    @Column(name="closing_date")
    private LocalDate closingDate;
    @Column(name="soft_delete")
    private Boolean softDelete = false;
}
