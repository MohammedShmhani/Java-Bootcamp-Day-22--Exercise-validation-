package com.example.eventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class Event {

    @NotBlank(message = "ID cannot be blank")
    @Size(min = 3, message = "ID must be more than 2 characters")
    private String id;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 16, message = "Description must be more than 15 characters")
    private String description;

    @NotNull(message = "Capacity is required")
    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;


}
