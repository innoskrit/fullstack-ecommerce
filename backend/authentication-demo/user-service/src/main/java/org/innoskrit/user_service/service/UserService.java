package org.innoskrit.user_service.service;

import org.innoskrit.auth_lib.exception.ClientException;
import org.innoskrit.auth_lib.exception.NotFoundException;
import org.innoskrit.user_service.dao.UserEntity;
import org.innoskrit.user_service.dto.CreateUserRequest;
import org.innoskrit.user_service.dto.UserDto;
import org.innoskrit.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserDto getByEmail(String email) throws NotFoundException {
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("user not found with email: " + email));
        return convertToUserDto(user);
    }

    public UserDto insertUser(CreateUserRequest request) throws ClientException {
        var user = userRepo.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new ClientException("user already exists with email: " + request.getEmail());
        }
        UserEntity newUser = UserEntity.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                // TODO: Encode password
                .password(request.getPassword())
                // TODO: Create role enum in auth-lib
                .roles(List.of("ROLE_ADMIN"))
                .build();
        UserEntity userEntity = userRepo.save(newUser);
        System.out.println("user created! " + userEntity);

        return convertToUserDto(userEntity);
    }

    public UserDto getById(Long id) {
        UserEntity user = userRepo.findById(id).orElseThrow(() ->
                new NotFoundException("user not found with id: " + id));
        return convertToUserDto(user);
    }

    private UserDto convertToUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles()).build();
    }
}
