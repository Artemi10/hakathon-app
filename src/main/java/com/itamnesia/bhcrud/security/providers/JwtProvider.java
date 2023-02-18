package com.itamnesia.bhcrud.security.providers;

import com.itamnesia.bhcrud.model.user.Role;
import com.itamnesia.bhcrud.repository.ExpertRepository;
import com.itamnesia.bhcrud.repository.UserRepository;
import com.itamnesia.bhcrud.security.JwtAuthenticationException;
import com.itamnesia.bhcrud.security.details.UserPrincipal;
import com.itamnesia.bhcrud.security.token.AccessTokenService;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final ExpertRepository expertRepository;
    private final AccessTokenService accessTokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            if (authentication == null) {
                throw new JwtAuthenticationException();
            }
            var accessToken = authentication.getCredentials().toString();
            if (!accessTokenService.isValid(accessToken)) {
                throw new JwtAuthenticationException();
            }
            var login = accessTokenService.getCredentials(accessToken);
            var role = accessTokenService.getRole(accessToken);

            UserPrincipal principal;
            if (Role.EXPERT.name().equals(role)) {
                principal = expertRepository.findByPhoneNumber(login)
                        .map(UserPrincipal::new)
                        .orElseThrow(() -> new BadCredentialsException("Credentials are invalid"));
            } else {
                principal = userRepository.findByPhoneNumber(login)
                        .map(UserPrincipal::new)
                        .orElseThrow(() -> new BadCredentialsException("Credentials are invalid"));
            }
            var hasAuthority = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(authority -> authority.equals(role));
            if (principal.isEnabled() && hasAuthority) {
                return new UsernamePasswordAuthenticationToken(principal, login, principal.getAuthorities());
            }
            throw new JwtAuthenticationException();
        } catch (JwtException exception) {
            throw new JwtAuthenticationException();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
