package br.com.senac.inovasultech.jwt;

import br.com.senac.inovasultech.entitys.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${spring.security.secret}")
    private String secret;

    @Value("${spring.security.expiration_time}")
    private Long expirationTime;

    private String emissor = "exemplo-logback-api";

    public String gerarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(emissor)
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public String validarToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer(emissor)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now()
                .plusMinutes(expirationTime)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
