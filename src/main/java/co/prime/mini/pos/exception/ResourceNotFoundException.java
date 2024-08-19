package co.prime.mini.pos.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{
    public ResourceNotFoundException(String resourseName, Long id){
        super(HttpStatus.NOT_FOUND,
                String.format("%s with id =%d not found",resourseName,id));
    }
}
