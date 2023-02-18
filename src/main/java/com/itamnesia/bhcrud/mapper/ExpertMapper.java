package com.itamnesia.bhcrud.mapper;

import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.dto.expert.ExpertDto;
import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.model.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = Role.class)
public interface ExpertMapper {

    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "role", expression = "java(Role.UNCONFIRMED)")
    Expert toModel(SignUpDto signUpDto);


    ExpertDto toDto(Expert expert);

    List<ExpertDto> toDto(List<Expert> experts);
}
