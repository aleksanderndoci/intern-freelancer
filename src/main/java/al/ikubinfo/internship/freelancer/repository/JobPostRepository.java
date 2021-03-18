package al.ikubinfo.internship.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import al.ikubinfo.internship.freelancer.entity.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Integer>{

}
