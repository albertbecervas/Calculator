package com.abecerra.calculator.core.encryption

import com.abecerra.calculator.presentation.ui.main.MainActivity
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.xor

object KeyGenerator {

    fun obfuscateKey(key: String): ByteArray {
        val clearKey = key.toByteArray()
        val runtimeKey = generateRuntimeKey()

        return generateObfuscatedKey(clearKey, runtimeKey)
    }

    private fun generateObfuscatedKey(clearKey: ByteArray, runtimeKey: ByteArray): ByteArray {
        val obfuscate = ByteArray(32)
        var i = 0

        for (b in clearKey) {
            obfuscate[i] = (runtimeKey[i++] xor b)
        }

        return obfuscate
    }

    private fun generateRuntimeKey(): ByteArray {
        //get the string
        val runtime = String.format(MainActivity::class.java.name, "utf-8")
        var hash: String? = null

        try {
            val md = MessageDigest.getInstance("SHA-256")
            val hashBytes = md.digest(runtime.toByteArray(charset("UTF-8")))
            val bigInteger = BigInteger(1, hashBytes)
            hash = bigInteger.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }

        return hash?.toByteArray() ?: ByteArray(256)
    }
}
