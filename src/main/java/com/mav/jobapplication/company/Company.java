package com.mav.jobapplication.company;

import com.mav.jobapplication.job.Job;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.List;

public record Company(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,

        @Nullable
        List<Job> jobs
) {
}
