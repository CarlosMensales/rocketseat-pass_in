package rocketseat.com.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;
import rocketseat.com.dto.attendee.AttendeeIdDTO;
import rocketseat.com.dto.attendee.AttendeeRequestDTO;
import rocketseat.com.dto.attendee.AttendeesListResponseDTO;
import rocketseat.com.dto.event.EventIdDTO;
import rocketseat.com.dto.event.EventRequestDTO;
import rocketseat.com.dto.event.EventResponseDTO;
import rocketseat.com.passin.domain.event.exceptions.EventNotFoundException;
import rocketseat.com.passin.services.AttendeeService;
import rocketseat.com.passin.services.EventService;


@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private EventService eventService;
    private AttendeeService attendeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.eventService.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendee")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String eventId, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, body);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();
        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }


    @GetMapping("/attendee/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListResponseDTO attendeesListResponseDTO = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeesListResponseDTO);
    }
}
