package tw.com.ourProject.utils;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender jms;

	public void mailOnlyText(String addressTo, String subject, String text) {
		try {
			MimeMessage Mimemessage = jms.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(Mimemessage, true, "UTF-8");
			message.setFrom("iworkisgood@gmail.com");
			message.setTo(addressTo);
			message.setSubject(subject);
			message.setText(text);

			jms.send(Mimemessage);
			System.out.println("寄送成功");
		} catch (Exception E) {
			System.out.println(E);
		}
	}

	public void mailWithAttchMent(String addressTo, String subject, String text , String filePath) {
		try {
			MimeMessage Mimemessage = jms.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(Mimemessage, true, "UTF-8");
			message.setFrom("iworkisgood@gmail.com");
			message.setTo(addressTo);
			message.setSubject(subject);
			message.setText(text);

		    FileSystemResource file = new FileSystemResource(new File(filePath));
		    message.addAttachment("附件-1.pdf", file);
		   
			jms.send(Mimemessage);
			System.out.println("寄送附件成功");
		} catch (Exception E) {
			System.out.println(E);
		}
	}
}
