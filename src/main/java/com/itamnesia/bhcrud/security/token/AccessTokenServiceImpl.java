package com.itamnesia.bhcrud.security.token;

import com.itamnesia.bhcrud.model.user.Expert;
import com.itamnesia.bhcrud.model.user.User;
import com.itamnesia.bhcrud.service.TimeService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.FixedClock;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${jwt.access.key}")
    private String secretKey;
    @Value("${jwt.access.expired}")
    private Long validTimePeriod;
    private final TimeService timeService;


    @Override
    public String createToken(User user) {
        var claims = Jwts.claims()
                .setSubject(user.getPhoneNumber());
        claims.put("role", user.getRole().name());
        var currentTime = timeService.now();
        var expirationTime = currentTime.plusSeconds(validTimePeriod);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Override
    public String createToken(Expert expert) {
        var claims = Jwts.claims()
                .setSubject(expert.getPhoneNumber());
        claims.put("role", expert.getRole().name());
        var currentTime = timeService.now();
        var expirationTime = currentTime.plusSeconds(validTimePeriod);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Override
    public String getCredentials(String accessToken) {
        try {
            return Jwts.parser()
                    .setClock(new FixedClock(Date.from(timeService.now().toInstant())))
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException exception) {
            return exception.getClaims().getSubject();
        }
    }

    @Override
    public String getRole(String accessToken) {
        try {
            return Jwts.parser()
                    .setClock(new FixedClock(Date.from(timeService.now().toInstant())))
                    .setSigningKey(secretKey)
                    .parseClaimsJws(accessToken)
                    .getBody()
                    .get("role", String.class);
        } catch (ExpiredJwtException exception) {
            return exception.getClaims().get("role", String.class);
        }
    }

    @Override
    public boolean isValid(String token){
        try {
            var currentTime = timeService.now().toInstant();
            var expirationTime = Jwts.parser()
                    .setClock(new FixedClock(Date.from(timeService.now().toInstant())))
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration()
                    .toInstant();
            return !expirationTime.isBefore(currentTime);
        } catch(JwtException e){
            return false;
        }
    }
}
