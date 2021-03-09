package al.ikubinfo.internship.freelancer.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);
	
	
}
