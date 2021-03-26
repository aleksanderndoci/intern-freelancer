package al.ikubinfo.internship.freelancer.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import al.ikubinfo.internship.freelancer.entity.JobPost;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

	List<JobPost> findByUserId(int userId);
	
	@Query("select j from JobPost j "
			+ "left join Application a on j.id=a.id.jobPostId "
			+ "where j.jobPostType=:type and lower(j.position) like lower(concat('%', :positionName,'%')) or a.applicationStatus='REFUSED'")
	public List<JobPost> findByJobPostTypeAndPosition(@Param("type") String type, @Param("positionName")String position,
			Pageable pageable);

}
