package com.itamnesia.bhcrud.security.details;

import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public record UserPrincipal(
        UUID id,
        String credentials,
        String password,
        boolean isEnabled,
        Collection<? extends GrantedAuthority> authorities
) implements UserDetails {

    public UserPrincipal(User user) {
       this(
               user.getId(),
               user.getPhoneNumber(),
               user.getPassword(),
               true,
               Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }

    public UserPrincipal(Expert expert) {
        this(
                expert.getId(),
                expert.getPhoneNumber(),
                expert.getPassword(),
                true,
                Collections.singletonList(new SimpleGrantedAuthority(expert.getRole().name())));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return credentials;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
