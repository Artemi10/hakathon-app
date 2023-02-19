package com.itamnesia.bhcrud.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private String firstName;
    private String telegramLink;
    private double trustRating;
}
