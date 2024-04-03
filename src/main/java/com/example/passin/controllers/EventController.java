package com.example.passin.controllers;

import com.example.passin.dto.attendee.AttendeesListResponseDTO;
import com.example.passin.dto.event.EventIdDTO;
import com.example.passin.dto.event.EventRequestDTO;
import com.example.passin.dto.event.EventResponseDTO;
import com.example.passin.services.AttendeeService;
import com.example.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){

        EventResponseDTO event = this.eventService.getEventDetail(id);

        return ResponseEntity.ok(event);
    }


    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){

        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id){

        AttendeesListResponseDTO attendeesListResponseDTO = this.attendeeService.getEventsAttendee(id);

        return ResponseEntity.ok(attendeesListResponseDTO);
    }
}
