package com.example.Rest;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.EventBook;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
public class EventBookingRestWebClient {
	WebClient webClient;

	@PostConstruct
	public void init() {
		webClient = WebClient
				.builder()
				.baseUrl("http://localhost:9090")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@PostMapping("/bookNow")
	public Mono<String> BookNow(@RequestBody EventBook request) {
		return webClient.post().uri("/bookingShow").retrieve().bodyToMono(String.class);
	}

	@GetMapping("/trackBookings")
	public Flux<EventBook> trackAllBooking() {
		return webClient.get().uri("/getAllBooking").retrieve().bodyToFlux(EventBook.class);
	}

	@GetMapping("/trackBooking/{bookingId}")
	public Mono<EventBook> getBookingById(@PathVariable int bookingId) {
		return webClient.get().uri("/getBooking/" + bookingId).retrieve().bodyToMono(EventBook.class);
	}

	@DeleteMapping("/removeBooking/{bookingId}")
	public Mono<String> cancelBooking(@PathVariable int bookingId) {
		return webClient.delete().uri("/cancelBooking/" + bookingId).retrieve().bodyToMono(String.class);
	}

	@PutMapping("/changeBooking/{bookingId}")
	public Mono<EventBook> updateBooking(@PathVariable int bookingId, @RequestBody EventBook request) {
		return webClient.put().uri("/updateBooking/" + bookingId).retrieve()
				.bodyToMono(EventBook.class);
	}


}
