package com.mav.jobapplication.job.utils;

import com.mav.jobapplication.company.CompanyRepository;
import com.mav.jobapplication.job.Job;
import com.mav.jobapplication.job.JobEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {
    private final CompanyRepository companyRepository;

    public JobMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Job toDomain(JobEntity jobEntity) {
        return new Job(
                jobEntity.getId(),
                jobEntity.getTitle(),
                jobEntity.getDescription(),
                jobEntity.getMinSalary(),
                jobEntity.getMaxSalary(),
                jobEntity.getLocation(),
                jobEntity.getCompanyEntity() != null ?
                        jobEntity.getCompanyEntity().getId() : null
        );
    }

    public JobEntity toEntity(Job job) {
        if (job == null) return null;

        return new JobEntity(
                job.id(),
                job.title(),
                job.description(),
                job.minSalary(),
                job.maxSalary(),
                job.location(),
                companyRepository.findById(job.companyId()).orElseThrow(
                        () -> new EntityNotFoundException("Company with id " + job.companyId() + " not found!")
                )
        );
    }
}
