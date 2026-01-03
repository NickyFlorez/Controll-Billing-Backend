package com.nicky.controlBilling.infrastructure.driven_adapters.jwt.adapter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nicky.controlBilling.domain.port.authentication.TokenPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtAdapter implements TokenPort {

    @Value("${jwt.secret}")
    private String jwtSecret;


    public String generateToken(String username, String role){
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        return JWT.create()
                .withIssuer("controlBilling/auth0")
                .withSubject(username)
                .withExpiresAt( Instant.now().plus(1, ChronoUnit.HOURS))
                .withIssuedAt(new Date())
                .withClaim("custom:role", role)
                .sign(algorithm);
    }

    public DecodedJWT verifyToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("controlBilling/auth0")
                .build();

        return verifier.verify(token);
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }
}
