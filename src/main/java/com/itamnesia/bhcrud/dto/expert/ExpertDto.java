package com.itamnesia.bhcrud.dto.expert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    private String firstName;
    private double trustRating;
}
