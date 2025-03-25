package rediscache.russiaairline.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import rediscache.russiaairline.entity.Booking;
import rediscache.russiaairline.entity.Flight;
import rediscache.russiaairline.repository.BookingRepository;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public void addBookingToQueue(Booking booking) {
        redisTemplate.opsForList().leftPush("booking_queue", booking.getBookRef());
    }

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public String processBookingFromQueue() {
        try {
            // Lấy bookRef từ queue
            Object cached = redisTemplate.opsForList().rightPop("booking_queue");
            if (cached == null) {
                System.out.println("No booking found in queue 'booking_queue'");
                return "No booking to process";
            }

            // Ép kiểu sang String vì dữ liệu trong queue là bookRef (String)
            String bookRef = (String) cached;
            return "Processed booking: " + bookRef;

        } catch (ClassCastException e) {
            System.err.println("Error casting queue data to String: " + e.getMessage());
            return "Error: Invalid data in queue";
        } catch (Exception e) {
            System.err.println("Error processing booking from queue: " + e.getMessage());
            return "Error processing booking: " + e.getMessage();
        }
    }
}
