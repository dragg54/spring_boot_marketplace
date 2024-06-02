package ecommerce.services;

import ecommerce.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Data
@Service
@RequiredArgsConstructor
public class AuthService{
    private final String SECRET_KEY = "/NDRrJ/jzaBe2CoCwW877FSrFs6XdKVjp12JXeqIkhkIzinEhvn8v+aSczkELnegQWWZdZY3/kKE2ZUZiX1Q/eYU82FWm9EHPycZijuOtr0qRRKiMltnH3jYVqMdttnxE/P5/stfSsu5lqUn4QP6U9bJt+TEKDjYbi54urfqJDHySV8NMghntj9PL7GvmMU1+rbxRhj28brrwe916LUgiLCRjwzpu9MSpKLIoORBmCit1RNUe+8ZhdIv3AjzSYT3OV9zwIFJxd+V8+FikrjAPcLtOQWuCC5d//Z1Sfnmp6s8v0Y+XXY1+FjZL0RXHr8mqhbB0SrFMh12qbDS3GHrzws/rz6H0Vjn0B/EAYFLDKk=\n";
    public String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUserName(token);
        return (username.equals(user.getUsername()));
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token)
                .before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
}