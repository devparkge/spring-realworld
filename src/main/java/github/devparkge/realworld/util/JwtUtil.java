package github.devparkge.realworld.util;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;


@Component
public class JwtUtil {
    private final String secretKey;
    private final long expiration;
    private final SecretKey key;


    public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") long expiration) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.key = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UUID uuid) {
        Date date = new Date();
        return Jwts.builder()
                .subject(uuid.toString())
                .issuedAt(date)
                .expiration(getExpiredDate(date))
                .signWith(key)
                .compact();
    }

    private Date getExpiredDate(Date date) {
        return new Date(date.getTime() + expiration);
    }

    public UUID parseToken(String token) {
        return UUID.fromString(
                Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject()
        );
    }
}

