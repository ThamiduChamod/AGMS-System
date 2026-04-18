package lk.ijse.authservice.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // මේ Key එක අනිවාර්යයෙන්ම API Gateway එකෙත් මේ විදිහටම තියෙන්න ඕනේ
    // Key එක අවම වශයෙන් characters 32ක් වත් දිග වෙන්න ඕනේ (Security සඳහා)
    @Value("${jwt.secret}")
    private String secretKey;

    // 1. Token එක Generate කරන Method එක
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // පැය 10ක් වලංගුයි
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Token එක Verify කරන (අත්‍යවශ්‍යයි Gateway එකට)
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Key එක හදාගන්නා ආකාරය
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(java.util.Base64.getEncoder().encodeToString(secretKey.getBytes()));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}