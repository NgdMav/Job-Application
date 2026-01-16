package com.mav.jobapplication.job.impl;

import com.mav.jobapplication.job.Job;
import com.mav.jobapplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class JobServiceImpl implements JobService {
    private final Map<Long, Job> jobs;
    private final AtomicLong counter;

    public JobServiceImpl() {
        this.jobs = new HashMap<>();
        this.counter = new AtomicLong(0);
    }

    @Override
    public List<Job> getAll() {
        return jobs.values().stream().toList();
    }

    @Override
    public Job getOneById(Long id) {
        if (!jobs.containsKey(id)) {
            throw new NoSuchElementException("Job with id " + id + " does not exist");
        }
        return jobs.get(id);
    }

    @Override
    public Job create(Job job) {
        if (job.getId() != null) {
            throw new IllegalStateException("Job must not have an id");
        }
        job.setId(counter.incrementAndGet());
        jobs.put(job.getId(), job);
        return job;
    }

    @Override
    public void delete(Long id) {
        if (!jobs.containsKey(id)) {
            throw new NoSuchElementException("Job with id " + id + " does not exist");
        }
        jobs.remove(id);
    }

    @Override
    public Job update(Long id, Job job) {
        if (!jobs.containsKey(id)) {
            throw new NoSuchElementException("Job with id " + id + " does not exist");
        }
        var newJob = new Job(
                id,
                job.getTitle(),
                job.getDescription(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getLocation()
        );
        jobs.put(id, newJob);
        return newJob;
    }
}
