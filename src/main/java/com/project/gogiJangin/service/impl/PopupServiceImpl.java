package com.project.gogiJangin.service.impl;

import com.project.gogiJangin.common.exception.CustomException;
import com.project.gogiJangin.common.exception.ErrorCode;
import com.project.gogiJangin.common.module.FileHandler;
import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.dto.popup.PopupResponseDto;
import com.project.gogiJangin.entity.Popup;
import com.project.gogiJangin.repository.AttachFileRepository;
import com.project.gogiJangin.repository.PopupRepository;
import com.project.gogiJangin.service.PopupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PopupServiceImpl implements PopupService {

    @Value("${file.upload-path}")
    private String path;

    private final PopupRepository popupRepository;
    private final AttachFileRepository attachFileRepository;
    private final FileHandler fileHandler;

    // 팝업 생성
    @Override
    @Transactional
    public Long addPopup(PopupRequestDto requestDto) {

        List<MultipartFile> list = List.of(requestDto.getPuImage());

        return popupRepository.save(Popup.builder()
                .puTitle(requestDto.getPuTitle())
                .puAfGroupId(fileHandler.saveFiles(list, path + File.separator + "popup"))
                .puStartDt(requestDto.getPuStartDt())
                .puEndDt(requestDto.getPuEndDt())
                .build()).getPuId();
    }

    // 팝업 단건 조회
    @Override
    public PopupResponseDto getPopupDetail(Long puId) {

        Popup popup = popupRepository.findById(puId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POPUP));

        Long puAfId = attachFileRepository.findAfIdByAfGroupId(popup.getPuAfGroupId());

        return PopupResponseDto.builder()
                .popup(popup)
                .puAfId(puAfId)
                .build();
    }

    // 팝업 목록 조회
    @Override
    public PageResponse<PopupResponseDto> getPopupList(PopupRequestDto requestDto, Pageable pageable) {

        Pageable sortedPageable = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "regDt")
        );

        Page<Popup> popupPage;

        if (requestDto != null) {
            popupPage = popupRepository.search(requestDto, sortedPageable);
        } else {
            popupPage = popupRepository.findAllByDelYn("N", sortedPageable);
        }

        List<PopupResponseDto> content = popupPage.getContent()
                .stream()
                .map(popup -> PopupResponseDto.builder()
                        .popup(popup)
                        .puAfId(attachFileRepository.findAfIdByAfGroupId(popup.getPuAfGroupId()))
                        .build()) // or mapper 사용
                .toList();

        return PageResponse.<PopupResponseDto>builder()
                .content(content)
                .page(popupPage.getNumber())
                .size(popupPage.getSize())
                .totalElements(popupPage.getTotalElements())
                .totalPages(popupPage.getTotalPages())
                .first(popupPage.isFirst())
                .last(popupPage.isLast())
                .build();
    }

    // 팝업 수정
    @Override
    @Transactional
    public Long updatePopup(PopupRequestDto requestDto) {

        if (requestDto.getPuId() != null) {
            Popup popup = popupRepository.findById(requestDto.getPuId()).orElseThrow(() ->
                    new CustomException(ErrorCode.NOT_FOUND_POPUP));

            if (requestDto.getPuImage() != null) {
                fileHandler.deleteAllFileByAfGroupId(popup.getPuAfGroupId());
                popup.update(requestDto, fileHandler.saveFiles(List.of(requestDto.getPuImage()), path + File.separator + "popup"));
            } else {
                popup.update(requestDto, popup.getPuAfGroupId());
            }

            return popup.getPuId();
        } else {
            throw new CustomException(ErrorCode.NOT_FOUND_POPUP);
        }
    }

    // 팝업 삭제
    @Override
    @Transactional
    public void deletePopup(Long puId) {

        Popup popup = popupRepository.findById(puId).orElseThrow(() ->
                new CustomException(ErrorCode.NOT_FOUND_POPUP));

        fileHandler.deleteAllFileByAfGroupId(popup.getPuAfGroupId());
        popup.softDelete();
    }

    @Override
    public List<PopupResponseDto> getActivePopupList() {
        List<Popup> popupList = popupRepository.findAllActiveList();

        return popupList.stream()
                .map(popup -> PopupResponseDto.builder()
                        .popup(popup)
                        .puAfId(
                                attachFileRepository.findAfIdByAfGroupId(
                                        popup.getPuAfGroupId()
                                )
                        )
                        .build()
                )
                .toList();
    }
}
