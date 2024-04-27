package com.dms.springqrcodeapi.controllers;

import com.dms.springqrcodeapi.dto.ChangePasswordRequest;
import com.dms.springqrcodeapi.entities.User;
import com.dms.springqrcodeapi.services.UserService;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @PatchMapping("/changePassword")
  public ResponseEntity<?> changePassword(
    @RequestBody ChangePasswordRequest request,
    Principal connectedUser
  ) {
    try {
      service.changePassword(request, connectedUser);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping("/users")
  public ResponseEntity<List<User>> getAllUsers() {
    try {
      List<User> users = service.getAllUsers();
      return ResponseEntity.ok(users);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @DeleteMapping("/user")
  public ResponseEntity<?> deleteUserById(@RequestParam("id") Integer userId) {
    try {
      service.deleteUserById(userId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
