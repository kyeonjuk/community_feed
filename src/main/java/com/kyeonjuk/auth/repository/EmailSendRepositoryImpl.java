package com.kyeonjuk.auth.repository;

import com.kyeonjuk.auth.application.interfaces.EmailSendRepository;
import com.kyeonjuk.auth.domain.Email;
import com.kyeonjuk.message.component.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmailSendRepositoryImpl implements EmailSendRepository {

    private final MailComponents mailComponents;

    @Override
    public void sendEmail(Email email, String token) {

        String subject = "[PhotoGram] 이메일 인증입니다.";
        String text = "<div class=\"mail_view_contents_inner\" data-translate-body-53082=\"\">"
            + "    <div>"
            + "        <table style=\"width:750px;margin:0 auto;background:#fff;\" cellpadding=\"0\" cellspacing=\"0\">"
            + "            <tbody>"
            + "                <tr>"
            + "                    <td style=\"padding:50px 50px 80px 50px;background:#fff\">"
            + "                        <p style=\"font-size:30px;font-weight:700;line-height:1em;margin:0 0 20px 0;\">PhotoGram 이메일 인증</p>"
            + "                        <p style=\"font-size:16px;line-height:26px;\">"
            + "                            안녕하세요. <strong>" + email.getEmailText() + "</strong>님<br>"
            + "                            PhotoGram 의 소식과 다양한 혜택을 만나보세요."
            + "                        </p>"
            + "                        <div style=\"border-top:solid 2px #222;border-bottom:solid 1px #ddd;padding:20px 30px;margin-top:30px;\">"
            + "                            <p style=\"line-height:32px;font-size:16px;\">"
            + "                                <span style=\"display:inline-block;width:90px\">아이디</span>"
            + "                                <strong>" + email.getEmailText() + "</strong>"
            + "                            </p>"
            + "                        </div>"
            + "                        <div style=\"border-top:solid 2px #222;border-bottom:solid 1px #ddd;padding:20px 30px;margin-top:30px;\">"
            + "                            <p style=\"line-height:32px;font-size:16px;\">"
            + "                                <span style=\"display:inline-block;width:90px\">인증코드</span>"
            + "                                <strong>" + token + "</strong>"
            + "                            </p>"
            + "                        </div>"
            + "                    </td>"
            + "                </tr>"
            + "            </tbody>"
            + "        </table>"
            + "    </div>"
            + "</div>";

        mailComponents.sendMail(email.getEmailText(), subject, text);
    }
}
