package al.ikubinfo.internship.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import al.ikubinfo.internship.freelancer.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

}
