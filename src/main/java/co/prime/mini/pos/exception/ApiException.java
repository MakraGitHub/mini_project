package co.prime.mini.pos.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
