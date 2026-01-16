package com.mav.jobapplication.company.utils;

import com.mav.jobapplication.company.Company;
import com.mav.jobapplication.company.CompanyEntity;
import com.mav.jobapplication.job.utils.JobMapper;
import org.springframework.stereotype.Component;

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
                        companyEntity.getJobs().stream().map(jobMapper::toDomain).toList()
        );
    }

    public CompanyEntity toEntity(Company company) {
        return new CompanyEntity(
                company.id(),
                company.name(),
                company.description(),
                company.jobs() == null ?
                        null :
                        company.jobs().stream().map(jobMapper::toEntity).toList()
        );
    }
}
