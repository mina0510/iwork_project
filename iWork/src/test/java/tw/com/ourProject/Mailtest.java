package tw.com.ourProject;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class Mailtest {

	@Autowired
	private JavaMailSender jms;
	
	@Test
	public void sendMail() {
		try {
			MimeMessage Mimemessage = jms.createMimeMessage();
			MimeMessageHelper message = new MimeMessageHelper(Mimemessage, true , "UTF-8"); 
		    message.setFrom("iworkisgood@gmail.com"); 
		    message.setTo("k123ke123e@gmail.com");
		    message.setSubject("寄送信件測試1111");
		    message.setText("信件測試111");
		    
		    FileSystemResource file = new FileSystemResource(new File("D:/test8.pdf"));
		    message.addAttachment("附件-1.pdf", file);
		   
		    jms.send(Mimemessage);
		    System.out.println("寄送成功");
		}catch(Exception E) {
			System.out.println(E);
		}
		
	    
	}
}
