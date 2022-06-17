package event.api.service;

import event.api.AttendeeEntity;
import event.api.AttendeeRepository;
import event.api.service.smtp.EmailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AttendeeServiceImplTest {

    @Mock
    private AttendeeRepository attendeeRepository;

    @Mock
    private EmailServiceImpl emailService;

    @InjectMocks
    private AttendeeServiceImpl attendeeService;

    private AttendeeEntity attendeeEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        attendeeEntity = new AttendeeEntity();
        attendeeEntity.setDni("123475678");
        attendeeEntity.setEmail("agustin_y2k@hotmail.com");
        attendeeEntity.setName("John");
        attendeeEntity.setSurname("Doe");
        attendeeEntity.setPhone("+34 654321987");
        attendeeEntity.setBirthDate(new Date(2000, 1, 1));
        attendeeEntity.setDniScanUrl("http://www.google.com");
        attendeeEntity.setApproved(true);
    }

    @Test
    void registerAttendee() throws Exception {
        when(attendeeRepository.findAttendeeByDni(attendeeEntity.getDni())).thenReturn(null);
        assertEquals("You are registered", attendeeService.registerAttendee(attendeeEntity));
    }

    @Test
    void getAttendeeByDni() throws Exception {
        when(attendeeRepository.findAttendeeByDni("12345678")).thenReturn(attendeeEntity);
        assertEquals(attendeeEntity, attendeeService.getAttendeeByDni("12345678"));
    }

    @Test
    void getAllAttendees() throws Exception {
        when(attendeeRepository.findAll()).thenReturn(List.of(attendeeEntity));
        assertEquals(List.of(attendeeEntity), attendeeService.getAllAttendees());
    }

    @Test
    void verifyAttendee() throws Exception {
        when(attendeeRepository.findAttendeeByDni("12345678")).thenReturn(attendeeEntity);
        assertEquals("Attendee verified successfully", attendeeService.verifyAttendee("12345678"));
    }

    @Test
    void getAttendeeStatus() throws Exception {
        when(attendeeRepository.findAttendeeByDni("12345678")).thenReturn(attendeeEntity);
        assertEquals("Approved", attendeeService.getAttendeeStatus("12345678"));
    }

    @Test
    void deleteAttendee() throws Exception {
        when(attendeeRepository.findAttendeeByDni("12345678")).thenReturn(attendeeEntity);
        assertEquals("Attendee deleted successfully", attendeeService.deleteAttendee("12345678"));
    }
}