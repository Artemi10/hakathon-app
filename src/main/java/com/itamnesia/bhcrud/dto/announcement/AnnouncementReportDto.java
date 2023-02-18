package com.itamnesia.bhcrud.dto.announcement;

import com.itamnesia.bhcrud.dto.expert.ExpertDto;
import com.itamnesia.bhcrud.dto.risk.RiskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementReportDto {
    private UUID id;
    private List<Integer> ratings = new ArrayList<>();
    private List<RiskDto> risks = new ArrayList<>();
    private AnnouncementDto announcement;
    private ExpertDto expert;
}
