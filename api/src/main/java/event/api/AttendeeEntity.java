package event.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendee")
public class AttendeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @NotEmpty(message = "DNI is required")
    @Pattern(regexp = "\\d*(\\.\\d+)?", message = "the dni must contain only numbers")
    @Column(name = "dni")
    private String dni;

    @Email
    @NotEmpty(message = "Email is required")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "must indicate the attendee name")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "must indicate the attendee surname")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "must indicate the attendee phone")
    @Column(name = "phone")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthDate")
    @NotEmpty(message = "must indicate the attendee birth date")
    private Date birthDate;

    @NotEmpty(message = "must indicate the dniScan URL")
    @Column(name = "dniScanUrl")
    private String dniScanUrl;

    @JsonIgnore
    @Column(name = "approved")
    private boolean approved = false;

}
