package org.innoskrit.user_service.service;

import org.innoskrit.user_service.repository.UserRepository;
import org.innoskrit.user_service.exception.UserNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired private UserRepository userRepo;

    public UserDetails getByUserName(String username) throws UserNotFoundException {
        var user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        return new User(user.getUsername(), user.getPassword(),
                user.getRoles().stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}
