package com.alkemy.wallet.repository;

import com.alkemy.wallet.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface UserModelRepository extends JpaRepository<UserEntity, Long> {

}
