package com.mav.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobService {
    private final Map<Long, Job> jobs;
    private final AtomicLong counter;

    public JobService() {
        this.jobs = new HashMap<>();
        this.counter = new AtomicLong(0);
    }

    public List<Job> getAll() {
        return jobs.values().stream().toList();
    }

    public Job getOneById(Long id) {
        return jobs.get(id);
    }

    public Job create(Job job) {
        if (job.getId() != null) {
            throw new IllegalStateException("Job must not have an id");
        }
        job.setId(counter.incrementAndGet());
        jobs.put(job.getId(), job);
        return job;
    }
}
