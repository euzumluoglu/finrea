package de.reach.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListModificationResponse {


    private Boolean operationStatus;

    private String ip;

    private String message;

    public Boolean getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(Boolean operationStatus) {
        this.operationStatus = operationStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}