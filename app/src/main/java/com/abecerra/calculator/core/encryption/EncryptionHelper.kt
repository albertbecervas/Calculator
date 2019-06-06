package com.abecerra.calculator.core.encryption

import android.util.Base64
import com.abecerra.calculator.presentation.ui.main.MainActivity
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.xor

object EncryptionHelper {

    private val key: ByteArray

    //todo put this into gradle properties
    private val obfuscated = byteArrayOf(
        100,
        89,
        87,
        112,
        92,
        83,
        64,
        107,
        71,
        66,
        85,
        65,
        106,
        85,
        80,
        69,
        80,
        71,
        87,
        99,
        92,
        64,
        121,
        83,
        121,
        72,
        72,
        119,
        87,
        73,
        84,
        70
    )

    private//get the string
    val generatedRunTimeByteArray: ByteArray
        get() {
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

    private val randomIv: ByteArray
        get() {
            val iv = ByteArray(16)
            val r = SecureRandom()
            r.nextBytes(iv)
            return iv
        }

    init {
        val runByteKey = generatedRunTimeByteArray
        key = getRealKeyFromObfuscated(runByteKey).toByteArray()
    }

    private fun getRealKeyFromObfuscated(runByteKey: ByteArray): String {
        val realKeyByte = ByteArray(32)

        var i = 0
        for (b in obfuscated) {
            realKeyByte[i] = (runByteKey[i++] xor b)
        }

        return String(realKeyByte)
    }

    @Throws(Exception::class)
    fun encrypt(message: String): String {
        val srcMessage = message.toByteArray(charset("UTF8"))
        val iv = randomIv

        val cipher = getCipherInstance(iv, Cipher.ENCRYPT_MODE)

        val encryptedMessage = cipher.doFinal(srcMessage)
        val composedMessage = setComposedByteArrayMessage(iv, encryptedMessage)

        return Base64.encodeToString(composedMessage, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    private fun getCipherInstance(iv: ByteArray, encryptMode: Int): Cipher {
        val ivSpec = IvParameterSpec(iv)
        val skeySpec = SecretKeySpec(key, "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")!!
        cipher.init(encryptMode, skeySpec, ivSpec)

        return cipher
    }

    @Throws(IOException::class)
    private fun setComposedByteArrayMessage(iv: ByteArray, encryptedMessage: ByteArray): ByteArray {
        val outputStream = ByteArrayOutputStream()
        outputStream.write(iv)
        outputStream.write(encryptedMessage)

        return outputStream.toByteArray()
    }

    @Throws(Exception::class)
    fun decrypt(encrypted: String): String {
        val rawMessage = decryptBase64(encrypted)
        if (rawMessage.size < 16) {
            throw DecryptException("The encrypted message does not have at least 16 bytes")
        }

        // Extract iv from string to decrypt the message
        val iv: ByteArray
        val encryptedMessage: ByteArray
        iv = Arrays.copyOfRange(rawMessage, 0, 16)
        encryptedMessage = Arrays.copyOfRange(rawMessage, 16, rawMessage.size)

        return decryptAes256(iv, encryptedMessage)
    }

    @Throws(Exception::class)
    private fun decryptAes256(iv: ByteArray, encryptedMessage: ByteArray): String {
        val cipher = getCipherInstance(iv, Cipher.DECRYPT_MODE)
        val originalBytes = cipher.doFinal(encryptedMessage)
        return String(originalBytes, Charset.forName("UTF8"))

    }

    @Throws(DecryptException::class)
    private fun decryptBase64(encrypted: String): ByteArray {
        try {
            return Base64.decode(encrypted, Base64.DEFAULT)
        } catch (e: Exception) {
            throw DecryptException("The string message is not Base64")
        }

    }

    class DecryptException internal constructor(message: String) : Exception(message)

}
