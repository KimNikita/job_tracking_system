package com.job_tracking_system.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class TaskUploadRequest {

    @NotBlank(message = "Task name is required!")
    private String name;
    @NotNull
    @Positive(message = "Difficulty cannot be Zero or negative")
    private double difficulty;
    @NotBlank(message = "Task description is required!")
    private String description;
}
