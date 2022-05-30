package com.employeetracker.Repository.RemoteServer.WebService;
import com.employeetracker.Model.LocationUpload.LocationRequest;
import com.employeetracker.Model.LocationUpload.LocationResponse;
import com.employeetracker.Model.Login.LoginRequest;
import com.employeetracker.Model.Login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface  ApiService {
    //Login Api
    @POST("authentication_user/Login.php")
    Call<LoginResponse> getLoginResponse(
            @Body LoginRequest loginRequest
            );
    @POST("latitude_langitude_Data/latitude_langitude.php")
    Call<LocationResponse> getUploadResponse(
            @Body LocationRequest locationRequest
    );
}
