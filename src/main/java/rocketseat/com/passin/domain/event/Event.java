package rocketseat.com.passin.domain.event;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, name = "maximum_attendees")
    private Integer maximumAttendees;
}
