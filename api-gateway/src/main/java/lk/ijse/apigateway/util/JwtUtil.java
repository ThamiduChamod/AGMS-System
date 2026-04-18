package lk.ijse.apigateway.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class JwtUtil {
    // මේ Key එක අර Gateway එකේ තියෙන Key එකම වෙන්න ඕනේ!
    private static final String SECRET_KEY = "mySecretKey123456789012345678901234567890";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Token එක කාටද අයිති?
                .setIssuedAt(new Date()) // හදපු වෙලාව
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // පැයකින් Expire වෙනවා
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Security Algorithm එක
                .compact();
    }
}
