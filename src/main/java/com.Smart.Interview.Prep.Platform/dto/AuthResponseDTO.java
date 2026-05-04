package com.Smart.Interview.Prep.Platform.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String token;
    private String msg;
}
