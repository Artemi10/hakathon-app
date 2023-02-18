package com.itamnesia.bhcrud.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementClientResponse {

    private List<AnnouncementClientDto> data;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnnouncementClientDto {
        private Long id;
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
        private String created;
        private String region;
        private String city;
        private String material;
        private boolean isNew;
        private Long rooms;
        private String category;
        private String section;
    }
}
