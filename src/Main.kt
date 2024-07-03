import java.io.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

fun main() {
    val key: SecretKey = generateAESKey() // Generate AES key
    val inputFile = File("input.txt")
    val encryptedFile = File("encrypted.txt")
    val decryptedFile = File("decrypted.txt")

    // Encrypt file
    encrypt(inputFile, encryptedFile, key)

    // Decrypt file
    decrypt(encryptedFile, decryptedFile, key)
}

fun encrypt(inputFile: File, encryptedFile: File, key: SecretKey) {
    try {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val inputStream = FileInputStream(inputFile)
        val inputBytes = ByteArray(inputFile.length().toInt())
        inputStream.read(inputBytes)

        val outputBytes = cipher.doFinal(inputBytes)

        val outputStream = FileOutputStream(encryptedFile)
        outputStream.write(outputBytes)

        inputStream.close()
        outputStream.close()

        println("File encrypted successfully.")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun decrypt(encryptedFile: File, decryptedFile: File, key: SecretKey) {
    try {
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, key)

        val inputStream = FileInputStream(encryptedFile)
        val inputBytes = ByteArray(encryptedFile.length().toInt())
        inputStream.read(inputBytes)

        val outputBytes = cipher.doFinal(inputBytes)

        val outputStream = FileOutputStream(decryptedFile)
        outputStream.write(outputBytes)

        inputStream.close()
        outputStream.close()

        println("File decrypted successfully.")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun generateAESKey(): SecretKey {
    val keyGenerator = KeyGenerator.getInstance("AES")
    keyGenerator.init(128) // 128-bit AES key
    return keyGenerator.generateKey()
}
