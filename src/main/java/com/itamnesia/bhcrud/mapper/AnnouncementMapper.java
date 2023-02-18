package com.itamnesia.bhcrud.mapper;

import com.itamnesia.bhcrud.dto.announcement.AnnouncementClientResponse;
import com.itamnesia.bhcrud.dto.announcement.AnnouncementDto;
import com.itamnesia.bhcrud.model.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface AnnouncementMapper {

    @Mapping(target = "announcementId", source = "id")
    @Mapping(target = "createdDate", source = "created")
    @Mapping(target = "id", ignore = true)
    Announcement toModel(AnnouncementClientResponse.AnnouncementClientDto announcementClientDto);

    List<Announcement> toModel(List<AnnouncementClientResponse.AnnouncementClientDto> announcementClientDto);

    @Mapping(target = "reportId", source = "announcementReport.id")
    AnnouncementDto toDto(Announcement announcement);

    List<AnnouncementDto> toDto(List<Announcement> announcement);
}
