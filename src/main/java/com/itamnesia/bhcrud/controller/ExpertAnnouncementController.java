package com.itamnesia.bhcrud.controller;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.risk.RiskDto;
import com.itamnesia.bhcrud.security.details.UserPrincipal;
import com.itamnesia.bhcrud.service.AnnouncementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expert/announcements")
public class ExpertAnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping
    @ApiOperation("Get expert announcement")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Expert announcements", response = AnnouncementDto.class)
    })
    public AnnouncementDto getExpertAnnouncement(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return announcementService.getExpertAnnouncement(userPrincipal.id());
    }

    @GetMapping("/current")
    @ApiOperation("Get current expert announcement")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Expert announcements", response = AnnouncementDto.class)
    })
    public AnnouncementDto getCurrentExpertAnnouncement(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return announcementService.getCurrentExpertAnnouncement(userPrincipal.id());
    }

    @PostMapping
    @ApiOperation("Create announcement report")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Announcement report", response = AnnouncementReportDto.class)
    })
    public AnnouncementReportDto createReport(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody List<RiskDto> reportDto) {
        return announcementService.createReport(userPrincipal.id(), reportDto);
    }

}
