package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "SELECT * from accounts WHERE userId = :userId AND softDelete = false", nativeQuery = true)
    List<Account> accountsOfUser(@Param(value = "userId") Long userId);
}
