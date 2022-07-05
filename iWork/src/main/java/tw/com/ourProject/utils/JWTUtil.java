package tw.com.ourProject.utils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JWTUtil {
	
	@Autowired
    private OnlineCounterUtil onlineCounterUtil;

	private static final long EXPIRATION_TIME = 60 * 60 * 1000;
    /**
     * JWT SECRET KEY
     */
    private static final String SECRET = "i like to work hard";

    /**
     * 簽發JWT
     */
    public String generateToken(HashMap<String, String> userDetails) {
    	long nowMillis = System.currentTimeMillis();
    	Date now = new Date(nowMillis);

    	
        Map<String, Object> claims = new HashMap<>();
        claims.put( "empId", userDetails.get("empId") );
//        claims.put( "passwd", userDetails.get("passwd") );
        claims.put( "adm", userDetails.get("adm") );

        return Jwts.builder()
                .setClaims( claims )
                .setIssuedAt(now)
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();
    }

    /**
     * 驗證JWT
     */
    public void validateToken(String token) throws AuthException {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature.");
        }
        catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        }
        catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        }
        catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        }
        catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
    }
    
    public String getInfoFromJwtToken(String token,String param) {
    	onlineCounterUtil.insertToken(token);
        return Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody().get(param).toString();
    	
    }
    
  public static Claims getClaimsFromJwtToken(String token) {
    	
        return Jwts.parser()
                            .setSigningKey(SECRET)
                            .parseClaimsJws(token)
                            .getBody();
    	
    }
    
	
}
