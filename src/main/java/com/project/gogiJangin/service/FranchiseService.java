package com.project.gogiJangin.service;

import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import org.springframework.data.domain.Pageable;

public interface FranchiseService {

    Long addFranchise(FranchiseRequestDto requestDto);
    FranchiseResponseDto getFranchiseDetail(Long frId);
    PageResponse<FranchiseResponseDto> getFranchiseList(Pageable pageable);
    Long updateFranchise(FranchiseRequestDto requestDto);
    void deleteFranchise(Long frId);
}
