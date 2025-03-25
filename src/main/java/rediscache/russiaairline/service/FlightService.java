package rediscache.russiaairline.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import rediscache.russiaairline.entity.Flight;
import rediscache.russiaairline.repository.FlightRepository;

import java.util.concurrent.TimeUnit;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Flight getFlight(Integer flightId) {
        if (flightId == null) {
            System.err.println("flightId is null");
            return null;
        }

        // Tạo key cho Redis
        String key = "flight" + flightId;
        Flight flight = null;

        // 1. Kiểm tra trong Redis trước
        Object cached = redisTemplate.opsForValue().get(key);
        if (cached != null) {
            try {
                flight = objectMapper.convertValue(cached, Flight.class);
                System.out.println("Retrieved from Redis: " + key);
                return flight;
            } catch (Exception e) {
                System.err.println("Error converting Redis data to Flight for key " + key + ": " + e.getMessage());
            }
        } else {
            System.out.println("No data found in Redis for key: " + key);
        }

        // 2. Lấy từ database nếu không có trong Redis
        try {
            flight = flightRepository.findById(flightId).orElse(null);
            if (flight != null) {
                redisTemplate.opsForValue().set(key, flight, 5, TimeUnit.MINUTES);
                System.out.println("Saved to Redis: " + key);
            } else {
                System.out.println("Flight not found in database for ID: " + flightId);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving flight from database for ID " + flightId + ": " + e.getMessage());
        }

        return flight;
    }

    public void saveFlight(Flight flight) {
        if (flight == null || flight.getFlightId() == null) {
            System.err.println("Flight or flightId is null");
            return;
        }
        try {
            flightRepository.save(flight);
            String key = "flight" + flight.getFlightId();
            redisTemplate.opsForValue().set(key, flight, 5, TimeUnit.MINUTES);
            System.out.println("Saved to Redis: " + key);
        } catch (Exception e) {
            System.err.println("Error saving flight to database or Redis: " + e.getMessage());
        }
    }
}
