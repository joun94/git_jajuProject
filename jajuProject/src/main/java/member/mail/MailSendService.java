package member.mail;

import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	@Autowired
	private JavaMailSender mailSender;
	
	private String getKey(int size) {
		return getAuthCode(size);
	}
	
	private String getAuthCode(int size) {
		Random random = new Random();
		StringBuffer buffer = new StringBuffer();
		int num = 0;
		
		while(buffer.length() < size) {
			num = random.nextInt(10);
			buffer.append(num);
		}
		
		return buffer.toString();
	}
	
	public String sendAuthMail(String email) {
		String authKey = getKey(6);
		
		MimeMessage mail = mailSender.createMimeMessage();
		
		String mailContent = "<table>" + 
				"		<tr>" + 
				"		 <td style=\"padding-right:27px; padding-left:18px;line-height:34px;font-size:29px;color:#424240;font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" + 
				"		   	요청하신 인증번호를<br>" + 
				"		 	<span style=\"color:#064acb\">발송해드립니다.</span>" + 
				"		 </td>" + 
				"		</tr>" + 
				"		<tr>" + 
				"			<td height=\"22\"></td>" + 
				"		</tr>" + 
				"		<tr>" + 
				"			<td height=\"1\" style=\"background-color: #e5e5e5;\"></td>" + 
				"		</tr>" + 
				"		<tr>" + 
				"			<td style=\"padding-top:24px; padding-right:27px; padding-bottom:32px; padding-left:20px\">" + 
				"				<table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" + 
				"					<tr>" + 
				"						<td style=\"font-size:14px;color:#696969;line-height:24px;font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" + 
				"							<strong>아래의 인증번호를 인증번호 입력창에 입력해 주세요.</strong>" + 
				"						</td>" + 
				"					</tr>" + 
				"					<tr><td height=\"24\"></td></tr>" + 
				"					<tr>" + 
				"						<td style=\"font-size:14px;color:#696969;line-height:24px;font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" + 
				"							<strong>인증번호 : <span style=\"color:#064acb\">" + authKey + "</span></strong>" + 
				"						</td>" + 
				"					</tr>" + 
				"					<tr><td height=\"24\"></td></tr>" + 
				"					<tr>" + 
				"						<td style=\"font-size:14px;color:#696969;line-height:24px;font-family:'나눔고딕',NanumGothic,'맑은고딕',Malgun Gothic,'돋움',Dotum,Helvetica,'Apple SD Gothic Neo',Sans-serif;\">" + 
				"							자주마켓를 이용해 주셔서 감사합니다.<br>" + 
				"							더욱 편리한 서비스를 제공하기 위해 항상 최선을 다하겠습니다." + 
				"						</td>" + 
				"					</tr>" + 
				"				</table>" + 
				"			</td>" + 
				"		</tr>" + 
				"	</table>";
				
		try {
			mail.setSubject("JAJUMarket 이메일 인증", "UTF-8");
			mail.setText(mailContent, "UTF-8", "html");
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return authKey;
	}
}
