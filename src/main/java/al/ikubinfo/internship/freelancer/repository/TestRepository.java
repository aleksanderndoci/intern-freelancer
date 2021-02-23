package al.ikubinfo.internship.freelancer.repository;

import al.ikubinfo.internship.freelancer.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {
}
