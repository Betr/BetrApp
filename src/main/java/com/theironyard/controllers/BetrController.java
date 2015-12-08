package com.theironyard.controllers;

import com.theironyard.entities.User;
import com.theironyard.services.CommunityRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by jessicahuffstutler on 12/7/15.
 */
@RestController
public class BetrController {

    @Autowired
    UserRepository users;

    @Autowired
    PostRepository posts;

    @Autowired
    CommunityRepository communities;

    @RequestMapping
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        return user;
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session) throws Exception {

        User user = users.findOneByUsername(username);
        if (user == null) {
            throw new Exception("User not found.");
        } else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Wrong password");
        }

        session.setAttribute("username", username);

        return "redirect:/";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {

        session.getAttribute("username");
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(String firstName, String lastName, String email, String username, String password, boolean isAdmin, HttpSession session) throws Exception {

        User user = users.findOneByUsername(username);
        if (user == null) {
            user = new User();
            user.firstName = firstName;
            user.lastName = lastName;
            user.email = email;
            user.username = username;
            user.password = PasswordHash.createHash(password);
            user.isAdmin = isAdmin;
            users.save(user);
        } else {
            throw new Exception("An account with that username already exists.");
        }

        session.setAttribute("username", username);

        return "redirect:/";
    }

    @RequestMapping("/community")
    public void addVillage(HttpSession session, String name, int numberOfPeople, int goal) {

        Commuinty commuinty =
    }
}
