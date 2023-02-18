package com.itamnesia.bhcrud.mapper;

import org.mapstruct.Mapper;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface DateMapper {

    default OffsetDateTime toDate(String date) {
        return OffsetDateTime.parse(date);
    }
}
