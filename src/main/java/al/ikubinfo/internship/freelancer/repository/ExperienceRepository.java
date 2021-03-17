package al.ikubinfo.internship.freelancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Integer> {

//	@Query(value="Select e.name_of_company,e.position,e.position_description,"
//			+ "e.start_date,e.end_date from Experience e where e.user_id= ?1"
//			,nativeQuery = true)
	List<Experience> getExperiencesByUserUserId(int userId);
	
}
