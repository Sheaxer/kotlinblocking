package fei.stuba.gono.kotlin.security

import org.springframework.beans.factory.annotation.Value
/***
 * Singleton object holding constants used in security implementation
 * Singleton objekt, ktorý udržiava konštanty použité v implementácii
 * bezpečnosti.
 */
object SecurityConstants {
    @Value("\${security.jwt.token.expire-length:864_000_000}")
     val EXPIRE_LENGTH: Long = 864000000

    @Value("\${security.jwt.token.secret-key:SecretKeyToGenJWTs}")
    var SECRET_KEY = "SecretKeyToGenJWTs"

    val HEADER_STRING = "Authorization"

    val TOKEN_PREFIX = "Bearer "

    val SIGN_UP_URL = "/signup"
}