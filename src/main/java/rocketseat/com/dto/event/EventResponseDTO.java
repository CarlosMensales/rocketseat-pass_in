package rocketseat.com.dto.event;

public class EventResponseDTO {
    EventDetailDTO event;

    public EventResponseDTO(rocketseat.com.passin.domain.event.Event event2, Integer numberOfAttendees) {
        this.event = new EventDetailDTO(event2.getId(), event2.getTitle(), event2.getDetails(), event2.getSlug(), event2.getMaximumAttendees(), numberOfAttendees);
    }
}
