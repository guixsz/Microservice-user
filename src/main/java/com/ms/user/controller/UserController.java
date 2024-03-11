package com.ms.user.controller;

import com.ms.user.dto.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel =  userService.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }
}
