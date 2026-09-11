package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.Popup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PopupRepository extends JpaRepository<Popup, Long>, PopupRepositoryCustom {
    Page<Popup> findAllByDelYn(String delYn, Pageable pageable);

    @Query("""
        SELECT p
        FROM Popup p
        WHERE p.puStartDt <= CURRENT_TIMESTAMP
          AND p.puEndDt >= CURRENT_TIMESTAMP
          AND p.delYn = 'N'
    """)
    List<Popup> findAllActiveList();
}
