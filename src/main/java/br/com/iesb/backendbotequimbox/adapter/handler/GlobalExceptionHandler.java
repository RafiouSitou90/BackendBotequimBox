package br.com.iesb.backendbotequimbox.adapter.handler;

import br.com.iesb.backendbotequimbox.domain.exception.auth.AuthBadCredentialsException;
import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelAlreadyExistsException;
import br.com.iesb.backendbotequimbox.domain.exception.user.UserModelNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Clock clock;

    @ExceptionHandler(value = {UserModelNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND, code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException ex, HttpServletRequest request) {
        return new ResponseEntity<>(getErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), null, request), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {
            UserModelAlreadyExistsException.class, AuthBadCredentialsException.class, IllegalStateException.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity
                .badRequest()
                .body(getErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null, request));
    }

    @ExceptionHandler(value = {
            UserModelAlreadyExistsException.class, AuthBadCredentialsException.class, IllegalStateException.class
    })
    @ResponseStatus(value = HttpStatus.FORBIDDEN, code = HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(RuntimeException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                getErrorResponse(HttpStatus.FORBIDDEN, ex.getMessage(), null, request), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(MethodArgumentNotValidException ex,
                                                                            HttpServletRequest request) {
        List<String> details = new ArrayList<>();

        ex.getFieldErrors().forEach(error ->
                details.add(String.format("%s => %s", error.getObjectName(), error.getDefaultMessage())));

        ex.getGlobalErrors().forEach(error ->
                details.add(String.format("%s => %s", error.getObjectName(), error.getDefaultMessage())));

        return ResponseEntity
                .badRequest()
                .body(getErrorResponse(HttpStatus.BAD_REQUEST, "Fields validation errors", details, request));
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerException(RuntimeException ex, HttpServletRequest request) {
        return ResponseEntity
                .internalServerError()
                .body(getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null, request));
    }

    private ErrorResponse getErrorResponse(HttpStatus status, String message,
                                           @Nullable Object details, HttpServletRequest request) {

        return ErrorResponse.builder()
                .withError(status.getReasonPhrase())
                .withStatusCode(status.value())
                .withMessage(message)
                .withPath(request.getRequestURI())
                .withDetails(Objects.requireNonNullElseGet(details, ArrayList::new))
                .withTimestamp(LocalDateTime.now(clock).toString())
                .build();
    }
}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
class ErrorResponse {

    private int statusCode;
    private String error;
    private String message;
    private String path;
    private Object details;
    private String timestamp;
}