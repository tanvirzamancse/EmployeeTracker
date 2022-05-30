package com.employeetracker.Repository.RemoteServer.WebService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitClient {
    private static final String BASE_URL = "https://apoerp.apodigi.com/apo_ERP/";
    private static final String UPLOAD_BASE_URL = "https://apoerp.apodigi.com/apo_ERP/";
    private static Retrofit Loginretrofit;
    private static Retrofit Uploadretrofit;
    public static Retrofit getLoginretrofit(){
        if (Loginretrofit == null){
            Loginretrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return Loginretrofit;
    }

    public static Retrofit getUploadretrofit(){
        if (Uploadretrofit == null){
            Uploadretrofit = new Retrofit.Builder()
                    .baseUrl(UPLOAD_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return Uploadretrofit;
    }

}

