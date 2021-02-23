package al.ikubinfo.internship.freelancer.service;

import al.ikubinfo.internship.freelancer.model.TestModel;

public interface TestService {
    TestModel addTest(TestModel testModel);

    TestModel findById(Integer id);
}
