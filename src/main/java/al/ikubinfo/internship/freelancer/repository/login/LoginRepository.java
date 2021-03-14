package al.ikubinfo.internship.freelancer.repository.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import al.ikubinfo.internship.freelancer.entity.Users;

@Repository
@Transactional(readOnly = true)
public interface LoginRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByEmailAndPassword(String email,String password);

	
	@Query("SELECT COUNT(*) FROM Users WHERE email = ?1 AND password = ?2")
	boolean validUser(String email, String password);
	
	@Transactional
	@Query("UPDATE Users u SET u.locked = true WHERE u.email= ?1")
	int lockUser(String email);
}
