package com.theironyard.controllers;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.theironyard.entities.Community;
import com.theironyard.entities.Post;
import com.theironyard.entities.User;
import com.theironyard.services.CommunityRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

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

    //might be necessary for saving csv file and working with write and save file below
    static User user;

    @PostConstruct
    public void init() throws InvalidKeySpecException, NoSuchAlgorithmException {
        if (users.count() == 0) {
            User kate = new User();
            kate.firstName = "Kate";
            kate.lastName = "Wilson";
            kate.username = "wilsonkate";
            kate.password = PasswordHash.createHash("1234");
            kate.email = "wilsonkate.kw@gmail.com";
            kate.isAdmin = true;
            users.save(kate);
        }
    }

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
            if (email.equals("wilsonkate.kw@gmail.com") || email.equals("jessica.huffstutler@gmail.com" ) || email.equals("info@betrapp.co")) {
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
    public void addPost(HttpSession session, String communityName, String postName, String postBody, LocalDateTime postTime) throws Exception {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Post post = new Post();
        post.communityName = communityName; //this should be a dropdown menu for the admin to select a community to avoid spelling errors
        post.postName = postName;
        post.postBody = postBody;
        post.postTime = postTime;
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

    @RequestMapping(path = "/community",method = RequestMethod.POST)
    public void addCommunity(HttpSession session, String name, int numberOfPeople, int goal) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Community community = new Community();
        community.name = name;
        community.numberOfPeople = numberOfPeople;
        community.goal = goal;
        communities.save(community);
    }
    
    @RequestMapping(path = "/community", method = RequestMethod.DELETE)
    public void deleteCommunity(HttpSession session, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Community community = communities.findOne(id);
        communities.delete(community);
    }

    @RequestMapping(path = "/community", method = RequestMethod.PUT)
    public void updateCommunity(HttpSession session, String name, int numberOfPeople, int goal, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        Community community = communities.findOne(id);
        community.name = name;
        community.numberOfPeople = numberOfPeople;
        community.goal = goal;
        communities.save(community);
    }

    static void writeFile(String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e) {

        }
    }

    static void saveUser() {
        File f = new File("save.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.serialize(user);

        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {
            System.out.println("Save not successful.");
        }
    }

    @RequestMapping(path = "/userInformation", method = RequestMethod.GET)
    public void getUserInformation(HttpSession session, String firstName, String lastName, String email) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            throw new Exception("You are not logged in.");
        }

        LocalDateTime fileTime = LocalDateTime.now();
        String newFileName = String.format("%s_userInformation.csv", fileTime);

        String contents = String.format("%s %s %s", firstName, lastName, email);
        writeFile(newFileName, contents);
    }


}
