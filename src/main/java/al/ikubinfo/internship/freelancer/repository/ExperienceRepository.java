package al.ikubinfo.internship.freelancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.Experience;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Integer> {

	List<Experience> findByUserId(int userId);
	
}
