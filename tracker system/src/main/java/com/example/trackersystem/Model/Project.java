package com.example.trackersystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    @NotEmpty(message = "ID cannot be empty")
    @Size(min = 2, message = "ID must be more than 2 characters")
    private String id;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 8, message = "Title must be more than 8 characters")
    private String title;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 15, message = "Description must be more than 15 characters")
    private String description;

    @NotEmpty(message = "Status cannot be empty")
    @Pattern(
            regexp = "Not Started|In Progress|Completed",
            message = "Status must be either: Not Started, In Progress, or Completed"
    )
    private String status;

    @NotEmpty(message = "Company name cannot be empty")
    @Size(min = 6, message = "Company name must be more than 6 characters")
    private String companyName;
}
