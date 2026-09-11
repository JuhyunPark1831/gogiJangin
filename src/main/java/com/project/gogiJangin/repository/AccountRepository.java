package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.Account;
import com.project.gogiJangin.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAcLoginId(String acLoginId);
}
