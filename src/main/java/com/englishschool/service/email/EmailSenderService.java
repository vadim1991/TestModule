package com.englishschool.service.email;

import com.englishschool.datamodel.CommonConstants;
import com.englishschool.entity.TestProfile;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.Date;

import static com.englishschool.datamodel.CommonConstants.*;

/**
 * Created by Vadym_Vlasenko on 11/3/2015.
 */
public class EmailSenderService {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    public void sendMimeEmail(final TestProfile profile) throws MessagingException {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @SuppressWarnings({"rawtypes", "unchecked"})
            public void prepare(MimeMessage mimeMessage) throws Exception {
                setupMimeMessageRegistration(mimeMessage, profile);
            }
        };
        mailSender.send(preparator);
    }

    private void setupMimeMessageRegistration(MimeMessage mimeMessage, TestProfile profile) throws MessagingException {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setTo(profile.getEmail());
        messageHelper.setFrom(FROM);
        messageHelper.setSubject("Your account has been created");
        messageHelper.setSentDate(new Date());
        messageHelper.setText(getContentForRegistration(profile), true);
    }

    private String getContentForRegistration(TestProfile profile) {
        Template template = velocityEngine.getTemplate("/templates/createProfile.vm");
        VelocityContext velocityContext = new VelocityContext();
        final StringWriter stringWriter = new StringWriter();
        velocityContext.put(NAME, profile.getName());
        velocityContext.put(EMAIL, profile.getEmail());
        velocityContext.put(PASSWORD, profile.getPassword());
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }


    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}
