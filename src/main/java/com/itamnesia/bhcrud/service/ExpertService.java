package com.itamnesia.bhcrud.service;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.user.ExpertDto;

import java.util.List;
import java.util.UUID;

public interface ExpertService {

    List<AnnouncementReportDto> getExpertAnnouncement(UUID id);

    List<ExpertDto> getExpertList();

    ExpertDto getExpertInfo(UUID id);

    ExpertDto addTelegramLink(UUID id, String link);
}
