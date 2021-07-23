package br.com.verex.avaliacao.controller.shared;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse <T>{

    private T data;

    private String message;

    private Long status;

    private String help;

    private String typeMessage;

    private List<ResponseErrorItem> errors = new ArrayList<>();

    public ServiceResponse(T data) {
        this.data = data;
    }

    public ResponseEntity<ServiceResponse<T>> okResponse(T data) {
        ServiceResponse<T> response = new ServiceResponse<T>();
        response.setData(data);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ServiceResponse<T>> createdResponse(T data) {
        ServiceResponse<T> response = new ServiceResponse<T>();
        response.setData(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<ServiceResponse<T>> okOrNoContent(T data) {
        ServiceResponse<T> response = new ServiceResponse<T>();
        response.setData(data);
        if (data != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<ServiceResponse<T>> okOrNotFound(T data) {
        ServiceResponse<T> response = new ServiceResponse<T>();
        response.setData(data);
        if (data != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ServiceResponse<T>> ok(T data) {
        return ResponseEntity.ok(this);
    }

}
