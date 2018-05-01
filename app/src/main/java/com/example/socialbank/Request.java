package com.example.socialbank;

class Request {
    public String firstName;
    public String lastName;
    public String address;
    public String email;
    public String details;
    public String category;
    public String phone_number;

    public Request(){}

    public Request(String fn, String ln, String add, String mail, String deta, String cate, String tel){
        firstName = fn;
        lastName = ln;
        address = add;
        email = mail;
        details = deta;
        category = cate;
        phone_number = tel;
    }
}