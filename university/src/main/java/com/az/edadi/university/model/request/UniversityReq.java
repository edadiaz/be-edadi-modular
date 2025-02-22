package com.az.edadi.university.model.request;


import com.az.edadi.dal.constants.UniversityType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UniversityReq {
    @NotBlank(message = "Field cannot be null")
    @Size(max = 10,min = 2 ,message = "Symbol count must be minimum 2 maximum 10" )
    private String abbrAz;

    @NotBlank(message = "Field cannot be null")
    @Size(max = 10,min = 2 ,message = "Symbol count must be minimum 2 maximum 10" )
    private String abbrEn;

    @NotBlank(message = "Field cannot be null")
    @Size(max = 60,min = 5 ,message = "Symbol count must be minimum 2 maximum 10" )
    private String nameAz;

    @NotBlank(message = "Field cannot be null")
    @Size(max = 60,min = 5 ,message = "Symbol count must be minimum 2 maximum 10" )
    private String nameEn;

    @NotBlank(message = "Field cannot be null")
    @Pattern(regexp = ".*\\.(png|jpg|jpeg)$", message = "photoUrl must be PNG, JPG or JPEG")
    private String photoUrl;

    @NotNull(message = "Field cannot be null")
    @Min(value = 1800, message = "foundedYear cannot be less than 1800")
    @Max(value = 2025, message = "foundedYear cannot be greater than 2025")
    private Integer foundedYear;

    @NotBlank(message = "webSiteUrl cannot be empty")
    @Pattern(regexp = "^(http|https)://.*$", message = "webSiteUrl must be in a valid URL format")
    private String webSiteUrl;

    @NotNull(message = "countOfStudent cannot be null")
    @Min(value = 1, message = "countOfStudent must be at least 1")
    private Integer countOfStudent;

    @NotBlank(message = "type cannot be empty")
    @Pattern(regexp = "^(COMMUNITY|PRIVATE|PUBLIC)$", message = "type can only be 'PUBLIC', 'PRIVATE','COMMUNITY")
    private String type;

    @NotBlank(message = "description cannot be empty")
    @Size(max = 500, message = "description can be a maximum of 500 characters")
    private String description;

    @NotBlank(message = "location cannot be empty")
    private String location;

}
