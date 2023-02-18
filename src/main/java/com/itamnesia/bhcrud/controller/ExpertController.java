package com.itamnesia.bhcrud.controller;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.expert.ExpertDto;
import com.itamnesia.bhcrud.security.details.UserPrincipal;
import com.itamnesia.bhcrud.service.ExpertService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/experts")
public class ExpertController {

    private final ExpertService expertService;

    @GetMapping("/info")
    @ApiOperation("Get expert info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Expert dto", response = ExpertDto.class)
    })
    public ExpertDto getExpertInfo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return expertService.getExpertInfo(userPrincipal.id());
    }

    @GetMapping("/announcements")
    @ApiOperation("Get expert info")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Expert announcement dto", response = AnnouncementReportDto.class)
    })
    public List<AnnouncementReportDto> getExpertAnnouncement(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return expertService.getExpertAnnouncement(userPrincipal.id());
    }

    @GetMapping
    @ApiOperation("Get all experts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All experts dto", response = ExpertDto.class)
    })
    public List<ExpertDto> getAllExpert() {
        return expertService.getExpertList();
    }
}
