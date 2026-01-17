package com.mav.jobapplication.job;

import com.mav.jobapplication.company.Company;

import java.util.List;

public interface JobService {
    List<Job> getAll();

    Job getOneById(Long id);

    Job create(Job job);

    void delete(Long id);

    Job update(Long id, Job job);

    Company getJobCompany(Long id);
}
