package com.catalogapp.securityservicejwt.jwt;

import com.catalogapp.securityservicejwt.respository.UserRepository;
import com.catalogapp.securityservicejwt.services.UserDetailsImpl;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${andreea.app.jwtSecret}")
    private String jwtSecret;

    @Autowired
    private UserRepository userRepository;

    @Value("${andreea.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        var user = userRepository.findByUsername(userPrincipal.getUsername()).get();
        var subject = new JwtSubject(userPrincipal.getUsername(),user.getRole());
        return Jwts.builder()
                .setSubject(subject.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public JwtSubject getUserNameFromJwtToken(String token) {
        return new Gson().fromJson(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject(),JwtSubject.class);
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
