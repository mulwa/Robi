package Modes.RequestPojo;

public class LocationRequest {
    private String username;
    private String api_key;
    private String action;

    public LocationRequest(String username, String api_key, String action) {
        this.username = username;
        this.api_key = api_key;
        this.action = action;
    }
}
