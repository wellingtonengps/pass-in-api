package com.example.passin.services;

import com.example.passin.domain.attendee.Attendee;
import com.example.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.example.passin.domain.chekin.CheckIn;
import com.example.passin.dto.attendee.AttendeesDetails;
import com.example.passin.dto.attendee.AttendeesListResponseDTO;
import com.example.passin.repositories.AttendeeRepository;
import com.example.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeesDetails> attendeesDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());

            LocalDateTime checkInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);

            return new AttendeesDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt() , checkInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeesDetailsList);
    }

    public void verifyAttendeeSubscription(String email, String eventId){
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);

        if(isAttendeeRegistered.isPresent()){
            throw new AttendeeAlreadyExistException("Attendee already registered");
        }
    }

    public Attendee registerAttendee(Attendee attendee){
        return this.attendeeRepository.save(attendee);
    }
}
