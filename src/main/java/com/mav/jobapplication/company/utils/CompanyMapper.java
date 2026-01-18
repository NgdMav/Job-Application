package com.mav.jobapplication.company.utils;

import com.mav.jobapplication.company.Company;
import com.mav.jobapplication.company.CompanyEntity;
import com.mav.jobapplication.job.utils.JobMapper;
import com.mav.jobapplication.review.utils.ReviewMapper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private final JobMapper jobMapper;
    private final ReviewMapper reviewMapper;

    public CompanyMapper(JobMapper jobMapper, ReviewMapper reviewMapper) {
        this.jobMapper = jobMapper;
        this.reviewMapper = reviewMapper;
    }

    public Company toDomain(CompanyEntity companyEntity) {
        return new Company(
                companyEntity.getId(),
                companyEntity.getName(),
                companyEntity.getDescription(),
                companyEntity.getJobs() == null ?
                        null :
                        companyEntity.getJobs().stream().map(jobMapper::toDomain).collect(Collectors.toSet()),
                companyEntity.getReviews() == null ?
                        null :
                        companyEntity.getReviews().stream().map(reviewMapper::toDomain).collect(Collectors.toSet())
        );
    }

    public CompanyEntity toEntity(Company company) {
        return new CompanyEntity(
                company.id(),
                company.name(),
                company.description(),
                company.jobs() == null ?
                        new HashSet<>() :
                        company.jobs().stream().map(jobMapper::toEntity).collect(Collectors.toSet()),
                company.reviews() == null ?
                        new HashSet<>() :
                        company.reviews().stream().map(reviewMapper::toEntity).collect(Collectors.toSet())
        );
    }
}
