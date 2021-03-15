package al.ikubinfo.internship.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.Experience;

@Repository
public interface ExperinceRepository extends JpaRepository<Experience,Integer> {

}
