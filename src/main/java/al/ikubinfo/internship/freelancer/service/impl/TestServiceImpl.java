package al.ikubinfo.internship.freelancer.service.impl;

import al.ikubinfo.internship.freelancer.entity.TestEntity;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.TestModel;
import al.ikubinfo.internship.freelancer.repository.TestRepository;
import al.ikubinfo.internship.freelancer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    private final Mapper<TestEntity, TestModel> testMapper;

    public TestServiceImpl(Mapper<TestEntity, TestModel> testMapper,
                           TestRepository testRepository) {
        this.testMapper = testMapper;
        this.testRepository = testRepository;
    }

    @Override
    public TestModel addTest(TestModel testModel) {
        TestEntity testEntity = testMapper.toEntity(testModel);
        testRepository.save(testEntity);
        return testMapper.toModel(testEntity);
    }

    @Override
    public TestModel findById(Integer id) {
        TestEntity testEntity = testRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return testMapper.toModel(testEntity);
    }
}
