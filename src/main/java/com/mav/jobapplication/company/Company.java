package com.mav.jobapplication.company;

import com.mav.jobapplication.job.Job;
import com.mav.jobapplication.review.Review;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.util.Set;

public record Company(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String description,

        @Nullable
        Set<Job> jobs,
        @Nullable
        Set<Review> reviews
) {
}
