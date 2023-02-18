package com.itamnesia.bhcrud.controller;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.service.AnnouncementService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/latest")
    @ApiOperation("Get latest announcements")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pageable announcements", response = AnnouncementDto.class)
    })
    public List<AnnouncementDto> getLatestAnnouncements() {
        return announcementService.getLatestAnnouncements();
    }

}
