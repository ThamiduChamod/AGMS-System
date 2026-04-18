package lk.ijse.apigateway.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    // මේක ඔයාගේ Auth-Service එකේ තියෙන Key එකම වෙන්න ඕනේ!
    private static final String SECRET_KEY = "mySecretKey123456789012345678901234567890";

    public static class Config { }

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String path = exchange.getRequest().getURI().getPath();

            // මේ paths වලට Filter එක වැඩ කරන්නේ නැහැ
            if (path.contains("/api/auth/register") || path.contains("/api/auth/login")) {
                return chain.filter(exchange);
            }

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new RuntimeException("Unauthorized: Missing or invalid token");
            }

            String token = authHeader.substring(7); // "Bearer " ට පස්සේ තියෙන කොටස ගන්නවා

            try {
                // Token එකේ Signature එක Verify කරනවා
                Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY.getBytes())
                        .build()
                        .parseClaimsJws(token);
            } catch (Exception e) {
                throw new RuntimeException("Unauthorized: Invalid token signature");
            }

            return chain.filter(exchange);
        };
    }
}