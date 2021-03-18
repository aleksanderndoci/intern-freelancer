package al.ikubinfo.internship.freelancer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.User;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.enabled = true WHERE u.email = ?1")
	int enableUser(String email);

	@Query(value = "SELECT COUNT(*) FROM User u WHERE u.email = ?1 AND u.password = ?2", nativeQuery = true)
	boolean validUser(String email, String password);

	@Query(value = "Select u.locked from users u where u.email = ?1", nativeQuery = true)
	boolean checkIfLocked(String email);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.locked = true WHERE u.email= ?1")
	int lockUser(String email);

}
