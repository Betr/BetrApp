package com.theironyard.controllers;

import com.theironyard.entities.Community;
import com.theironyard.entities.Post;
import com.theironyard.entities.User;
import com.theironyard.services.CommunityRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            if (email.equals("wilsonkate.kw@gmail.com") || email.equals("jessica.huffstutler@gmail.com")) {
                isAdmin = true;
            } else {
                isAdmin = false;
            }
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

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void addPost(HttpSession session, String communityName, String postName, String postBody, int id) throws Exception {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Post post = new Post();
        post.communityName = communityName; //this should be a dropdown menu for the admin to select a community to avoid spelling errors
        post.postName = postName;
        post.postBody = postBody;
        posts.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.PUT)
    public void editPost(HttpSession session, String communityName, String postName, String postBody, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Post post = posts.findOne(id);
        post.communityName = communityName; //this should be a dropdown menu for the admin to select a community to avoid spelling errors
        post.postName = postName;
        post.postBody = postBody;
        posts.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.DELETE)
    public void deletePost(HttpSession session, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Post post = posts.findOne(id);
        posts.delete(post);
    }
}
