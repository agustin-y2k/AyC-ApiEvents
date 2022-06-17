package event.api;

import event.api.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@Validated
public class AttendeeController {

    @Autowired
    private AttendeeService attendeeService;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/attendee/register")
    public String showAttendeeForm(Model model) {
        model.addAttribute("attendee", new AttendeeEntity());
        return "register-attendee";
    }

    @PostMapping("/attendee/register")
    public String registerAttendee(@Valid @ModelAttribute("attendee") AttendeeEntity attendeeEntity) throws Exception {
        try {
            attendeeService.registerAttendee(attendeeEntity);
            return "redirect:/";
        } catch (Exception e) {
            return "register-attendee";
        }
    }

    @GetMapping("/attendee/checkStatus/{dni}")
    public String checkAttendeeStatus(@PathVariable(name = "dni") String dni) throws Exception {
        String message = attendeeService.getAttendeeStatus(dni);
        if (message.equals("Approved")) {
            return "approved";
        } else {
            return "not-approved";
        }
    }

    @GetMapping("/attendee/{dni}")
    public ResponseEntity<AttendeeEntity> getAttendeeByDNI(@PathVariable(name = "dni") String dni) throws Exception {
        AttendeeEntity attendee = attendeeService.getAttendeeByDni(dni);
        return new ResponseEntity<>(attendee, HttpStatus.OK);
    }

    @GetMapping("/attendees")
    public ResponseEntity<List<AttendeeEntity>> getAllAttendees() throws Exception {
        List<AttendeeEntity> attendees = attendeeService.getAllAttendees();
        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }

    @PostMapping("/attendee/verify/{dni}")
    public ResponseEntity<String> attendeeVerification(@PathVariable(name = "dni") String dni) throws Exception {
        String message = attendeeService.verifyAttendee(dni);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/attendee/{dni}")
    public ResponseEntity<String> deleteAttendee(@PathVariable(name = "dni") String dni) throws Exception {
        String message = attendeeService.deleteAttendee(dni);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}

