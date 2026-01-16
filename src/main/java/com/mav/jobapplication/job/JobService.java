package com.mav.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    private final List<Job> jobs;

    public JobService() {
        this.jobs = new ArrayList<>();
    }

    public List<Job> getAll() {
        return jobs;
    }
}
