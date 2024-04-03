package com.example.passin.services;

import com.example.passin.domain.attendee.Attendee;
import com.example.passin.domain.events.Event;
import com.example.passin.domain.events.exceptions.EventNotFoundException;
import com.example.passin.dto.event.EventIdDTO;
import com.example.passin.dto.event.EventRequestDTO;
import com.example.passin.dto.event.EventResponseDTO;
import com.example.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;


    public EventResponseDTO getEventDetail(String eventId){

        Event event = this.eventRepository.findById(eventId).orElseThrow(( ) -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendees = this.attendeeService.getAllAttendeesFromEvent(eventId);

        return new EventResponseDTO(event, attendees.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){

        Event newEvent = new Event();

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalizer = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalizer.replace("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }

}
