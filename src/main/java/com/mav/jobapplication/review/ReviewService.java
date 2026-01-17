package com.mav.jobapplication.review;

import jakarta.validation.Valid;

import java.util.List;

public interface ReviewService {

    List<Review> getAll();

    Review getOneById(Long id);

    Review create(@Valid Review review);

    void delete(Long id);

    Review update(Long id, @Valid Review review);
}
