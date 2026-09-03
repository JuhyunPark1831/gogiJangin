package com.project.gogiJangin.repository;

import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.entity.Popup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PopupRepositoryCustom {
    Page<Popup> search(
            PopupRequestDto requestDto,
            Pageable pageable
    );
}
