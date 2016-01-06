package com.theironyard.controllers;

import com.braintreegateway.*;
import com.theironyard.TransactionParams;
import com.theironyard.entities.Community;
import com.theironyard.entities.Post;
import com.theironyard.entities.Press;
import com.theironyard.entities.User;
import com.theironyard.services.CommunityRepository;
import com.theironyard.services.PostRepository;
import com.theironyard.services.PressRepository;
import com.theironyard.services.UserRepository;
import com.theironyard.utils.PasswordHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    PressRepository pressPosts;


    private static BraintreeGateway gateway = new BraintreeGateway(
            Environment.SANDBOX,
            "r4pm2vfwphd4pnfh",
            "fkd575nb39thx694",
            "f76bfbc6d5ea0bbfc9caed00077353b3"
    );

    @RequestMapping(path = "/transaction", method = RequestMethod.POST)
    public Object addTransaction(@RequestBody TransactionParams params){
        TransactionRequest request = new TransactionRequest()
                .amount(new BigDecimal(params.amount))
                .paymentMethodNonce("fake-valid-nonce")
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(request);

        return params;

    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public User getUser(HttpSession session) {
        String email = (String) session.getAttribute("email");

        User user = users.findOneByEmail(email);
        return user;
    }

    @RequestMapping(path = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {

        users.save(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.PUT)
    public void editUser(@RequestBody User user) {
        users.save(user);
    }

    @RequestMapping(path = "/user/{id}", method = RequestMethod.DELETE)
    public void deleteUser (HttpSession session, @PathVariable("id") int id) {
        users.delete(id);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(HttpSession session, @RequestBody User user) throws Exception {

        User currentUser = users.findOneByEmail(user.email);
        if (currentUser == null) {
            throw new Exception("User not found.");
        } else if (!PasswordHash.validatePassword(user.password, currentUser.password)) {
            throw new Exception("Wrong password");
        }

        session.setAttribute("email", user.email);
        return user;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
        System.out.println("Successfully logged out");
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public User register(HttpSession session, @RequestBody User user) throws Exception {

        User currentUser = users.findOneByEmail(user.email);
        if (currentUser == null) {
            if (user.email.toLowerCase().equals("wilsonkate.kw@gmail.com") || user.email.equals("jessica.huffstutler@gmail.com") || user.email.equals("info@betrapp.co")) {
                user.isAdmin = true;
                System.out.println("Is Admin");
            } else {
                user.isAdmin = false;
                System.out.println("Is User");
            }
            user.password = PasswordHash.createHash(user.password);
            users.save(user);
        } else {
            throw new Exception("An account with that email already exists.");
        }

        users.save(user);
        session.setAttribute("email", user.email);
        return user;
    }

    @RequestMapping(path = "/press/{id}", method = RequestMethod.GET)
    public Press getPress(HttpSession session, int id) throws Exception {
        return pressPosts.findOne(id);
    }

    @RequestMapping(path = "/press", method = RequestMethod.GET)
    public List<Press> getPress(HttpSession session) throws Exception {
        String email = (String) session.getAttribute("email");

        return (List<Press>) pressPosts.findAll();
    }

    @RequestMapping(path = "/press", method = RequestMethod.POST)
    public void addPress(HttpSession session, @RequestBody Press press) throws Exception {
        String email = (String) session.getAttribute("email");
        pressPosts.save(press);
    }

    @RequestMapping(path = "/press/{id}", method = RequestMethod.PUT)
    public void editPress(HttpSession session, @PathVariable("id") int id) throws Exception {
        String email = (String) session.getAttribute("email");
        Press press = pressPosts.findOne(id);
        pressPosts.save(press);
    }

    @RequestMapping(path = "/press/{id}", method = RequestMethod.DELETE)
    public void deletePress(HttpSession session, @PathVariable("id") int id) throws Exception {
        String email = (String) session.getAttribute("email");

        Press press = pressPosts.findOne(id);
        pressPosts.delete(press);
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public List<Post> getPosts(HttpSession session) throws Exception {
        String email = (String) session.getAttribute("email");

        return (List<Post>) posts.findAll();
    }

    @RequestMapping(path = "/posts", method = RequestMethod.POST)
    public void addPost(HttpSession session, @RequestBody Post post) throws Exception {
        String email = (String) session.getAttribute("email");

        posts.save(post);
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.PUT)
    public void editPost(HttpSession session, @PathVariable("id") int id) throws Exception {
        String email = (String) session.getAttribute("email");

        Post post = posts.findOne(id);
        posts.save(post);
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.DELETE)
    public void deletePost(HttpSession session, @PathVariable("id") int id) throws Exception {
        String email = (String) session.getAttribute("email");

        Post post = posts.findOne(id);
        posts.delete(post);
    }

    @RequestMapping(path = "/community", method = RequestMethod.POST)
       public void addCommunity(HttpSession session, @RequestBody Community community) throws Exception {
        String email = (String) session.getAttribute("email");

        community.amount = BigDecimal.valueOf(0);
        communities.save(community);
    }
    
    @RequestMapping(path = "/community/{id}", method = RequestMethod.DELETE)
    public void deleteCommunity(HttpSession session, @PathVariable("id") int id) throws Exception {
        String email = (String) session.getAttribute("email");

        Community community = communities.findOne(id);
        communities.delete(community);
    }

    @RequestMapping(path = "/community/{id}", method = RequestMethod.PUT)
    public void editCommunity(HttpSession session, @RequestBody Community community) throws Exception {
        String email = (String) session.getAttribute("email");

        communities.save(community);
    }

    @RequestMapping(path = "/communities", method = RequestMethod.GET)
    public List<Community> getCommunities(HttpSession session) throws Exception {
        String email = (String) session.getAttribute("email");

        return (List<Community>) communities.findAll();
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
