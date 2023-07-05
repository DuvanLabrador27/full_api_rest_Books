package com.duvanlabrador.api_rest_full_duvan.Excepcion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResourceNotFoundException extends RuntimeException{
    private String nameOfRecourse;
    private String nameOfField;
    private long valueOfField;


    public ResourceNotFoundException(String nameOfRecourse, String nameOfField, long valueOfField) {
        super(String.format("%s No found whit: %s : '%s'", nameOfRecourse, nameOfField, valueOfField));
        this.nameOfRecourse = nameOfRecourse;
        this.nameOfField = nameOfField;
        this.valueOfField = valueOfField;
    }
}
