package com.employeetracker.Model.LocationUpload;
import com.google.gson.annotations.SerializedName;
public class LocationResponse {
    @SerializedName("message")
    private String message;

    public LocationResponse(String message) {
        this.message = message;
    }
    public LocationResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
