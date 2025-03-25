package rediscache.russiaairline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rediscache.russiaairline.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer>{
}
