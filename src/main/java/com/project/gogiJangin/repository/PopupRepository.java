package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.Popup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopupRepository extends JpaRepository<Popup, Long>, PopupRepositoryCustom {
}
