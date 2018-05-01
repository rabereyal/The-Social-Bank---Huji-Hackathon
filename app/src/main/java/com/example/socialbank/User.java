package com.example.socialbank;


class User {

    public String firstName;
    public String lastName;
    public String bisName;
    public String address;
    public String email;
    public String phone;
    public String password;
    public String category;
    public boolean isAdmin;
    public User(){

    }

    public User(String first, String last,String bussinessName, String adrs, String mail, String psw, String selectedCategory, String phoneNum)
    {
        firstName = first;
        lastName = last;
        bisName = bussinessName;
        address = adrs;
        email = mail;
        password= psw;
        category = selectedCategory;
        phone = phoneNum;
        isAdmin = false;
    }
}
