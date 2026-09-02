package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {

    @Query("SELECT COALESCE(MAX(af.afGroupId) + 1, 1) FROM AttachFile af")
    Long findMaxAfGroupId();
    @Query("SELECT MIN(af.afId) FROM AttachFile af WHERE af.afGroupId = :afGroupId")
    Long findAfIdByAfGroupId(@Param("afGroupId") Long afGroupId);
    List<AttachFile> findAllByAfGroupId(Long afGroupId);
}
