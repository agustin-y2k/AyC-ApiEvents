package event.api.service.smtp;

import event.api.AttendeeEntity;
import event.api.AttendeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmailServiceImplTest {

    @Mock
    private AttendeeRepository attendeeRepository;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender mailSender;

    private AttendeeEntity attendeeEntity;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        attendeeEntity = new AttendeeEntity();
        attendeeEntity.setDni("12345678");
        attendeeEntity.setEmail("agustin_y2k@hotmail.com");
        attendeeEntity.setName("John");
        attendeeEntity.setSurname("Doe");
        attendeeEntity.setPhone("+34 654321987");
        attendeeEntity.setBirthDate(new Date(2000, 1, 1));
        attendeeEntity.setDniScanUrl("http://www.google.com");
        attendeeEntity.setApproved(false);

    }

    @Test
    void sendMail() {
        when(attendeeRepository.findAttendeeByDni("12345678")).thenReturn(attendeeEntity);
        assertEquals("Email send successfully", emailService.sendMail("agustin_y2k@hotmail.com", "Attendee registered successfully", "Attendee registered successfully"));

    }
}