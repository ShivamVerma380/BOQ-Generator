package com.mahavir.boq.helper;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
    

    public String message;

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
