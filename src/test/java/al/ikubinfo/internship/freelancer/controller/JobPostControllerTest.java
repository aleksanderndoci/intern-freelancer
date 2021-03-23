package al.ikubinfo.internship.freelancer.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import al.ikubinfo.internship.freelancer.entity.JobPost;
import al.ikubinfo.internship.freelancer.entity.User;
import al.ikubinfo.internship.freelancer.repository.JobPostRepository;
import al.ikubinfo.internship.freelancer.service.JobPostService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JobPostControllerTest {
	
	@Autowired
	private JobPostService postService;

	@MockBean
	private JobPostRepository postRepository;
	
	@Test
	public void testlistJobPostsByUserId() throws JsonProcessingException, Exception {
        JobPost post = new JobPost();
        post.setPosition("Java Developer");
        User user =new User();
        user.setId(1);
        user.setName("Ilsea");
        user.setSurname("Cani");
        post.setUser(user);
		List<JobPost> optJobPost = Arrays.asList(post);
		when(postRepository.findByUserId(1)).thenReturn(optJobPost);
		assertTrue(postService.listJobPostsByUserId(1)
				.get(0).getPosition().contains("Java Developer"));
	}

}
