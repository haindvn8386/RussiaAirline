package rediscache.russiaairline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rediscache.russiaairline.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
