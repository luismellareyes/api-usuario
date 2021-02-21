package cl.mella.usuarios.security.jwt;

import cl.mella.usuarios.security.entity.UsuarioPrincipal;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j
public class JwtProvider {
    //private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("token mal formado");
        }catch (UnsupportedJwtException e){
            log.error("token no soportado");
        }catch (ExpiredJwtException e){
            log.error("token expirado");
        }catch (IllegalArgumentException e){
            log.error("token vacío");
        }catch (SignatureException e){
            log.error("fail en la firma");
        }
        return false;
    }
}
