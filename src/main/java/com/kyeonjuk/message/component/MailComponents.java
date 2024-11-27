package com.kyeonjuk.message.component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailComponents {

    // 메일 전송을 위한 인터페이스 주입
    private final JavaMailSender javaMailSender;

    /**
     * 1. 메일 전송 테스트
     */
    public void sendMailTest() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("mailtestback@gmail.com");
        msg.setSubject("테스트 메시지 제목입니다.");
        msg.setText("테스트 메시지 내용입니다.");

        javaMailSender.send(msg);
    }

    /**
     * 2. 메일 전송
     */
    public boolean sendMail(String mail, String subject, String text) {

        boolean result = false;

        MimeMessagePreparator msg = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMessageHelper.setTo(mail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(text, true);
            }
        };

        try {
            // 메시지 전송
            javaMailSender.send(msg);
            result = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
