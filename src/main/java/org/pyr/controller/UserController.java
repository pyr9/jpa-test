package org.pyr.controller;

import org.pyr.entity.User;
import org.pyr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/cus/hello")
    String hello() {
        List<User> Users = new ArrayList<>();
        for (int i = 171; i < 180; i++) {
            User User = new User();
            User.setCustId((long) i);
            User.setCreatedAt(new Date());
            User.setCustName("lisi" + i);
            User.setAddress("中国" + i);
            Users.add(User);
        }
        userRepository.saveAll(Users);
        return "Hello World";
    }
}
