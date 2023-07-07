package ac.uk.bolton.ecommercebackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author Sandaru Anjana <sandaruanjana@outlook.com>
 */
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject, String message,String token) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        helper.setFrom("devproject0001@outlook.com");
        helper.setTo(to);
        helper.setSubject(subject);

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("token", token);
        String htmlContent = templateEngine.process("email-template", context);

        helper.setText(htmlContent, true);

        javaMailSender.send(mimeMessage);
    }
}
