package com.project.gogiJangin.repository.impl;

import com.project.gogiJangin.dto.review.ReviewRequestDto;
import com.project.gogiJangin.entity.Review;
import com.project.gogiJangin.repository.ReviewRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.gogiJangin.entity.QReview.review;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Review> search(
            ReviewRequestDto requestDto,
            Pageable pageable
    ) {

        List<Review> content = queryFactory
                .selectFrom(review)
                .where(
                        rvTitleContains(requestDto.getRvTitle()),
                        rvRegDtContains(requestDto.getRvStartDt(), requestDto.getRvEndDt()),
                        rvStatusEq(requestDto.getRvStatus()),
                        review.delYn.eq("N")
                )
                .orderBy(review.regDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(review.count())
                .from(review)
                .where(
                        rvTitleContains(requestDto.getRvTitle()),
                        rvRegDtContains(requestDto.getRvStartDt(), requestDto.getRvEndDt()),
                        rvStatusEq(requestDto.getRvStatus()),
                        review.delYn.eq("N")
                )
                .fetchOne();

        return new PageImpl<>(
                content,
                pageable,
                total != null ? total : 0L
        );
    }

    private BooleanExpression rvTitleContains(String rvTitle) {
        return rvTitle != null && !rvTitle.isBlank()
                ? review.rvTitle.contains(rvTitle)
                : null;
    }

    private BooleanExpression rvRegDtContains(LocalDateTime rvStartDt, LocalDateTime rvEndDt) {
        return rvStartDt != null
                ? review.regDt.between(rvStartDt, rvEndDt)
                : null;
    }

    private BooleanExpression rvStatusEq(String rvStatus) {
        return rvStatus != null && !rvStatus.isBlank()
                    ? review.rvStatus.eq(rvStatus)
                    : null;
    }
}
