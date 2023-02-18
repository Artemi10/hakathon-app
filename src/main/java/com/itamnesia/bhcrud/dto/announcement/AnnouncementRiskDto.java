package com.itamnesia.bhcrud.dto.announcement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementRiskDto {
    private List<Integer> ratings = new ArrayList<>();
    private AnnouncementDto announcement;
}
