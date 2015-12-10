package com.theironyard.controllers;

import com.theironyard.entities.Community;
import com.theironyard.entities.Post;
import com.theironyard.entities.User;
import com.theironyard.services.CommunityRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @RequestMapping("/user")
    public User getUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        User user = users.findOneByUsername(username);

        return user;
    }

    @RequestMapping("/login")
    public void login(String username, String password, HttpSession session) throws Exception {

        User user = users.findOneByUsername(username);
        if (user == null) {
            throw new Exception("User not found.");
        } else if (!PasswordHash.validatePassword(password, user.password)) {
            throw new Exception("Wrong password");
        }

        session.setAttribute("username", username);

    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {

        session.getAttribute("username");
        session.invalidate();
    }

    @RequestMapping("/register")
    public void register(String firstName, String lastName, String email, String username, String password, boolean isAdmin, HttpSession session) throws Exception {

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

        users.save(user);
        session.setAttribute("username", username);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void addPost(HttpSession session, @RequestBody Post post) throws Exception {
        String username = (String) session.getAttribute("username");

//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }
//         if (!postImage.getContentType().startsWith("image")){
//             throw new Exception("Only images are allowed!");
//         }
//         File photoFile = File.createTempFile("postImage", postImage.getOriginalFilename(), new File("public"));
//         FileOutputStream fos = new FileOutputStream(photoFile);
//         fos.write(postImage.getBytes()); //to save to a file in the public folder
//
//        Post post = new Post();
//        post.communityName = communityName; //this should be a dropdown menu for the admin to select a community to avoid spelling errors
//        post.postName = postName;
//        post.postBody = postBody;
//        post.postTime = postTime;
//        post.filename = photoFile.getName();
        posts.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.PUT)
    public void editPost(HttpSession session, @RequestBody Post post) throws Exception {
        String username = (String) session.getAttribute("username");

//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }

//        Post post = posts.findOne(id);
//        if (post.communityName!=null){
//                post.communityName = communityName; //this should be a dropdown menu for the admin to select a community to avoid spelling errors
//        }
//        if (post.postName!=null){
//               post.postName = postName;
//        }
//        if (post.postBody!=null){
//                post.postBody = postBody;
//        }
//        if (!postImage.isEmpty()) {
//            if (!postImage.getContentType().startsWith("image")) {
//                throw new Exception("Only images are allowed!");
//            }
//            File photoFile = File.createTempFile("postImage", postImage.getOriginalFilename(), new File("public"));
//            FileOutputStream fos = new FileOutputStream(photoFile);
//            fos.write(postImage.getBytes()); //to save to a file in the public folder
//        }
        posts.save(post);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.DELETE)
    public void deletePost(HttpSession session, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }

        Post post = posts.findOne(id);
        posts.delete(post);
    }

    @RequestMapping(path = "/community", method = RequestMethod.POST)
    public void addCommunity(HttpSession session, @RequestBody Community community) throws Exception {
//        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }

//        if (!image.getContentType().startsWith("image")){
//            throw new Exception("Only images are allowed!");
//        }
//        File photoFile = File.createTempFile("communityImage", communityImage.getOriginalFilename(), new File("public"));
//        FileOutputStream fos = new FileOutputStream(photoFile);
//        fos.write(communityImage.getBytes()); //to save to a file in the public folder
//
//        Community community = new Community();
//        community.name = name;
//        community.population = population;
//        community.goal = goal;
//        community.description = description;
//        community.filename = photoFile.getName();

        communities.save(community);
    }
    
    @RequestMapping(path = "/community", method = RequestMethod.DELETE)
    public void deleteCommunity(HttpSession session, Integer id) throws Exception {
        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }

        Community community = communities.findOne(id);
        communities.delete(community);
    }

    @RequestMapping(path = "/community", method = RequestMethod.PUT)
    public void editCommunity(HttpSession session, @RequestBody Community community) throws Exception {
        String username = (String) session.getAttribute("username");
//        if (username == null) {
//            throw new Exception("You are not logged in.");
//        }
//
//        Community community = communities.findOne(id);  //When updating the previous input will remain.
//        if (community.name!=null){
//            community.name = name;
//        }
//        if (community.population!=0){
//            community.population = population;
//        }
//        if (community.goal!=0) {
//            community.goal = goal;
//        }
//        if (community.description!=null){
//            community.description = description;
//        }
//        if (!communityImage.isEmpty()){
//            if (!communityImage.getContentType().startsWith("image")){
//                throw new Exception("Only images are allowed!");
//            }
//            File photoFile = File.createTempFile("communityImage", communityImage.getOriginalFilename(), new File("public"));
//            FileOutputStream fos = new FileOutputStream(photoFile);
//            fos.write(communityImage.getBytes());
//        }
        communities.save(community);
    }

    @RequestMapping(value = "/userInformation", method = RequestMethod.GET)
    public void generateCsvFile(HttpServletResponse response) throws Exception {
        ArrayList<User> usersList = (ArrayList<User>) users.findAll();

        if(users == null) {
            throw new Exception("There are no users.");
        } else {
            try{
                StringBuilder writer = new StringBuilder();

                writer.append("FirstName");
                writer.append(',');
                writer.append("LastName");
                writer.append(',');
                writer.append("Email");
                writer.append('\n');

                for (User user : usersList) {
                    writer.append(user.firstName);
                    writer.append(',');
                    writer.append(user.lastName);
                    writer.append(',');
                    writer.append(user.email);
                    writer.append('\n');
                }

                response.getOutputStream().write(writer.toString().getBytes());
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", String.format("Attachment: filename = %s_usersList.csv", LocalDateTime.now()));

            } catch(IOException e){
                e.printStackTrace();
            }
        }

    }
}
