package br.com.verex.avaliacao.error;

import br.com.verex.avaliacao.entity.DomainException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

    private final MessageSource env;
    private final Locale locale = Locale.getDefault();


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DomainException.class)
    @ResponseBody
    public Map<String, String> handleDomainException(HttpServletRequest req, DomainException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", env.getMessage(ex.getMessage(), null, ex.getMessage(), locale));
        return response;
    }
}
