package ua.tumakha.yuriy.webapp.alchemytec.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuriy Tumakha
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        if (responseStatus != null) {
            httpStatus = responseStatus.value();
        }
        return handleExceptionInternal(ex, getJsonBody(ex), new HttpHeaders(), httpStatus, request);
    }

    private String getJsonBody(Exception ex) {
        String json;
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("exception", ex.getClass().getSimpleName());
        jsonData.put("message", ex.getMessage());
        try {
            json = new ObjectMapper().writeValueAsString(jsonData);
        } catch (JsonProcessingException jsonException) {
            json = String.format("{\"exception\":\"%s\",\"message\":\"%s\"}",
                    ex.getClass().getSimpleName(), ex.getMessage());
        }
        return json;
    }

}