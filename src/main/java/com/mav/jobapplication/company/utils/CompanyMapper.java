package com.mav.jobapplication.company.utils;

import com.mav.jobapplication.company.Company;
import com.mav.jobapplication.company.CompanyEntity;
import com.mav.jobapplication.job.utils.JobMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private final JobMapper jobMapper;

    public CompanyMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    public Company toDomain(CompanyEntity companyEntity) {
        return new Company(
                companyEntity.getId(),
                companyEntity.getName(),
                companyEntity.getDescription(),
                companyEntity.getJobs() == null ?
                        null :
                        companyEntity.getJobs().stream().map(jobMapper::toDomain).collect(Collectors.toSet())
        );
    }

    public CompanyEntity toEntity(Company company) {
        return new CompanyEntity(
                company.id(),
                company.name(),
                company.description(),
                company.jobs() == null ?
                        new HashSet<>() :
                        company.jobs().stream().map(jobMapper::toEntity).collect(Collectors.toSet())
        );
    }
}
