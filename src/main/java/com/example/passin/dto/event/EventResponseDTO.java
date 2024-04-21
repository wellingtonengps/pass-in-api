package com.example.passin.dto.event;

import com.example.passin.domain.events.Event;
import lombok.Getter;

@Getter
public class EventResponseDTO {
    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}
