package com.project.gogiJangin.repository.impl;

import com.project.gogiJangin.dto.franchise.FranchiseRequestDto;
import com.project.gogiJangin.entity.Franchise;
import com.project.gogiJangin.repository.FranchiseRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.gogiJangin.entity.QFranchise.franchise;

@Repository
@RequiredArgsConstructor
public class FranchiseRepositoryImpl implements FranchiseRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Franchise> search(
            FranchiseRequestDto requestDto,
            Pageable pageable
    ) {

        List<Franchise> content = queryFactory
                .selectFrom(franchise)
                .where(
                        frNameContains(requestDto.getFrName()),
                        frRegDtContains(requestDto.getFrStartDt(), requestDto.getFrEndDt()),
                        frStatusEq(requestDto.getFrStatus()),
                        franchise.delYn.eq("N")
                )
                .orderBy(franchise.regDt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(franchise.count())
                .from(franchise)
                .where(
                        frNameContains(requestDto.getFrName()),
                        frRegDtContains(requestDto.getFrStartDt(), requestDto.getFrEndDt()),
                        frStatusEq(requestDto.getFrStatus()),
                        franchise.delYn.eq("N")
                )
                .fetchOne();

        return new PageImpl<>(
                content,
                pageable,
                total != null ? total : 0L
        );
    }

    private BooleanExpression frNameContains(String frName) {
        return frName != null && !frName.isBlank()
                ? franchise.frName.contains(frName)
                : null;
    }

    private BooleanExpression frRegDtContains(
            LocalDateTime frStartDt,
            LocalDateTime frEndDt) {

        return frStartDt != null && frEndDt != null
                ? franchise.regDt.between(frStartDt, frEndDt)
                : frStartDt != null
                ? franchise.regDt.goe(frStartDt)
                : frEndDt != null
                ? franchise.regDt.loe(frEndDt)
                : null;
    }

    private BooleanExpression frStatusEq(String frStatus) {
        return frStatus != null && !frStatus.isBlank()
                    ? franchise.frStatus.eq(frStatus)
                    : null;
    }
}
