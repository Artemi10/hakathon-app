package com.itamnesia.bhcrud.mapper;

import com.itamnesia.bhcrud.dto.auth.SignUpDto;
import com.itamnesia.bhcrud.model.user.Role;
import com.itamnesia.bhcrud.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = Role.class)
public interface UserMapper {

    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "middleName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "role", expression = "java(Role.UNCONFIRMED)")
    User toModel(SignUpDto signUpDto);
}
