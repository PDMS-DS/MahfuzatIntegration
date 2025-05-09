package com.dataserve.mahfuzatintegration.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.dataserve.mahfuzatintegration.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    @Value("${jwt.token.validity}")
    private Long jwtTokenValidity;
    @Value("${jwt.secret}")
    private String secretKey;


    public String getUsernameFromToken(String token) {
        String claimFromToken = getClaimFromToken(token, Claims::getSubject);
        String id = getClaimFromToken(token, Claims::getId);
        return claimFromToken;
    }

    public UserDTO getUsernameAndPasswordFromToken(String token) {
        UserDTO userDTO = new UserDTO();
        token = token.substring(7);
        userDTO.setUserNameLdap(getClaimFromToken(token, Claims::getSubject));
        userDTO.setPassword(getClaimFromToken(token, Claims::getId));
        return userDTO;
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails, String password) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername(), password);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String password) {
        Date expirationDate = new Date(new Date().getTime() + jwtTokenValidity);
        return Jwts.builder().setClaims(claims).setSubject(subject).setId(password).setIssuedAt(new Date())
                .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
