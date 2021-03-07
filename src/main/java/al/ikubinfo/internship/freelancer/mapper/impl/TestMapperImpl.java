package al.ikubinfo.internship.freelancer.mapper.impl;

import al.ikubinfo.internship.freelancer.entity.TestEntity;
import al.ikubinfo.internship.freelancer.mapper.Mapper;
import al.ikubinfo.internship.freelancer.model.TestModel;
import org.springframework.stereotype.Component;

@Component
public class TestMapperImpl implements Mapper<TestEntity, TestModel> {

	@Override
	public TestEntity toEntity(TestModel model) {
		TestEntity testEntity = new TestEntity();
		testEntity.setValue(model.getValue());
		return testEntity;
	}

	@Override
	public TestModel toModel(TestEntity entity) {
		TestModel testModel = new TestModel();
		testModel.setValue(entity.getValue());
		return testModel;
	}
}
