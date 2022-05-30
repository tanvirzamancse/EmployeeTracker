package com.employeetracker.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.employeetracker.Model.Login.LoginRequest;
import com.employeetracker.Model.Login.LoginResponse;
import com.employeetracker.R;
import com.employeetracker.Repository.RemoteServer.WebService.ApiService;
import com.employeetracker.Repository.RemoteServer.WebService.RetrofitClient;
import com.employeetracker.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {

    private ApiService LoginApiService;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);

        LoginApiService = RetrofitClient.getLoginretrofit().create(ApiService.class);

        binding.userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Login();
            }
        });

    }
    private void Login() {
        String email=binding.userEmail.getText().toString().trim();
        String password=binding.userPassword.getText().toString().trim();

        Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
        binding.userEmail.setText("");
        binding.userPassword.setText("");
        finish();

//        if(email.isEmpty()){
//            Email.setError("Enter Email");
//            Email.requestFocus();
//            return;
//        }
//        if(password.isEmpty()){
//            Password.setError("Enter Password");
//            Password.requestFocus();
//            return;
//        }
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            Email.setError("Enter Valid Email Addres");
//            Email.requestFocus();
//            return;
//        }
//        if (password.length()<8){
//            Password.setError("At least 8 Number");
//            Password.requestFocus();
//        }
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmail(email);
        loginRequest.setPassword(password);
        Call<LoginResponse> userResponseCall = LoginApiService.getLoginResponse(loginRequest);
        userResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();
//                if (loginResponse.getEmail()==email){
//                    Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
//                    intent.putExtra("email",email);
//                    startActivity(intent);
//                    finish();
//                    Password.setText("");
//                    Email.setText("");
//                }else {
//                    Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_LONG).show();
//                    Password.setText("");
//                    Email.setText("");
//                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
            }
        });
    }
}