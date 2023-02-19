package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.risk.RiskDto;

import java.util.List;
import java.util.UUID;

public interface AnnouncementService {

    List<AnnouncementDto> getLatestAnnouncements();

    AnnouncementDto getExpertAnnouncement(UUID expertId);

    AnnouncementDto getCurrentExpertAnnouncement(UUID expertId);

    AnnouncementReportDto createReport(UUID expertId, List<RiskDto> reportDto);
}
