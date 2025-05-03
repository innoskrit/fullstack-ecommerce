package org.innoskrit.auth_lib;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserPrincipal extends User {
    private final Long userId;

    public CustomUserPrincipal(Long userId, Collection<? extends GrantedAuthority> authorities) {
        super(userId.toString(), "", authorities);
        this.userId = userId;
    }
}

