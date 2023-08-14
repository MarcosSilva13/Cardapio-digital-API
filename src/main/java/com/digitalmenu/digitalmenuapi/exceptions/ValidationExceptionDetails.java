package com.digitalmenu.digitalmenuapi.exceptions;

public class ValidationExceptionDetails extends ExceptionDetails {
    private String fields;

    private String fieldsMessage;

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getFieldsMessage() {
        return fieldsMessage;
    }

    public void setFieldsMessage(String fieldsMessage) {
        this.fieldsMessage = fieldsMessage;
    }
}
