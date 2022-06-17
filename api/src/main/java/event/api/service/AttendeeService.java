package event.api.service;

import event.api.AttendeeEntity;

import java.util.List;

public interface AttendeeService {

    public String registerAttendee(AttendeeEntity attendeeEntity) throws Exception;

    public List<AttendeeEntity> getAllAttendees() throws Exception;
    public AttendeeEntity getAttendeeByDni(String dni) throws Exception;
    public String verifyAttendee(String dni) throws Exception;
    public String getAttendeeStatus(String dni) throws Exception;
    public String deleteAttendee(String dni) throws Exception;

}
