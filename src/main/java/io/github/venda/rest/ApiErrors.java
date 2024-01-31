package io.github.venda.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ApiErrors {

    @Getter
    private List<String> errorMessages;

    @Getter
    private Map<String, String> fieldErrors;

    public ApiErrors(String errorMessage) {
        this.errorMessages = Arrays.asList(errorMessage);
    }

    public ApiErrors(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ApiErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

}
