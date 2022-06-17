package event.api;

import event.api.service.AttendeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AttendeeController.class)
class AttendeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @InjectMocks
    AttendeeController controller;

    @MockBean
    private AttendeeServiceImpl service;

    private AttendeeEntity attendeeEntity;


    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        mockMvc = org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup(context).build();

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
    void index() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void showAttendeeForm() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/attendee/register"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void registerAttendee() throws Exception {
        when(service.registerAttendee(any(AttendeeEntity.class))).thenReturn("Attendee registered successfully");

        RequestBuilder request = MockMvcRequestBuilders.get("/attendee/register").accept("application/json");
        MvcResult result = null;
    }

    @Test
    void checkAttendeeStatus() throws Exception {
        when(service.getAttendeeStatus(any(String.class))).thenReturn("Attendee status checked successfully");

        RequestBuilder request = MockMvcRequestBuilders.get("/attendee/checkStatus").accept("application/json");
        MvcResult result = null;
    }

    @Test
    void getAttendeeByDNI() throws Exception {
        when(service.getAttendeeByDni(attendeeEntity.getDni())).thenReturn(attendeeEntity);

        RequestBuilder request = MockMvcRequestBuilders.get("/attendee/12345678").accept("application/json");
        MvcResult result = null;
        result = mockMvc.perform(request).andReturn();
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void getAllAttendees() throws Exception {
        when(service.getAllAttendees()).thenReturn(List.of(attendeeEntity));

        RequestBuilder request = MockMvcRequestBuilders.get("/attendees").accept("application/json");
        MvcResult result = null;
        result = mockMvc.perform(request).andReturn();
        assertEquals(200, result.getResponse().getStatus());

    }

    @Test
    void attendeeVerification() throws Exception {
        when(service.verifyAttendee(any(String.class))).thenReturn("Attendee verified successfully");
    }

    @Test
    void deleteAttendee() throws Exception {
        when(service.deleteAttendee(any(String.class))).thenReturn("Attendee deleted successfully");
    }

}