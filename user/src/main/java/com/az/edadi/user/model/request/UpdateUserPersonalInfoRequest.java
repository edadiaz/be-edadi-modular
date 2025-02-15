package com.az.edadi.user.model.request;

import com.az.edadi.dal.types.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserPersonalInfoRequest {
    private LocalDate birthDate;
    private Gender gender;
}
