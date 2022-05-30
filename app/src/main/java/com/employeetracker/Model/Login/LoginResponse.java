package com.employeetracker.Model.Login;
import com.google.gson.annotations.SerializedName;
public class LoginResponse {
    @SerializedName("User_Status")
    private String User_Status;
    @SerializedName("User_Type")
    private String User_Type;
    @SerializedName("email")
    private String email;
    @SerializedName("fname")
    private String fname;
    @SerializedName("img")
    private String img;
    @SerializedName("lname")
    private String lname;
    @SerializedName("password")
    private String password;
    @SerializedName("status")
    private String status;
    @SerializedName("unique_id")
    private String unique_id;
    @SerializedName("user_id")
    private String user_id;

    public LoginResponse(String user_Status, String user_Type,
                         String email, String fname, String img,
                         String lname, String password, String status,
                         String unique_id, String user_id) {
        User_Status = user_Status;
        User_Type = user_Type;
        this.email = email;
        this.fname = fname;
        this.img = img;
        this.lname = lname;
        this.password = password;
        this.status = status;
        this.unique_id = unique_id;
        this.user_id = user_id;
    }

    public LoginResponse() {
    }

    public String getUser_Status() {
        return User_Status;
    }

    public void setUser_Status(String user_Status) {
        User_Status = user_Status;
    }

    public String getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(String user_Type) {
        User_Type = user_Type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
