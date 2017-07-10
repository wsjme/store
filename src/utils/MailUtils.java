package utils;



import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws MessagingException {
			// 创建会话对象
		//1、获得邮箱服务的连接(会话对象)
		Properties props = new Properties();//封装数据
		props.setProperty("mail.transport.protocol", "SMTP");//设置发邮件的协议
		props.setProperty("mail.host", "smtp.163.com");//设置发邮件的地址(smtp邮箱服务器地址)
		props.setProperty("mail.smtp.auth", "true");// 指定验证为true

		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				//验证邮箱用户名和密码
				return new PasswordAuthentication("wsj804750848@163.com", "wsj1001");//密码
			}
		};

		Session session = Session.getInstance(props, auth);//会话对象
		
		// 创建邮件对象
				
		Message message = new MimeMessage(session);//创建邮件对象
		message.setFrom(new InternetAddress("wsj804750848@163.com")); // 设置发送者
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // 设置发送方式与接收者
		message.setSubject("激活邮件");//邮件主题
		message.setContent(emailMsg, "text/html;charset=utf-8");//设置邮件的正文

		// 邮件发送
		Transport.send(message);
	}
}
