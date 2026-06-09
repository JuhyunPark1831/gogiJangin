package com.project.gogiJangin.service.impl;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.dto.franchise.FranchiseResponseDto;
import com.project.gogiJangin.entity.Franchise;
import com.project.gogiJangin.repository.FranchiseRepository;
import com.project.gogiJangin.repository.RegionRepository;
import com.project.gogiJangin.service.FranchiseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FranchiseServiceImpl implements FranchiseService {

    private final FranchiseRepository franchiseRepository;
    private final RegionRepository regionRepository;

    // 프랜차이즈 CREATE
    @Override
    public Long addFranchise(FranchiseRequestDto requestDto) {
        return franchiseRepository.save(Franchise.builder()
                        .frName(requestDto.getFrName())
                        .frContact(requestDto.getFrContact())
                        .frRegion(regionRepository.findById(requestDto.getFrRgId()).orElseThrow(() ->
                                new CustomException(ErrorCode.NOT_FOUND_REGION)))
                .build()).getFrId();
    }
    
    // 프랜차이즈 단건 조회
    @Override
    public FranchiseResponseDto getFranchiseDetail(Long frId) {
        return FranchiseResponseDto.builder()
                .fr(franchiseRepository.findById(frId).orElseThrow(() ->
                        new CustomException(ErrorCode.NOT_FOUND_FRANCHISE)))
                .build();
    }

    // 프랜차이즈 목록 조회
    @Override
    public PageResponse<FranchiseResponseDto> getFranchiseList(Pageable pageable) {

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "regDt")
        );

        Page<Franchise> franchisePage = franchiseRepository.findAll(sortedPageable);

        List<FranchiseResponseDto> content = franchisePage.getContent()
                .stream()
                .map(franchise -> FranchiseResponseDto.builder().fr(franchise).build()) // or mapper 사용
                .toList();

        return PageResponse.<FranchiseResponseDto>builder()
                .content(content)
                .page(franchisePage.getNumber())
                .size(franchisePage.getSize())
                .totalElements(franchisePage.getTotalElements())
                .totalPages(franchisePage.getTotalPages())
                .first(franchisePage.isFirst())
                .last(franchisePage.isLast())
                .build();
    }
}
