package com.undefiend.hospitalManagement.dto;

import com.undefiend.hospitalManagement.entity.type.BloodGroupType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BloodGroupCountResponseEntity {
    private BloodGroupType bloodGroupType;
    private Long count;
}
