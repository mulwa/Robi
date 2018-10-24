package Endpoints;

import java.util.List;

import Modes.Location;
import Modes.PhotoModel;
import Modes.Post;
import Modes.RequestPojo.LocationRequest;
import Modes.RequestPojo.getAllBuses;
import Modes.SearchBuses;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EndPointService {

    @GET("/photos")
    Call<List<PhotoModel>> getAllPhotos();


    @Headers( "Content-Type: application/json" )
    @POST("/v3/sandbox/")
    Call<Location> getLocation(@Body LocationRequest locationRequest);

    @Headers( "Content-Type: application/json" )
    @POST("/v3/sandbox/")
    Call<SearchBuses> getBusSchedule(@Body getAllBuses allBuses);

    @POST("/posts")
    @FormUrlEncoded
    Call <Post> savePost(@Field("title") String title,
                          @Field("body") String body,
                          @Field("userId") long userId);


}
