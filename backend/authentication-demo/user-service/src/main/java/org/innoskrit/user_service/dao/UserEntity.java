package org.innoskrit.user_service.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensures DB auto-generates ID
    private Long id;

    private String username;
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}