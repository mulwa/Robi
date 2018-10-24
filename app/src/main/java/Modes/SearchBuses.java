package Modes;

import com.google.gson.annotations.SerializedName;

public class SearchBuses {
    @SerializedName("bus")
    private Bus[] bus;
    @SerializedName("response_message")
    private String response_message;
    @SerializedName("response_code")
    private String response_code;

    public Bus[] getBus (){
        return bus;
    }

    public void setBus (Bus[] bus){
        this.bus = bus;
    }

    public String getResponse_message(){
        return response_message;
    }

    public void setResponse_message (String response_message){
        this.response_message = response_message;
    }

    public String getResponse_code (){
        return response_code;
    }

    public void setResponse_code (String response_code){
        this.response_code = response_code;
    }

    @Override
    public String toString(){
        return "ClassPojo [bus = "+bus+", response_message = "+response_message+", response_code = "+response_code+"]";
    }
}
