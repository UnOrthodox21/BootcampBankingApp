package com.bootcampTeam4.bootcampBankingApp.classes;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/home")
    public String home(){
    return ("<h1>Welcome</h1>");
}

    @GetMapping("/home/users")
    public String user(){
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/home/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }



}
