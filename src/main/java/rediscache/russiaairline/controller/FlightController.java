package rediscache.russiaairline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import rediscache.russiaairline.entity.Flight;
import rediscache.russiaairline.service.FlightService;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Integer id) {
        return flightService.getFlight(id);
    }

    @PostMapping
    public String saveFlight(@RequestBody Flight flight) {
        flightService.saveFlight(flight);
        return "Save flight: " + flight.getFlightId();
    }
}
