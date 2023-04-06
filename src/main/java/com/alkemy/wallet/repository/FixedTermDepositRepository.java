package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.FixedTermDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FixedTermDepositRepository extends JpaRepository<FixedTermDeposit, Long> {

    @Query(value = "SELECT * FROM fixed_term_deposits WHERE accountId = :accountId", nativeQuery = true)
    List<FixedTermDeposit> getFixedTermDepositDTObyAccountId(@Param(value = "accountId") Long accountId);
}
