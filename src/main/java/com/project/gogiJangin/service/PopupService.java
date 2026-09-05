package com.project.gogiJangin.service;

import com.project.gogiJangin.dto.PageResponse;
import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.dto.popup.PopupResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PopupService {
    Long addPopup(PopupRequestDto requestDto);
    PopupResponseDto getPopupDetail(Long puId);
    PageResponse<PopupResponseDto> getPopupList(PopupRequestDto requestDto, Pageable pageable);
    Long updatePopup(PopupRequestDto requestDto);
    void deletePopup(Long puId);

    List<PopupResponseDto> getActivePopupList();
}
