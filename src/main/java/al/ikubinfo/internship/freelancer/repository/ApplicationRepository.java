package al.ikubinfo.internship.freelancer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import al.ikubinfo.internship.freelancer.entity.Application;
import al.ikubinfo.internship.freelancer.entity.ApplicationKey;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, ApplicationKey> {

}
