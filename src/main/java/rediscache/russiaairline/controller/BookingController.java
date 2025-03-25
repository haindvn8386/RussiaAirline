package rediscache.russiaairline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rediscache.russiaairline.entity.Booking;
import rediscache.russiaairline.repository.BookingRepository;
import rediscache.russiaairline.service.BookingService;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String addBooking(@RequestBody Booking booking) {
        bookingService.saveBooking(booking);
        bookingService.addBookingToQueue(booking);

        return "Added booking to queue: " + booking.getBookRef();
    }

    @PostMapping("/process")
    public String processBooking() {
        return bookingService.processBookingFromQueue();
    }
}
