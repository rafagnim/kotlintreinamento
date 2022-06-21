package com.nadir.apiclientes.security

import com.nadir.apiclientes.exceptions.AuthenticationException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private val expiration : Int? = null

    @Value("\${jwt.secret}")
    private val secret : String? = null

    fun generateToken(id : Long): String {

        return Jwts.builder()
            .setSubject(id.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration!!))
            .signWith(SignatureAlgorithm.HS512,secret!!.toByteArray())
            .compact()
    }

    fun isValidToken(token: String): Boolean {
        val claims = getClaims(token)
        if(claims.subject == null || claims.expiration == null || Date().after(claims.expiration)){
            return false
        }
        return true
    }

    private fun getClaims(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(secret!!.toByteArray()).parseClaimsJws(token).body
        }
        catch (ex : Exception){
            throw AuthenticationException("invalid token", "001")
        }
    }

    fun getSubject(token: String) : String {
        return getClaims(token).subject
    }
}