import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

final class Encryption {

    //IV length require 16 byte long keys
    static final String SECRET_KEY_ONE = "ssdkFHUy2A#D%kd5"
    static final String SECRET_KEY_TWO = "weJiSEvR5yAC5ftB"
    static final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    static final IvParameterSpec ivParameterSpec = new IvParameterSpec(SECRET_KEY_ONE.getBytes("UTF-8"))
    static final SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY_TWO.getBytes("UTF-8"), "AES")
    
    private Encryption(){}

    static final String encrypt(String valueToEncrypt) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
        byte[] encrypted = cipher.doFinal(valueToEncrypt.getBytes())

        return Base64.encoder.encodeToString(encrypted)
    }

    static final String decrypt(String encrypted) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
        byte[] decryptedBytes = cipher.doFinal(Base64.decoder.decode(encrypted))

        return new String(decryptedBytes)
    }
}
