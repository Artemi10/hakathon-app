package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementRatingDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.exception.EntityNotFoundException;
import com.itamnesia.bhcrud.mapper.AnnouncementMapper;
import com.itamnesia.bhcrud.mapper.AnnouncementReportMapper;
import com.itamnesia.bhcrud.model.Announcement;
import com.itamnesia.bhcrud.model.AnnouncementReport;
import com.itamnesia.bhcrud.repository.AnnouncementReportRepository;
import com.itamnesia.bhcrud.service.AnnouncementReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnnouncementReportServiceImpl implements AnnouncementReportService {

    private final AnnouncementReportRepository announcementReportRepository;
    private final AnnouncementReportMapper announcementReportMapper;
    private final AnnouncementMapper announcementMapper;

    @Override
    public void addRating(UUID id, AnnouncementRatingDto ratingDto) {
        AnnouncementReport announcementReport = announcementReportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::announcementReportNotFoundException);
        announcementReport.getRatings().add(ratingDto.getRating());
        announcementReportRepository.save(announcementReport);
    }

    @Override
    public List<AnnouncementDto> getApprovedAnnouncement() {
        List<Announcement> announcements = announcementReportRepository.findAll().stream()
                .map(AnnouncementReport::getAnnouncement)
                .toList();
        return announcementMapper.toDto(announcements);
    }

    @Override
    public AnnouncementReportDto getReport(UUID id) {
        AnnouncementReport report = announcementReportRepository.findById(id)
                .orElseThrow(EntityNotFoundException::announcementReportNotFoundException);
        return announcementReportMapper.toDto(report);
    }
}
