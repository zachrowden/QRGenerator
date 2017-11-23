import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.util.logging.Logger

final class Encryption {


    static final String DEFAULT_SECRET_KEY_ONE = "ssdkFHUy2A#D%kd5"
    static final String DEFAULT_SECRET_KEY_TWO = "weJiSEvR5yAC5ftB"
    static final Cipher CIPHER = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    static final Logger logger = Logger.getLogger(this.getClass().name)

    static String paramKey
    static String secretKey
    static IvParameterSpec ivParameterSpec
    static SecretKeySpec secretKeySpec
    

    private Encryption(){}


    static final String encrypt(String valueToEncrypt) throws Exception {

        setKeySpecs()
        CIPHER.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

        byte[] encrypted = CIPHER.doFinal(valueToEncrypt.getBytes())

        Base64.encoder.encodeToString(encrypted)
    }

    static final String decrypt(String encrypted) throws Exception {

        setKeySpecs()
        CIPHER.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

        byte[] decryptedBytes = CIPHER.doFinal(Base64.decoder.decode(encrypted))

        new String(decryptedBytes)
    }

    static final boolean setSecretKeys(String paramKey, String secretKey){

        if( paramKey.bytes.length != 16 || secretKey.bytes.length != 16 ){
            logger.warning("Key length is not 16. Both keys require this. paramKey = ${paramKey.bytes.length} secretKey = ${secretKey.bytes.length}")
            return false
        }
        else{
            this.paramKey  = paramKey
            this.secretKey = secretKey
        }
        true
    }

    static final void setKeySpecs(){
        String keyOne = paramKey  != null ? paramKey  : DEFAULT_SECRET_KEY_ONE
        String keyTwo = secretKey != null ? secretKey : DEFAULT_SECRET_KEY_TWO

        ivParameterSpec = new IvParameterSpec(keyOne.getBytes("UTF-8"))
        secretKeySpec   = new SecretKeySpec  (keyTwo.getBytes("UTF-8"), "AES")
    }
}
