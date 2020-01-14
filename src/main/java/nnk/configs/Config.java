package nnk.configs;

public interface Config {

    //Web Security Config
    String FormLoginPage = "/login";
    String UsernameParameter = "email";
    String PasswordParameter = "password";
    String AccessDeniedPage = "/error/403";
    String AuthEntryPoint = "/users/login";

}
