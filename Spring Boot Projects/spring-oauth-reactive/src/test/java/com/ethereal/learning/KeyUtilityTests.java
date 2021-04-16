package com.ethereal.learning;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import com.ethereal.learning.utility.KeyUtility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class KeyUtilityTests {

    @Test
    public void testWithRSA() throws ParseException {
        String jwt = Jwts.builder()
                .setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2030"))
                .signWith(SignatureAlgorithm.RS256, KeyUtility.privateKey()).compact();
        System.out.println(jwt);

        String parsedJwt = Jwts.parser()
                .setSigningKey(KeyUtility.publicKey())
                .parse(jwt).toString();
        System.out.println(parsedJwt);
    }

    @Test
    public void testWithHMAC() throws ParseException {
        String jwt = Jwts.builder()
                .setExpiration(new SimpleDateFormat("dd/MM/yyyy").parse("23/10/2030"))
                .signWith(SignatureAlgorithm.HS256, KeyUtility.getSigningKey()).compact();
        System.out.println(jwt);
        String parsedJwt = Jwts.parser()
                .setSigningKey(KeyUtility.getSigningKey())
                .parse(jwt).toString();
        System.out.println(parsedJwt);
    }
}
