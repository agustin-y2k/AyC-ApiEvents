package event.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<AttendeeEntity, Long> {
    AttendeeEntity findAttendeeByDni(String dni);

}
