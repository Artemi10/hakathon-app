package com.itamnesia.bhcrud.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementDto {
    private UUID id;
    private String type;
    private String title;
    private String address;
    private Long floor;
    private Long floors;
    private Float sq;
    private Long cost;
    private String text;
    private List<String> images;
    private String lat;
    private String lng;
    private String name;
    private String phone;
    private String url;
    private boolean isAgent;
    private String source;
    private OffsetDateTime createdDate;
    private String region;
    private String city;
    private String material;
    private boolean isNew;
    private Long rooms;
    private String category;
    private String section;
    private UUID reportId;
}
