package al.ikubinfo.internship.freelancer.service.registration;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

	@Autowired
	private JavaMailSender mailSender;

	private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailService.class);

	@Override
	@Async
	public void sendEmail(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("CONFIRM YOUR EMAIL");
			helper.setFrom("alesjacani5@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("Failed to send email!", e);
			throw new IllegalStateException("Failed to send email!");
		}

	}
	

}
