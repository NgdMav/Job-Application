package com.mav.jobapplication.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    List<ReviewEntity> findByCompany_Id(Long companyId);

    Optional<ReviewEntity> findByCompany_IdAndId(Long id, Long id1);

}