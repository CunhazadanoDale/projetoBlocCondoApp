package com.condoapp.bloc.auth.service;

import com.condoapp.bloc.auth.entity.Conta;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms}")
    private Long expiration;


    public String gerarToken(Conta conta) {
        return Jwts.builder()
                .header().add("typ", "jwt")
                .and()
                .subject(conta.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(transformarJwtEmSecret())
                .compact();
    }

    public String extrairEmail(String token) {
        return Jwts.parser().verifyWith(transformarJwtEmSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValido(String token, UserDetails userDetails) {
        return extrairEmail(token).equals(userDetails.getUsername()) && !isTokenExpirado(token);
    }

    private boolean isTokenExpirado(String token) {
        return extrairTempoDeExpiracao(token).before(new Date());
    }

    private Date extrairTempoDeExpiracao(String token) {
        return Jwts.parser().verifyWith(transformarJwtEmSecret())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }

    private SecretKey transformarJwtEmSecret() {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        return secretKey;
    }
}
