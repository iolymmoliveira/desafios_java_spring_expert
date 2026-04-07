package com.iolyoliveira.DSCatalog.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String FieldName, String Message) {
        this.errors.add(new FieldMessage(FieldName, Message));
    }
}
