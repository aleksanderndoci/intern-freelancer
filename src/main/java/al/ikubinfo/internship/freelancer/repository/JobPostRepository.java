package al.ikubinfo.internship.freelancer.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import al.ikubinfo.internship.freelancer.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	List<JobPost> findByUserId(int userId);
	
	@Query
	(value="select * from job_post where job_post_type=:type and lower(position) like lower(:positionName)",nativeQuery = true)
	public List<JobPost> findByJobPostTypeAndPosition(@Param("type") String type,@Param("positionName")String position);
	

	
	
}
