package com.priyadarshan;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.priyadarshan.service.EncryptionDecryptionService;
import com.priyadarshan.service.KeyService;
import com.priyadarshan.constant.CommonConstant;

@SpringBootTest
class EncryptionDecryptionTechniquesApplicationTests {

	public static final String TEST_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuAofTHiUtz+hebpKj45Cv6BLTVg/V9qOuRucvcbADXHwMn1XOAyhLnb5gIhaGD4TMwIZge14bTny9XcUoGapctQtzNyUc0LsMMauagNM8Ppjmx+Fz66vPsdClBDZUw9ju+EpquHzSQg/tymw1vh09w+bgVV/XRXn8UQxQAk0vhs3mX4sHzha1UKzlb1V98YtP4E2tlClOn1YWMl9zUeC5yZHrmcjzV1MGV1DfcGpHA+IvW/Zh/+CpebS0GZPfeL5qju47JNP/uExd4KXI1BOSUgkq2EY5wcmsq7RZrHovUJqrr5AjNZJe5kv77K3EalugJpjnJqOQsTcZkdBPLp+vQIDAQAB";
	public static final String TEST_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC4Ch9MeJS3P6F5ukqPjkK/oEtNWD9X2o65G5y9xsANcfAyfVc4DKEudvmAiFoYPhMzAhmB7XhtOfL1dxSgZqly1C3M3JRzQuwwxq5qA0zw+mObH4XPrq8+x0KUENlTD2O74Smq4fNJCD+3KbDW+HT3D5uBVX9dFefxRDFACTS+GzeZfiwfOFrVQrOVvVX3xi0/gTa2UKU6fVhYyX3NR4LnJkeuZyPNXUwZXUN9wakcD4i9b9mH/4Kl5tLQZk994vmqO7jsk0/+4TF3gpcjUE5JSCSrYRjnByayrtFmsei9QmquvkCM1kl7mS/vsrcRqW6AmmOcmo5CxNxmR0E8un69AgMBAAECggEAAq7nGOrTJD7RwGAWcTReRcP1BRopxkKh/RHpfZzVtPx5x4qrSF5qxS4n4hda1OqmxgIcVBgdvt3u7GRBkTsd8lYkms2Gfo21utpStan5p7PG2v6hKU9/L5ehIV6DhKH/TsBwJpUnwmNT5nw9n0y9C9MW7dJMypMksBBR9YCM286saxcy4skUOqbkERMUIoKbPd8XNSTulafp8cWtzrnUltl2/jtqor8PYqa1KxO7T70v0ERp5prKRnQsGLBmkBCpbSi7kdIntbSKy/vKQ8Y+bByqjUFnPUXkjRIBVcHNBvOKg29+aFHDJSSeZLPpUJac7cKX1nRe4FTteunTswuQgQKBgQDvqCMsSwwAZ3irz2eS6j8JZ/2mTlfyzFJf7TGl8BUDuFDDJkfTntO55cWnQ+nifxUwgcRHjhOwKiFEq2deO17jwegRfnRFCuxMfLX6uSnHmUwptSBTiXML4IxFW0Y8kzkIa0QxERYLrrN5rLjALvXImuSOuJrR+Nt8FcVez9tOQQKBgQDElwjA3wiU6HcvCN/0MV1pGxNIijppIUZFiDpRH999hvdRcJHAZc+Y+hkQsiq6S+6Kv7H0kRzAEMAu72mCFMZmEHj3yuVuY78s6PWJX/eWQ/zfyeyCvVNQjJgOc9+9pUNDugfVZO6Z/bj1igc4y2ltzTCDkDekhfqS3EfZWaUJfQKBgQCN+sRrt0Iy8xnwX31y8kfKIMuKvRspgpbCsdkZ/7PTclyWYJVo781rco64W2myf6tkA1zvL2LGmQ8AibNBhlbfA7+irZHgXWpEnhWJpiz0TQNsKhwuoV2BCdTQOhTQSkAdlr7desveu+8kBImngaqm3+zMCN3fy1jsmFFHrEi2gQKBgQCAum2O7fzOlRJGCEetvQ1vn70WyPzKjnFXYobdTEOqDNCx0UZ/d6tifpIfLqtv5fzDehbrEIKE+fL+SOMLvNLVToAHsiJeEI7Et57gX6jeCpLxb2WhjpuZqRaAkDu4ERlkpdIvV7Itv8h3b/SeqgPVVIEqBSuKXfjgZcHefybkeQKBgQCDoFVn8MaxFeCDBu7begz/l6vdQjWf4zmX8Ck4Nn+Ath3oz3RBE3XvGgoDcbOQsflRNB/Er57YqrmrgrJr1eWsHSIHP2tBOIZ4YxnKQHSg8gbj7+z5XbpxzQGcARD+yWqR4wYdgRg/EtqzSPvTMFXmsNjeER15IDCvrF91mMBqjg==";

	@Autowired
	KeyService keyService;

	@Autowired
	EncryptionDecryptionService encryptionDecryptionService;

	@Test
	void contextLoads() {
	}

	/*
	 * @Test void testAvalaibleSignatures() { for(String s:
	 * java.security.Security.getAlgorithms("Signature")) System.out.println(s); }
	 */
	
	@Test
	void test() {
		for(String s:java.security.Security.getAlgorithms("Signature")) System.out.println(s);
	}

	@Test
	void generateRsaKeyPair() throws Exception {

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(CommonConstant.RSA_KEY_SIZE);
		KeyPair pair = generator.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();

		String privateKeyStr = Base64.encodeBase64String(privateKey.getEncoded());
		String publicKeyStr = Base64.encodeBase64String(publicKey.getEncoded());

		// System.out.println("publicKeyStr -> " + publicKeyStr);
		// System.out.println("privateKeyStr -> " + privateKeyStr);
	}

	@Test
	void getRsaEncryptedDataTest() throws Exception {
		PublicKey publicKey = keyService.getPublicKeyFromString(TEST_PUBLIC_KEY);
		PrivateKey privateKey = keyService.getPrivateKeyFromString(TEST_PRIVATE_KEY);
		String data = "111111";
		String encryptedData = encryptionDecryptionService.getRsaEncryptedData(data, publicKey);
		System.out.println("RSA OEAP -> " + encryptedData);

		String decryptedData = encryptionDecryptionService.getRsaDecryptedData(encryptedData, privateKey);
		System.out.println("RSA OEAP -> " + decryptedData);

	}

	@Test
	String getJweEncryptedDataTest() throws Exception {
		PublicKey publicKey = keyService.getPublicKeyFromString(TEST_PUBLIC_KEY);

		String data = "111111";
		String jweString = encryptionDecryptionService.getJweEncryptedData(data, publicKey);
		System.out.println("JWE String -> \n" + jweString);
		return jweString;
	}

	@Test
	void getJweDecryptedDataTest() throws Exception {
		PrivateKey privateKey = keyService.getPrivateKeyFromString(TEST_PRIVATE_KEY);
		String jweString = getJweEncryptedDataTest();
		
		String[] jwe = jweString.split("[.]");
		System.out.println("Split" + jwe[1]);
		

	}

}
