package al.ikubinfo.internship.freelancer.repository.registration;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.ConfirmationToken;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
	
	Optional<ConfirmationToken> findByToken(String token);

	 @Transactional
	 @Modifying
	 @Query("UPDATE ConfirmationToken c " +
	            "SET c.confirmedAt = ?2 " +
	            "WHERE c.token = ?1")
	    int updateConfirmedAt(String token,
	                          LocalDateTime confirmedAt);
	
}
