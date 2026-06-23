package com.project.gogiJangin.repository;

import com.project.gogiJangin.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {

    @Query("SELECT COALESCE(MAX(af.afGroupId) + 1, 1) FROM AttachFile af")
    Long findMaxAfGroupId();
}
