package com.itamnesia.bhcrud.repository;

import com.itamnesia.bhcrud.model.AnnouncementReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnouncementReportRepository extends JpaRepository<AnnouncementReport, UUID> {

    List<AnnouncementReport> findByExpertId(UUID expertId);
}
