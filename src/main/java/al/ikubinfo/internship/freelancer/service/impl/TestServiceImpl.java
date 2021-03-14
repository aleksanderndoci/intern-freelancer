package al.ikubinfo.internship.freelancer.service.impl;

import al.ikubinfo.internship.freelancer.entity.Users;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.TestModel;
import al.ikubinfo.internship.freelancer.service.TestService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@Slf4j
public class TestServiceImpl implements TestService {
//    @Autowired
//    private final UserRepository testRepository;
//
//    private final Mapper<Users, TestModel> testMapper;
//
//    public TestServiceImpl(Mapper<Users, TestModel> testMapper,
//                           UserRepository testRepository) {
//        this.testMapper = testMapper;
//        this.testRepository = testRepository;
//    }
//
  @Override
  public TestModel addTest(TestModel testModel) {
//        Users testEntity = testMapper.toEntity(testModel);
//        testRepository.save(testEntity);
//        return testMapper.toModel(testEntity);
	  return null;
}
//
 @Override
  public TestModel findById(Integer id) {
//        Users testEntity = testRepository.findById(id)
//                .orElseThrow(EntityNotFoundException::new);
//        return testMapper.toModel(testEntity);
  return null;
//    }
}
 }
