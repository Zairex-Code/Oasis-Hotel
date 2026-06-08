package com.oasis_hotel.oasis_hotel.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.expiration}")
    private Long jwtExpiration;

    // ==========================================
    // 1. TOKEN CREATION (The Passport)
    // ==========================================

    public String generateToken(String email , String role){
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("role", role);

        return Jwts.builder()
            .claims(extraClaims)                            // Extra Data Payload
            .subject(email)                                 //Token Owner (usually the email or username)
            .issuedAt(new Date(System.currentTimeMillis())) // Exact creation date
            .expiration(new Date(System.currentTimeMillis()+jwtExpiration)) // Expiration date
            .signWith(getSignInKey(), Jwts.SIG.HS256)       //modern digital signature 0.12.x
            .compact();                                     // Assemble and convert to text (string)

    }

    // ==========================================
    // 2. TOKEN READING (The Scanner)
    // ==========================================

    public String extractUsername (String token){
        // In JWT, the "Subject" is the token owner (in our case, the user's email)
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver ){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                        .verifyWith(getSignInKey()) // 1. Cryptographically verify using the secret key
                        .build()                    // 2. Build the parser/scanner
                        .parseSignedClaims(token)   // 3. Read the signed token (Formerly parseClaimsJws)
                        .getPayload();              // 4. Extract the internal data (Formerly getBody)
    }


    // ==========================================
    // 3. TOKEN VALIDATION (The Inspector)
    // ==========================================
    /**
     * Spring Security manages users under the UserDetails interface.
     * The token is valid if the email matches the DB record and if it hasn't expired.
     */

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return  extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }


    // ==========================================
    // 4. CRYPTOGRAPHIC KEY MANAGEMENT
    // ==========================================
    /**
     * Converts our Base64 string from application.yml into a real
     * cryptographic key that Java's HMAC-SHA algorithms can understand.
     */

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
