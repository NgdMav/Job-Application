package com.mav.jobapplication.web;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        String description,
        LocalDateTime errorTime
) {
}
