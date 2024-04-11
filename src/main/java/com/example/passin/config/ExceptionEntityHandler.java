package com.example.passin.config;

import com.example.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import com.example.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import com.example.passin.domain.chekin.exceptions.CheckInAlreadyExistsException;
import com.example.passin.domain.events.exceptions.EventFullException;
import com.example.passin.domain.events.exceptions.EventNotFoundException;
import com.example.passin.dto.general.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity handleEventNotFound(EventNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity handleAttendeeNotFound(AttendeeNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AttendeeAlreadyExistException.class)
    public ResponseEntity handleAttendeeAlreadyExist(AttendeeAlreadyExistException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity handleCheckInAlreadyExist(CheckInAlreadyExistsException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity handleEventFull(EventFullException exception){
        return ResponseEntity.badRequest().body(new ErrorResponseDTO(exception.getMessage()));
    }
}
