package com.mav.jobapplication.job.impl;

import com.mav.jobapplication.job.Job;
import com.mav.jobapplication.job.JobRepository;
import com.mav.jobapplication.job.JobService;
import com.mav.jobapplication.job.utils.JobMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll().stream().map(jobMapper::toDomain).toList();
    }

    @Override
    public Job getOneById(Long id) {
        return jobMapper.toDomain(jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Job with id " + id + " does not exist")
        ));
    }

    @Override
    public Job create(Job job) {
        if (job.maxSalary() < job.minSalary()) {
            throw new IllegalArgumentException("Max salary needs to be greater than min salary");
        }
        var jobEntity = jobMapper.toEntity(job);
        var result = jobRepository.save(jobEntity);
        return jobMapper.toDomain(result);
    }

    @Override
    public void delete(Long id) {
        var jobEntity = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Job with id " + id + " does not exist")
        );
        jobRepository.delete(jobEntity);
    }

    @Override
    public Job update(Long id, Job job) {
        var jobEntityOld = jobRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Job with id " + id + " does not exist")
        );
        if (job.maxSalary() < job.minSalary()) {
            throw new IllegalArgumentException("Max salary needs to be greater than min salary");
        }
        var jobEntityNew = jobMapper.toEntity(job);
        jobEntityNew.setId(jobEntityOld.getId());
        var result = jobRepository.save(jobEntityNew);
        return jobMapper.toDomain(result);
    }
}
