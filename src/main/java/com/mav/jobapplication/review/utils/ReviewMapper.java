package com.mav.jobapplication.review.utils;

import com.mav.jobapplication.company.CompanyRepository;
import com.mav.jobapplication.review.Review;
import com.mav.jobapplication.review.ReviewEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    private final CompanyRepository companyRepository;

    ReviewMapper(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Review toDomain(ReviewEntity reviewEntity) {
        return new Review(
                reviewEntity.getId(),
                reviewEntity.getTitle(),
                reviewEntity.getDescription(),
                reviewEntity.getRating(),
                reviewEntity.getCompany().getId()
        );
    }

    public ReviewEntity toEntity(Review review) {
        return new ReviewEntity(
                review.id(),
                review.title(),
                review.description(),
                review.rating(),
                companyRepository.findById(review.companyId()).orElseThrow(
                        () -> new EntityNotFoundException("Company with id " + review.companyId() + " not found!")
                )
        );
    }

    public Review insertCompanyId(Review review, Long companyId) {
        return new Review(
                review.id(),
                review.title(),
                review.description(),
                review.rating(),
                companyId
        );
    }
}
