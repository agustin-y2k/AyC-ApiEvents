package event.api.service;

import event.api.AttendeeEntity;
import event.api.AttendeeRepository;
import event.api.exception.EntityNotFoundException;
import event.api.exception.EntityNotValidException;
import event.api.service.smtp.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendeeServiceImpl implements AttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Override
    public String registerAttendee(AttendeeEntity attendeeEntity) throws Exception {
        if (!attendeeEntity.getDni().matches("\\d*(\\.\\d+)?")) {
            throw new EntityNotValidException("The DNI format is invalid");
        } else {
            AttendeeEntity attendee = attendeeRepository.findAttendeeByDni(attendeeEntity.getDni());
            if (attendee != null) {
                throw new EntityNotValidException("The DNI is already registered");
            } else {
                attendeeRepository.save(attendeeEntity);
                return "You are registered";
            }
        }
    }

    @Override
    public AttendeeEntity getAttendeeByDni(String dni) throws Exception {
        if (!dni.matches("\\d*(\\.\\d+)?")) {
            throw new EntityNotValidException("The DNI format is invalid");
        } else {
            AttendeeEntity attendee = attendeeRepository.findAttendeeByDni(dni);
            if (attendee == null) {
                throw new EntityNotFoundException("Attendee does not exist");
            } else {
                return attendee;
            }
        }
    }

    @Override
    public List<AttendeeEntity> getAllAttendees() throws Exception {
        if (attendeeRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("There are no attendees registered");
        } else {
            return attendeeRepository.findAll();
        }
    }

    @Override
    public String verifyAttendee(String dni) throws Exception {
        if (!dni.matches("\\d*(\\.\\d+)?")) {
            throw new EntityNotValidException("The DNI format is invalid");
        } else {
            AttendeeEntity attendee = attendeeRepository.findAttendeeByDni(dni);
            if (attendee == null) {
                throw new EntityNotFoundException("Attendee does not exist");
            } else {
                attendee.setApproved(true);
                attendeeRepository.save(attendee);
                emailService.sendMail(attendee.getEmail(), "Attendee verification", "Your attendee has been verified");
                return "Attendee verified successfully";
            }
        }
    }

    @Override
    public String getAttendeeStatus(String dni) throws Exception {
        if (!dni.matches("\\d*(\\.\\d+)?")) {
            throw new EntityNotValidException("The DNI format is invalid");
        } else {
            AttendeeEntity attendee = attendeeRepository.findAttendeeByDni(dni);
            if (attendee == null) {
                throw new EntityNotFoundException("Attendee does not exist");
            } else {
                String message = attendee.isApproved() ? "Approved" : "Unapproved";
                return message;
            }
        }
    }

    @Override
    public String deleteAttendee(String dni) throws Exception {
        if (!dni.matches("\\d*(\\.\\d+)?")) {
            throw new EntityNotValidException("The DNI format is invalid");
        } else {
            AttendeeEntity attendee = attendeeRepository.findAttendeeByDni(dni);
            if (attendee == null) {
                throw new EntityNotFoundException("Attendee does not exist");
            } else {
                attendeeRepository.delete(attendee);
                return "Attendee deleted successfully";
            }
        }
    }


}
