package com.project.gogiJangin.entity;

import com.project.gogiJangin.dto.popup.PopupRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_POPUP")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Popup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PU_ID")
    private Long puId;

    @Column(name = "PU_TITME")
    private String puTitle;

    @Column(name = "PU_AF_GROUP_ID")
    private Long puAfGroupId;

    @Column(name = "PU_START_DT")
    private LocalDateTime puStartDt;

    @Column(name = "PU_END_DT")
    private LocalDateTime puEndDt;

    @Builder
    public Popup(String puTitle,
                 Long puAfGroupId,
                 LocalDateTime puStartDt,
                 LocalDateTime puEndDt) {
        this.puTitle = puTitle;
        this.puAfGroupId = puAfGroupId;
        this.puStartDt = puStartDt;
        this.puEndDt = puEndDt;
    }

    public void update(PopupRequestDto requestDto, Long puAfGroupId) {
        this.puTitle = requestDto.getPuTitle();
        this.puAfGroupId = puAfGroupId;
        this.puStartDt = requestDto.getPuStartDt();
        this.puEndDt = requestDto.getPuEndDt();
    }
}
