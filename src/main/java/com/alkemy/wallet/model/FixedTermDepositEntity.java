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
@SQLDelete(sql = "UPDATE fixed_term_deposits SET softDelete=true WHERE fixed_term_depositsId = ?")
@Where(clause = "softDelete = false")
public class FixedTermDepositEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fixed_term_depositsId", nullable = false)
    private Long fixedTermDepositId;
    @Column(name="amount", nullable = false)
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private AccountEntity account;
    @Column(name="interest", nullable = false)
    private Double interest;
    @Column(name="creationDate")
    private LocalDate creationDate;
    @Column(name="closingDate")
    private LocalDate closingDate;
    @Column(name="softDelete")
    private Boolean softDelete = false;
}
