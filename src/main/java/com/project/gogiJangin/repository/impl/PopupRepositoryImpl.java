package com.project.gogiJangin.repository.impl;

import com.project.gogiJangin.dto.popup.PopupRequestDto;
import com.project.gogiJangin.entity.Popup;
import com.project.gogiJangin.repository.PopupRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.gogiJangin.entity.QPopup.popup;

@Repository
@RequiredArgsConstructor
public class PopupRepositoryImpl implements PopupRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Popup> search(
            PopupRequestDto requestDto,
            Pageable pageable
    ) {

        List<Popup> content = queryFactory
                .selectFrom(popup)
                .where(
                        puTitleContains(requestDto.getPuTitle()),
                        puStartDtGoe(requestDto.getPuStartDt()),
                        puEndDtLoe(requestDto.getPuEndDt()),
                        statusEq(requestDto.getStatus(), LocalDateTime.now()),
                        popup.delYn.eq("N")
                )
                .orderBy(popup.regDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(popup.count())
                .from(popup)
                .where(
                        puTitleContains(requestDto.getPuTitle()),
                        puStartDtGoe(requestDto.getPuStartDt()),
                        puEndDtLoe(requestDto.getPuEndDt()),
                        statusEq(requestDto.getStatus(), LocalDateTime.now()),
                        popup.delYn.eq("N")
                )
                .fetchOne();

        return new PageImpl<>(
                content,
                pageable,
                total != null ? total : 0L
        );
    }

    private BooleanExpression puTitleContains(String puTitle) {
        return puTitle != null && !puTitle.isBlank()
                ? popup.puTitle.contains(puTitle)
                : null;
    }

    private BooleanExpression puStartDtGoe(LocalDateTime puStartDt) {
        return puStartDt != null
                ? popup.puStartDt.goe(puStartDt)
                : null;
    }

    private BooleanExpression puEndDtLoe(LocalDateTime puEndDt) {
        return puEndDt != null
                ? popup.puEndDt.loe(puEndDt)
                : null;
    }

    private BooleanExpression statusEq(String status, LocalDateTime now) {

        if (status == null || status.isBlank()) {
            return null;
        }

        return switch (status) {

            // 예약
            case "01" -> popup.puStartDt.gt(now);

            // 노출중
            case "02" -> popup.puStartDt.loe(now)
                    .and(popup.puEndDt.goe(now));

            // 종료
            case "03" -> popup.puEndDt.lt(now);

            default -> null;
        };
    }
}
