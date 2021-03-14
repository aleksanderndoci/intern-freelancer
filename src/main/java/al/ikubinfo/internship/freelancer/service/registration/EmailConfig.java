package al.ikubinfo.internship.freelancer.service.registration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
//
//	
//	@Bean
//    public JavaMailSender getJavaMailSender() 
//    {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("localhost");
//        mailSender.setPort(1025);
//          
//        mailSender.setUsername("hello");
//        mailSender.setPassword("hello");
//          
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.trust", "*");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.connectiontimeout", 5000);
//        props.put("mail.smtp.timeout", 3000);
//        props.put("mail.smtp.writetimeout", 5000);
//        
//        
//        
//          
//        return mailSender;
//    }
}
