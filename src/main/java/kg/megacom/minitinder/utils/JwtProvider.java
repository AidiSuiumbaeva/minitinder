package kg.megacom.minitinder.utils;



import io.jsonwebtoken.*;
import kg.megacom.minitinder.exceptions.TokenValidationEx;
import kg.megacom.minitinder.models.enums.Language;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.Date;


@Component
public class JwtProvider {

    public String generateToken(Long personId, int expirationDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, expirationDate);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, "ghfghfhf")
                .claim("id", personId)
                .compact();
    }

    public String generateAccessToken(Long clientId) {
        return generateToken(clientId, 1);
    }

    public String generateRefreshToken(Long clientId) {
        return generateToken(clientId, 3 * 5);
    }

    public Long validateToken(String token, Language language) {

        try {
            Claims claims;
            claims = Jwts.parser().setSigningKey("ghfghfhf").parseClaimsJws(token).getBody();
            if (claims != null) {
                return Long.valueOf(String.valueOf(claims.get("id")));
            } else {
                throw new TokenValidationEx(ResourceBundle.periodMessages("tokenEmpty",language));
            }
        } catch (ExpiredJwtException e) {
            throw new TokenValidationEx("Срок действия токена истек. Просьба авторизоваться");
        } catch (MalformedJwtException e) {
            throw new TokenValidationEx("Токен взломан");
        } catch (ResponseStatusException e) {
            throw new TokenValidationEx("Токен пустой");
        } catch (Exception e) {
            throw new TokenValidationEx("Токен не прошел валидацию");
        }
    }
}
