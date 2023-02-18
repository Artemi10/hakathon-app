package com.itamnesia.bhcrud.controller;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementRatingDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.service.AnnouncementReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcements/report")
public class AnnouncementReportController {

    private final AnnouncementReportService announcementReportService;

    @GetMapping
    @ApiOperation("Get approved announcements")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Announcements reports", response = AnnouncementReportDto.class)
    })
    public List<AnnouncementDto> getApprovedAnnouncements() {
        return announcementReportService.getApprovedAnnouncement();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get approved announcements")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Announcements reports", response = AnnouncementReportDto.class)
    })
    public AnnouncementReportDto getReport(@PathVariable UUID id) {
        return announcementReportService.getReport(id);
    }

    @PostMapping("/{id}")
    @ApiOperation("Add announcement reports rating")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok")
    })
    public void addRating(@PathVariable UUID id, @RequestBody AnnouncementRatingDto ratingDto) {
        announcementReportService.addRating(id, ratingDto);
    }
}
