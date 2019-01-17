package org.tutu.springsecurity.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.tutu.springsecurity.demo.dto.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cguisheng
 * @className: UserController.java
 * @description: TODO
 * @date 2019/1/12 22:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PutMapping(value = "/{id:\\d+}")
    public User updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult != null) {
            bindingResult.getAllErrors().stream().forEach(result -> {

//                FieldError fieldError = (FieldError) result;
//                String message = fieldError.getField() + ": " + fieldError.getDefaultMessage();
                System.out.println(result.getDefaultMessage());
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult != null) {
            bindingResult.getAllErrors().stream().forEach(result -> System.out.println(result.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam String username, Pageable pageable) {

        System.out.println(username);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping(value = "{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable(value = "id") String id) {
        User user = new User();
        user.setUserName("tom");
        return user;
    }
}
