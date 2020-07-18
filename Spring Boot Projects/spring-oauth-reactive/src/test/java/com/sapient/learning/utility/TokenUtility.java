package com.sapient.learning.utility;

import com.sapient.learning.utility.KeyUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TokenUtility {

    public static String createToken(String username)  {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("user_name", username);
        claims.put("authorities", "ROLE_USER");
        String jwt = null;
        try {
            jwt = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2030"))
                    .signWith(SignatureAlgorithm.RS256, KeyUtility.privateKey()).compact();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jwt;
    }

}
