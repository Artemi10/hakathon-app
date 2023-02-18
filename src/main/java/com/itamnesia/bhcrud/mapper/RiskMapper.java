package com.itamnesia.bhcrud.mapper;

import com.itamnesia.bhcrud.dto.risk.RiskDto;
import com.itamnesia.bhcrud.model.Risk;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RiskMapper {

    Risk toModel(RiskDto riskDto);

    List<Risk> toModel(List<RiskDto> risksDto);

    RiskDto toDto(Risk risk);

    List<RiskDto> toDto(List<Risk> risks);
}
