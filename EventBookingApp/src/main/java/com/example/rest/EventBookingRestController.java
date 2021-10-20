package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.EventBook;
import com.example.repository.BookMyEventRepository;

@RestController
public class EventBookingRestController {
	@Autowired
	private BookMyEventRepository repository;

	@PostMapping("/bookingShow")
	public String bookShow(@RequestBody EventBook bookRequest) {
		EventBook response = repository.save(bookRequest);
		return "Hi " + response.getUserName() + " your Request for " + response.getShowName() + " on date "
				 + "Booking successfully..";
	}

	@GetMapping("/getAllBooking")
	public List<EventBook> getAllBooking() {
		return repository.findAll();
	}

	@GetMapping("/getBooking/{bookingId}")
	public EventBook getBooking(@PathVariable int bookingId) {
		return repository.findById(bookingId).get();
	}

	@DeleteMapping("/cancelBooking/{bookingId}")
	public String cancelBooking(@PathVariable int bookingId) {
		repository.deleteById(bookingId);
		return "Booking cancelled with bookingId : " + bookingId;
	}

	@PutMapping("/updateBooking/{bookingId}")
	public EventBook updateBooking(@RequestBody EventBook updateBookRequest, @PathVariable int bookingId) {
		EventBook dbResponse = repository.findById(bookingId).get();
		dbResponse.setPrice(updateBookRequest.getPrice());
		dbResponse.setShowName(updateBookRequest.getShowName());
		dbResponse.setUserCount(updateBookRequest.getUserCount());
		repository.saveAndFlush(dbResponse);
		return dbResponse;
	}
}
