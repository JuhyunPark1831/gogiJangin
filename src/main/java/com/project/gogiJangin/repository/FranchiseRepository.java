package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.Franchise;
import com.project.gogiJangin.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Long>, FranchiseRepositoryCustom {
    Page<Franchise> findAllByDelYn(String delYn, Pageable pageable);
}
