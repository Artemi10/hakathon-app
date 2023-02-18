package com.itamnesia.bhcrud.mapper;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementReportDto;
import com.itamnesia.bhcrud.model.AnnouncementReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnnouncementReportMapper {

    AnnouncementReportDto toDto(AnnouncementReport announcementReport);

    List<AnnouncementReportDto> toDto(List<AnnouncementReport> announcementReports);
}
