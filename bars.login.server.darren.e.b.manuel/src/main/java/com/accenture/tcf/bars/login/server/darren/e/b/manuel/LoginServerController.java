package com.accenture.tcf.bars.login.server.darren.e.b.manuel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;
import java.util.Base64;
import java.util.List;

@RestController
public class LoginServerController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    @ResponseBody
    public String greeting() {
        return "Hello, World!";
    }


    @GetMapping ("/users")
    public List<User> getUsersByAdmin() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping( value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id, Principal principal) {
        User user = userRepository.findByUserId(id);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& user/id");
        if (principal.getName().equals(user.getUserName()) || isPrincipalAdmin(principal))
            return new ResponseEntity<User>(user, HttpStatus.OK);
        else
            return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/users/new")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        System.out.println("XXXXXXXXXXXXXXXXXXXX " + id);
        userRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody User user) {
        User currentUser = userRepository.findByUserId(id);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setPassword(user.getPassword());
        currentUser.setRole(user.getRole());
        currentUser.setUserName(user.getUserName());
        currentUser.setUserId(id);
        userRepository.save(currentUser);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    private boolean isPrincipalAdmin(Principal principal) {
        User user = userRepository.findByUserName(principal.getName());
        if(user.getRole().equals("ADMIN"))
            return true;
        else
            return false;
    }
}
//    @PostMapping("/logout")
//    public void logoutUser() {
//
//    }
//    @RequestMapping(value="/logout", method = RequestMethod.POST)
//    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return "/redirect:/";
//    }



// Return login information to client
// darren.e.b.manuel
