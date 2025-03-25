package rediscache.russiaairline.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "book_ref", length = 6)
    private String bookRef;

    @Column(name = "book_date", nullable = false)
    private OffsetDateTime bookDate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;
}