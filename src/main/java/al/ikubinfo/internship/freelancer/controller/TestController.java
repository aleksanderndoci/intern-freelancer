package al.ikubinfo.internship.freelancer.controller;

import al.ikubinfo.internship.freelancer.model.TestModel;
import al.ikubinfo.internship.freelancer.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/add")
    public ResponseEntity<TestModel> addTest(@RequestBody TestModel testModel) {
        return ResponseEntity
                .ok(testService.addTest(testModel));
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<TestModel> getTestById(@PathVariable Integer id) {
        return ResponseEntity
                .ok(testService.findById(id));
    }
}
