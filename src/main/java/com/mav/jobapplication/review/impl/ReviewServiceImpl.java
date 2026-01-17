package com.mav.jobapplication.review.impl;

import com.mav.jobapplication.review.Review;
import com.mav.jobapplication.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReviewServiceImpl implements ReviewService {

    @Override
    public List<Review> getAll(Long companyId) {
        return List.of();
    }

    @Override
    public Review getOneById(Long companyId, Long id) {
        return null;
    }

    @Override
    public Review create(Long companyId, Review review) {
        return null;
    }

    @Override
    public void delete(Long companyId, Long id) {

    }

    @Override
    public Review update(Long companyId, Long id, Review review) {
        return null;
    }
}
