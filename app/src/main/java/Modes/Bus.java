package Modes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Bus implements Serializable {
    @SerializedName("seats_available")
    @Expose
    private String seats_available;
    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("destinations")
    @Expose
    private String[] destinations;
    @SerializedName("arrival_time")
    @Expose
    private String arrival_time;
    @SerializedName("total_seats")
    @Expose
    private String total_seats;

    @SerializedName("price")
    @Expose
    private Price[] price;

    @SerializedName("departure_time")
    @Expose
    private String departure_time;
    @SerializedName("route")
    @Expose
    private String route;
    @SerializedName("seats")
    @Expose
    private Seats[] seats;
    @SerializedName("from")
    @Expose
    private String from;

    public String getSeats_available ()
    {
        return seats_available;
    }

    public void setSeats_available (String seats_available)
    {
        this.seats_available = seats_available;
    }

    public String getTo ()
    {
        return to;
    }

    public void setTo (String to)
    {
        this.to = to;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getDestinations ()
    {
        return destinations;
    }

    public void setDestinations (String[] destinations)
    {
        this.destinations = destinations;
    }

    public String getArrival_time ()
    {
        return arrival_time;
    }

    public void setArrival_time (String arrival_time)
    {
        this.arrival_time = arrival_time;
    }

    public String getTotal_seats ()
    {
        return total_seats;
    }

    public void setTotal_seats (String total_seats)
    {
        this.total_seats = total_seats;
    }

    public Price[] getPrice ()
    {
        return price;
    }

    public void setPrice (Price[] price)
    {
        this.price = price;
    }

    public String getDeparture_time ()
    {
        return departure_time;
    }

    public void setDeparture_time (String departure_time)
    {
        this.departure_time = departure_time;
    }

    public String getRoute ()
    {
        return route;
    }

    public void setRoute (String route)
    {
        this.route = route;
    }

    public Seats[] getSeats ()
    {
        return seats;
    }

    public void setSeats (Seats[] seats)
    {
        this.seats = seats;
    }

    public String getFrom ()
    {
        return from;
    }

    public void setFrom (String from)
    {
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [seats_available = "+seats_available+", to = "+to+", id = "+id+", destinations = "+destinations+", arrival_time = "+arrival_time+", total_seats = "+total_seats+", price = "+price+", departure_time = "+departure_time+", route = "+route+", seats = "+seats+", from = "+from+"]";
    }
}