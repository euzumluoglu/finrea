package de.reach.api.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlackListModificationResponse {

    @JsonProperty("operation_status")
    private Boolean operationStatus;

    @JsonProperty("ip")
    private String ip;

    @JsonProperty("message")
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

    @Override
    public String toString() {
        return "BlackListModificationResponse{" +
                "operationStatus=" + operationStatus +
                ", ip='" + ip + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}