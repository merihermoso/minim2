package edu.upc.dsa.Minim2meriHermoso.services;

import java.util.List;

import edu.upc.dsa.Minim2meriHermoso.models.ReposResponse;
import edu.upc.dsa.Minim2meriHermoso.models.UserResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

                                    //serveis definits al api rest de git
  //  @GET("service/getUser")
  //  Call<List<UserResponse>> getProfile();

    @POST("user/register")
    Call<ResponseBody> postProfile(
            @Body UserResponse ProfileResponse
    );

    @GET("users/{username}")
    Call<UserResponse> getUser(
            @Path("username") String username
    );

    @GET("users/{username}/repos")
    Call<List<ReposResponse>> getUserRepo(
            @Path("username") String username
    );



}
