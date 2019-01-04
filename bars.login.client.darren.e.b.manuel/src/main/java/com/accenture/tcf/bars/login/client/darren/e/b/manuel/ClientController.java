package com.accenture.tcf.bars.login.client.darren.e.b.manuel;

import org.apache.coyote.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class ClientController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String formCredentials = "";
    private String isLoggedOnAdmin="0";

    @ResponseBody
    @GetMapping("/security")
    public String getUsernameAndRole() {
        String usernameAndRole;
        try {
            usernameAndRole = this.formCredentials.substring(0, this.formCredentials.indexOf(":")) + isLoggedOnAdmin;
        } catch (Exception ex) {
            usernameAndRole = "x0";
        }
        return usernameAndRole;
    }

    @GetMapping({"/login"})
    public String showLogin(HttpServletRequest request) {
            return "login";
    }

    @PostMapping("/login-success")
    public String postLogin (@ModelAttribute("username") String username, @ModelAttribute("password") String password, HttpSession session) {
        this.formCredentials = username + ":" + password;
        return "redirect:/";
    }

    @GetMapping({"/"})
    public String showHomePage(@ModelAttribute("username") String username, @ModelAttribute("password") String password, HttpServletRequest request, Model model) {
        if (formCredentials.isEmpty()) {
            request.getSession().setAttribute("cookie", true);
            return "login";
        } else {
            if (invalidCredentials()) {
                formCredentials="";
                return "redirect:/login";
            }
            else {
                request.getSession().setAttribute("cookie", true);
                try {
                    showAllAccountsForAdmin(request);
                    model.addAttribute("admin", true);
                    isLoggedOnAdmin="1";
                } catch(Exception ex) {
                    System.out.println("NOT ADMIN!!!!");
                    int id = getUserId();
                    model.addAttribute("admin", false);
                    model.addAttribute("id", id);
                    isLoggedOnAdmin="0";
                }
                return "home";
            }
        }
    }



    @GetMapping("/logout")
    public String logoutUrl (HttpServletRequest request) {
        this.formCredentials = "";
        request.getSession().setAttribute("formCredentials", "");
        return "login";
    }

    @GetMapping("/users")
        public ModelAndView showAllAccountsForAdmin(HttpServletRequest request) {
        request.getSession().setAttribute("cookie",true);
        ModelAndView modelAndView = new ModelAndView();
            System.out.println("@@@@@@@@@@@@@@@@@@@  " + this.formCredentials);
            if (this.formCredentials.isEmpty()) {
                modelAndView.setViewName("login");
                return modelAndView;}

        HttpHeaders headers = getHeaders();
        HttpEntity<List<User>> requestEntity = new HttpEntity<List<User>>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                "http://localhost:8001/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<User>>() { });
        List<User> userList = responseEntity.getBody();

        modelAndView.addObject("users", userList);
        modelAndView.addObject("admin", this.formCredentials.substring( 0, formCredentials.indexOf(":")));
        modelAndView.setViewName("admin-all-accounts2");
        return modelAndView;
    }

    @GetMapping(value="/users/{id}")
    public ModelAndView showAccount(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        if (this.formCredentials.isEmpty()) {
            modelAndView.setViewName("login");
            return modelAndView;}

        HttpHeaders headers = getHeaders();
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<User> responseEntity
                    = restTemplate.exchange(
                    "http://localhost:8001/user/" + id,
                    HttpMethod.GET,
                    requestEntity, User.class);
            User user = responseEntity.getBody();
            modelAndView.addObject("userObject", user);
            modelAndView.setViewName("edit-account");
            return modelAndView;
    }

    @GetMapping("/users/new")
    public ModelAndView getCreateUserForm() {
        ModelAndView modelAndView = new ModelAndView();
        if (this.formCredentials.isEmpty()) {
            modelAndView.setViewName("login");
            return modelAndView;
        }

        modelAndView.setViewName("create-account");
        return modelAndView;
    }

    @PostMapping("/users/new")
    public ModelAndView postNewUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(
                request.getParameter("username"),
                request.getParameter("role"),
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("password"));

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity
                = restTemplate.exchange(
                "http://localhost:8001/users/new",
                HttpMethod.POST,
                requestEntity, User.class);
        System.out.println(responseEntity.getBody());
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }


    @GetMapping({"/users/{id}/edit"})
    public ModelAndView getEditForm(@PathVariable int id) {
        ModelAndView modelAndView = showAccount(id);
        modelAndView.setViewName("edit-account");
        return modelAndView;
    }

    @PutMapping("/users/{id}/edit")
    public ModelAndView updateUser(HttpServletRequest httpServletRequest, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(httpServletRequest.getParameter("username"), httpServletRequest.getParameter("role"),
                httpServletRequest.getParameter("firstname"), httpServletRequest.getParameter("lastname"),
                httpServletRequest.getParameter("password"));

        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity
                = restTemplate.exchange(
                "http://localhost:8001/user/"+id,
                HttpMethod.PUT,
                requestEntity, User.class);
        System.out.println(responseEntity.getBody());
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }


    @DeleteMapping("/users/delete")
    public ModelAndView deleteUser(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String id = request.getParameter("deleteMe");
        User user = new User(Integer.valueOf(id));
        HttpEntity<User> requestEntity = new HttpEntity<User>(user, headers);
        System.out.println();
        ResponseEntity<User> responseEntity
                = restTemplate.exchange(
                "http://localhost:8001/user/" + id,
                HttpMethod.DELETE,
                requestEntity, User.class);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;

    }

    @ExceptionHandler( {HttpClientErrorException.class, HttpServerErrorException.class})
    @ResponseBody
    @GetMapping("/error")
    public String getErrorPage() {
        return "Sorry, page cannot be displayed. Access is denied.";
    }


    // Basic Auth Encoder
    private HttpHeaders getHeaders() {
        String plainCreds = this.formCredentials;
        String base64Creds = new String(Base64.encodeBase64(plainCreds.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private boolean invalidCredentials() {
        HttpHeaders headers = getHeaders();
        HttpEntity<User> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<User> responseEntity
                    = restTemplate.exchange(
                    "http://localhost:8001/",
                    HttpMethod.GET,
                    requestEntity, User.class);
            int statusCode = responseEntity.getStatusCodeValue();

            System.out.println("STATUS CODE VALUE: " + responseEntity.getStatusCodeValue());
            System.out.println("body VALUE: " + responseEntity.getBody());
        }
        catch (Exception ex) {
            if (ex.getMessage().contains("Unauthorized: 401") ){
                return true;
            } else if (ex.getMessage().contains("HttpMessageNotReadableException")) {
                return false;
            }
        }
        return true;
    }

    private int getUserId() {
        int i=1;
        int id=0;
        while(id==0) {
            try {
                getEditForm(i);
                id=i;
            }
            catch(Exception ex) {  }
            i++;
        }
        return id;
    }


}


//
//  if (responseEntity.getStatusCode() == HttpStatus.OK) {
//          logger.info("response received");
//          } else {
//          logger.info("error occurred");
//          }


//PAGES NEEDED:
//1. LOGIN
//2. FALLBACK
//3. SHOW ACCOUNT (NON-ADMIN)
//4. SHOW ALL ACCOUNTS (ADMIN)
//5. EDIT ACCOUNT
//6. CREATE ACCOUNT (ADMIN)


// @PostMapping("/users/new")
//    public ModelAndView createAccountForAdmin(HttpServletRequest request) {
//        ModelAndView modelAndView = new ModelAndView();
//        User user = new User(5, "james", "USER", "James", "Basical");
//        modelAndView.setViewName("create-account");
//        return modelAndView;
//    }
//
//    @DeleteMapping("/users/{id}") // Delete, Retrieve one
//    public ModelAndView destroyAccount(HttpServletRequest request, @PathVariable int id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("show-account");
//        return modelAndView;
//    }