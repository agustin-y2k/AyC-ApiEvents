package event.api.service.smtp;

public interface EmailService {

    public String sendMail(String to, String subject, String content);

}
