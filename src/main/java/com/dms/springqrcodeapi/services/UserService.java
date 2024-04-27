package com.dms.springqrcodeapi.services;

import com.dms.springqrcodeapi.dto.ChangePasswordRequest;
import com.dms.springqrcodeapi.entities.User;
import com.dms.springqrcodeapi.repositories.UserRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;

  public void changePassword(
    ChangePasswordRequest request,
    Principal connectedUser
  ) {
    var user =
      (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

    if (
      !passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())
    ) {
      throw new IllegalStateException("Wrong password");
    }
    if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
      throw new IllegalStateException("Password are not the same");
    }

    user.setPassword(passwordEncoder.encode(request.getNewPassword()));

    repository.save(user);
  }

  public void deleteUserById(Integer userId) {
    Optional<User> userOptional = repository.findById(userId);
    userOptional.ifPresentOrElse(repository::delete, () -> {
      throw new IllegalArgumentException(
        "User with ID " + userId + " does not exist"
      );
    });
  }

  public List<User> getAllUsers() {
    return repository.findAll();
  }
}
