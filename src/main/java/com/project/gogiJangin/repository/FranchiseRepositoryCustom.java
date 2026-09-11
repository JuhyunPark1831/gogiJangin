package com.project.gogiJangin.repository;

import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.entity.Franchise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FranchiseRepositoryCustom {
    Page<Franchise> search(
            FranchiseRequestDto requestDto,
            Pageable pageable
    );
}
