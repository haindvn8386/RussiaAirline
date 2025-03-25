package rediscache.russiaairline.entity;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Data;

@Entity
@Table(name = "Flights")
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private Integer flightId;

    @Column(name = "flight_no", length = 6, nullable = false)
    private Character flightNo;

    @Column(name = "scheduled_departure", nullable = false)
    private OffsetDateTime scheduledDeparture;

    @Column(name = "scheduled_arrival", nullable = false)
    private OffsetDateTime scheduledArrival;

    @Column(name = "departure_airport", length = 3, nullable = false)
    private Character departureAirport;

    @Column(name = "arrival_airport", length = 3, nullable = false)
    private Character arrivalAirport;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "aircraft_code", length = 3, nullable = false)
    private Character aircraftCode;

    @Column(name = "actual_departure")
    private OffsetDateTime actualDeparture;

    @Column(name = "actual_arrival")
    private OffsetDateTime actualArrival;

}
