package rocketseat.com.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.passin.domain.attendee.Attendee;

public interface AttendeeRepostory extends JpaRepository<Attendee, String> {
}
