package com.rianwnoviantoro.jwt.utils

import com.rianwnoviantoro.jwt.domains.entities.UserEntity
import com.rianwnoviantoro.jwt.error.UnauthorizedException
import io.jsonwebtoken.CompressionCodecResolver
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {
    fun generateJwtToken(issuer: String, user: UserEntity): String {
        return Jwts.builder()
            .setIssuer(issuer)
            .setSubject(user.id.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 1 day
            .signWith(SignatureAlgorithm.HS256, "s3CreTK3y").compact()
    }

    fun validateToken(token: String) {
        val claims = Jwts.parser().setSigningKey("s3CreTK3y").parseClaimsJws(token).body

        val expiredDate = claims.expiration
        val expired = expiredDate.before(Date())


        if (expired) {
            throw UnauthorizedException()
        }
    }
}