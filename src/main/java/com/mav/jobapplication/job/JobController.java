package com.mav.jobapplication.job;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public List<Job> getAll() {
        return jobService.getAll();
    }

    @GetMapping("/{id}")
    public Job getOneById(@PathVariable Long id) {
        return jobService.getOneById(id);
    }

    @PostMapping("/")
    public Job create(@RequestBody Job job) {
        return jobService.create(job);
    }
}
