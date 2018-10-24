package Modes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {
    @SerializedName("response_message")
    @Expose
    private String response_message;
    @SerializedName("cities")
    @Expose
    private Cities [] cities;
    @SerializedName("response_code")
    @Expose
    private String response_code;

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public Cities[] getCities() {
        return cities;
    }

    public void setCities(Cities[] cities) {
        this.cities = cities;
    }

    public String getResponse_code() {
        return response_code;
    }

    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }
}
