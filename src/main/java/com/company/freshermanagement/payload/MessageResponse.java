package com.company.freshermanagement.payload;

public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    // Getter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
