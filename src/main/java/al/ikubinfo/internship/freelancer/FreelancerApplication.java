package al.ikubinfo.internship.freelancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class FreelancerApplication {
	public static void main(String[] args) {
		SpringApplication.run(FreelancerApplication.class, args);
	}
}
