package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementClientResponse;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.dto.risk.RiskDto;
import com.itamnesia.bhcrud.exception.EntityNotFoundException;
import com.itamnesia.bhcrud.mapper.AnnouncementMapper;
import com.itamnesia.bhcrud.mapper.AnnouncementReportMapper;
import com.itamnesia.bhcrud.mapper.RiskMapper;
import com.itamnesia.bhcrud.model.Announcement;
import com.itamnesia.bhcrud.model.AnnouncementReport;
import com.itamnesia.bhcrud.model.Risk;
import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.repository.AnnouncementReportRepository;
import com.itamnesia.bhcrud.repository.AnnouncementRepository;
import com.itamnesia.bhcrud.repository.ExpertRepository;
import com.itamnesia.bhcrud.repository.RiskRepository;
import com.itamnesia.bhcrud.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final RiskRepository riskRepository;
    private final ExpertRepository expertRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementReportRepository announcementReportRepository;
    private final AnnouncementMapper announcementMapper;
    private final AnnouncementReportMapper announcementReportMapper;
    private final RiskMapper riskMapper;
    private final RestTemplate restTemplate;

    @Value("${api.service.url}")
    private String serviceUrl;

    @Override
    public List<AnnouncementDto> getLatestAnnouncements() {
        AnnouncementClientResponse response = restTemplate.getForObject(serviceUrl + "&limit=5", AnnouncementClientResponse.class);
        List<Announcement> announcements = announcementMapper.toModel(response.getData()).stream()
                .peek(it -> it.setText(it.getText().length() > 1024 ? it.getText().substring(0, 1024) : it.getText()))
                .toList();
        return announcementMapper.toDto(announcements);
    }

    @Override
    public AnnouncementDto getExpertAnnouncement(UUID expertId) {
        AnnouncementClientResponse response = restTemplate.getForObject(serviceUrl + "&limit=1", AnnouncementClientResponse.class);

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(EntityNotFoundException::expertNotFoundException);
        Announcement announcement = announcementMapper.toModel(response.getData()).stream()
                .peek(it -> it.setText(it.getText().length() > 1024 ? it.getText().substring(0, 1024) : it.getText()))
                .findFirst()
                .orElse(null);

        if (expert.getLatestAnnouncement() != null) {
            Announcement oldAnnouncement = expert.getLatestAnnouncement();
            oldAnnouncement.setExpert(null);
            announcementRepository.save(oldAnnouncement);
        }

        announcement.setExpert(expert);
        announcementRepository.save(announcement);

        return announcementMapper.toDto(announcement);
    }

    @Override
    public AnnouncementDto getCurrentExpertAnnouncement(UUID expertId) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(EntityNotFoundException::expertNotFoundException);
        Announcement announcement = expert.getLatestAnnouncement();
        return announcementMapper.toDto(announcement);
    }

    @Transactional
    @Override
    public AnnouncementReportDto createReport(UUID expertId, List<RiskDto> risksDto) {
        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(EntityNotFoundException::expertNotFoundException);
        Announcement announcement = expert.getLatestAnnouncement();

        List<Risk> risks = riskMapper.toModel(risksDto);

        AnnouncementReport report = AnnouncementReport.builder()
                .announcement(announcement)
                .expert(expert)
                .risks(risks)
                .ratings(new ArrayList<>())
                .build();
        AnnouncementReport savedReport = announcementReportRepository.save(report);
        risks.forEach(it -> {
            it.setAnnouncementReport(savedReport);
            riskRepository.save(it);
        });
        return announcementReportMapper.toDto(report);
    }
}
