package com.example.passin.services;

import com.example.passin.domain.attendee.Attendee;
import com.example.passin.domain.chekin.CheckIn;
import com.example.passin.domain.chekin.exceptions.CheckInAlreadyExistsException;
import com.example.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee){

        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkinRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckedIn = this.checkinRepository.findByAttendeeId(attendeeId);

        if(isCheckedIn.isPresent()){
            throw new CheckInAlreadyExistsException("Attendee already checked in");
        }
    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}
