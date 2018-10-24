package Modes.RequestPojo;

public class getAllBuses {
    private String username;
    private String api_key;
    private String action;
    private String travel_from;
    private String travel_to;
    private String travel_date;
    private String hash;

    public getAllBuses(String username, String api_key, String action, String travel_from, String travel_to, String travel_date, String hash) {
        this.username = username;
        this.api_key = api_key;
        this.action = action;
        this.travel_from = travel_from;
        this.travel_to = travel_to;
        this.travel_date = travel_date;
        this.hash = hash;
    }
}
