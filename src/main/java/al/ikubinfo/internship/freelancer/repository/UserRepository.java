package al.ikubinfo.internship.freelancer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.Users;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmail(String email);
	


	@Transactional
	@Modifying
	@Query("UPDATE Users u " + "SET u.enabled = true WHERE u.email = ?1")
	int enableUser(String email);

	@Query("SELECT COUNT(*) FROM Users u WHERE u.email = ?1 AND u.password = ?2")
	boolean validUser(String email, String password);
	
	@Query("Select u.locked from Users u where u.email = ?1")
	boolean locked(String email);

	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.locked = true WHERE u.email= ?1")
	int lockUser(String email);
	
	
}
