package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementRatingDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;

import java.util.List;
import java.util.UUID;

public interface AnnouncementReportService {

    void addRating(UUID id, AnnouncementRatingDto ratingDto);

    List<AnnouncementDto> getApprovedAnnouncement();

    AnnouncementReportDto getReport(UUID id);
}
