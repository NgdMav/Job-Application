package com.mav.jobapplication.job.utils;

import com.mav.jobapplication.job.Job;
import com.mav.jobapplication.job.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    public Job toDomain(JobEntity jobEntity) {
        return new Job(
                jobEntity.getId(),
                jobEntity.getTitle(),
                jobEntity.getDescription(),
                jobEntity.getMinSalary(),
                jobEntity.getMaxSalary(),
                jobEntity.getLocation()
        );
    }

    public JobEntity toEntity(Job job) {
        return new JobEntity(
                job.id(),
                job.title(),
                job.description(),
                job.minSalary(),
                job.maxSalary(),
                job.location()
        );
    }
}
