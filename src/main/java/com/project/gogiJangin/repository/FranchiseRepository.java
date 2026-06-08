package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long> {
}
