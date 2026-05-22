package com.rewards.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * DTO for API error responses.
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;

    private int status;

    private String error;

    private String message;
}