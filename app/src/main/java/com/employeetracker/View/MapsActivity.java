package com.employeetracker.View;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;
import com.employeetracker.Model.LocationUpload.LocationRequest;
import com.employeetracker.Model.LocationUpload.LocationResponse;
import com.employeetracker.R;
import com.employeetracker.Repository.RemoteServer.WebService.ApiService;
import com.employeetracker.Repository.RemoteServer.WebService.RetrofitClient;
import com.employeetracker.VewModel.MapActivityViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private static boolean isPermissionGranted=true;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 112;
    private static String Email;
    private ApiService UploadApiService;
    private double Latitude;
    private double Longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapActivityViewModel mapActivityViewModel=new MapActivityViewModel();
        String timeDate=mapActivityViewModel.getTime();
        Toast.makeText(this, "Current Time Date "+timeDate, Toast.LENGTH_SHORT).show();
        getLocationPermission();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap){
        mMap = googleMap;
       getCurrentLocation();
    }
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            isPermissionGranted = true;
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        client = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location==null){
                    return;
                }
                 Latitude=location.getLatitude();
                 Longitude=location.getLongitude();
                Intent intent = getIntent();
                Email = intent.getStringExtra("email");
                UploadApiService = RetrofitClient.getUploadretrofit().create(ApiService.class);
                LocationRequest locationRequest = new LocationRequest(Email, Latitude, Longitude);
                Call<LocationResponse> Response = UploadApiService.getUploadResponse(locationRequest);
                Response.enqueue(new Callback<LocationResponse>() {
                    @Override
                    public void onResponse(Call<LocationResponse> call, retrofit2.Response<LocationResponse> response) {
                        LocationResponse response1 = response.body();
                        Toast.makeText(getApplicationContext(), "Insert" + response1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<LocationResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                    }
                });

                LatLng bangladesh = new LatLng(Latitude, Longitude);
                mMap.addMarker(new MarkerOptions()
                        .position(bangladesh)
                        .title("I am Here")
                        .draggable(true)
                );
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangladesh ,20));
                mMap.getUiSettings().setZoomControlsEnabled(true);
            }
        });
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        isPermissionGranted = false;
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                isPermissionGranted = true;
                getCurrentLocation();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}