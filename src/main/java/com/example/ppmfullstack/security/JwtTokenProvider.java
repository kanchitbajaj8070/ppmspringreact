package com.example.ppmfullstack.security;

import com.example.ppmfullstack.domain.User;
import com.example.ppmfullstack.exceptions.ProjectNotFoundException;
import io.jsonwebtoken.*;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.ppmfullstack.security.SecurityConstants.EXPIRATION_TIME;
import static com.example.ppmfullstack.security.SecurityConstants.SECRET;
@Component
public class JwtTokenProvider {
//generate the token

    public String generateToken( Authentication authentication)
    {
        User user=(User) authentication.getPrincipal();
        Date now=new Date(System.currentTimeMillis());

        Date expiryDate= new Date(now.getTime()+EXPIRATION_TIME);
        // have to change it
        String userId=Long.toString(user.getId());
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",Long.toString(user.getId()));
        claims.put("username",user.getUsername());
        claims.put( "fullName",user.getFullName());
        return Jwts.builder().setSubject(userId).setClaims(claims).
                setIssuedAt(now).setExpiration(expiryDate).
                signWith(SignatureAlgorithm.HS512,SECRET).compact();

    }
    //validate the token
    public boolean validateToken( String token)
    {
        try
        {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return true;
        }
        catch (SignatureException e)
        {
            System.out.println("Invalid JWT Signature ");
        }
        catch( ExpiredJwtException e)
        {
            System.out.println("Expired JWT token");
        }
        catch( UnsupportedJwtException ex)
        {
            System.out.println("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("JWT claims string is empty");
        }
        return false;

    }
    public Long getUserIdFromJWT( String token)
    {
        Claims claims=Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id=(String) claims.get("id");
        return Long.parseLong(id);
    }
    // get user id from token
}
