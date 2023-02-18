package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.expert.ExpertDto;
import com.itamnesia.bhcrud.exception.EntityNotFoundException;
import com.itamnesia.bhcrud.mapper.AnnouncementReportMapper;
import com.itamnesia.bhcrud.mapper.ExpertMapper;
import com.itamnesia.bhcrud.model.AnnouncementReport;
import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.repository.AnnouncementReportRepository;
import com.itamnesia.bhcrud.repository.ExpertRepository;
import com.itamnesia.bhcrud.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    private final AnnouncementReportMapper announcementReportMapper;
    private final AnnouncementReportRepository announcementReportRepository;
    private final ExpertRepository expertRepository;
    private final ExpertMapper expertMapper;

    @Override
    public List<AnnouncementReportDto> getExpertAnnouncement(UUID id) {
        List<AnnouncementReport> reports = announcementReportRepository.findByExpertId(id);
        return announcementReportMapper.toDto(reports);
    }

    @Override
    public List<ExpertDto> getExpertList() {
        List<Expert> experts = expertRepository.findAll();
        return expertMapper.toDto(experts);
    }

    @Override
    public ExpertDto getExpertInfo(UUID id) {
        Expert expert = expertRepository.findById(id)
                .orElseThrow(EntityNotFoundException::expertNotFoundException);
        return expertMapper.toDto(expert);
    }

}
