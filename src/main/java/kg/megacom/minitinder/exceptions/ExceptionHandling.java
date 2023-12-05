package kg.megacom.minitinder.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    //TODO add response

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> commonExc(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotUniqueLogin.class})
    public ResponseEntity<?> hhh(NotUniqueLogin e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler({TokenValidationEx.class})
    public ResponseEntity<?> tokenExc(TokenValidationEx e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotFoundEntityEx.class})
    public ResponseEntity<?> tokenExc(NotFoundEntityEx e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }



}
