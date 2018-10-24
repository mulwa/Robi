package Modes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seats {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("seater")
    @Expose
    private String seater;

    @SerializedName("name")
    @Expose
    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSeater ()
    {
        return seater;
    }

    public void setSeater (String seater)
    {
        this.seater = seater;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", seater = "+seater+", name = "+name+"]";
    }
}
