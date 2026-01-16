package com.mav.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        if (!jobs.containsKey(id)) {
            throw new NoSuchElementException("Job with id " + id + " does not exist");
        }
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

    public void delete(Long id) {
        if (!jobs.containsKey(id)) {
            throw new NoSuchElementException("Job with id " + id + " does not exist");
        }
        jobs.remove(id);
    }

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
