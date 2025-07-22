package com.example.trackersystem.Controller;

import com.example.trackersystem.Api.ApiResponse;
import com.example.trackersystem.Model.Project;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
@Validated


public class ProjectController {

    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProject(@Valid @RequestBody Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity<?> updateProject(@Valid @RequestBody Project project, @PathVariable int index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        try {
            projects.set(index, project);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity<?> deleteProject(@PathVariable int index) {
        try {
            projects.remove(index);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ApiResponse("Invalid index"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    // @PutMapping("/update/status/{index}")
    // public ResponseEntity<?> changeProjectStatus(@PathVariable int index,
    //                                              @RequestParam @NotBlank(message = "Status is required") @Pattern(regexp = "Not Started|In Progress|Completed", message = "Status must be Not Started, In Progress, or Completed only") String status
    //         , Errors errors) {
    //     if (errors.hasErrors()) {
    //         return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
    //     }
    //     if (index < 0 || index >= projects.size()) {
    //         return ResponseEntity.status(400).body(new ApiResponse("Invalid index"));
    //     }
    //     projects.get(index).setStatus(status);
    //     return ResponseEntity.status(200).body(new ApiResponse("Project status changed successfully"));
    // }

    // @GetMapping("/search/{title}")
    // public ResponseEntity<?> searchByTitle(@PathVariable String title) {
    //     for (Project project : projects) {
    //         if (project.getTitle().equals(title)) {
    //             return ResponseEntity.status(200).body(project);
    //         }
    //     }
    //     return ResponseEntity.status(400).body(new ApiResponse("Title not found"));
    // }


    //Update part
    @PutMapping("/update/status/{index}/{status}")
public ResponseEntity<?> changeProjectStatus(
        @PathVariable int index,
        @PathVariable String status) {

    if (index < 0 || index >= projects.size()) {
        return ResponseEntity.status(400).body(new ApiResponse("Invalid index"));
    }

    if (!status.equals("Not Started") &&
        !status.equals("In Progress") &&
        !status.equals("Completed")) {
        return ResponseEntity.status(400).body(new ApiResponse("Status must be Not Started, In Progress, or Completed only"));
    }

    projects.get(index).setStatus(status);
    return ResponseEntity.ok(new ApiResponse("Project status changed successfully"));
}


    @GetMapping("/search/company/{companyName}")
    public ResponseEntity<?> getCompanyProjects(@PathVariable String companyName) {
        ArrayList<Project> companyProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getCompanyName().equals(companyName)) {
                companyProjects.add(project);
            }
        }
        return ResponseEntity.status(200).body(companyProjects);
    }
}
