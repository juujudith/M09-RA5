import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);

            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    private static byte[] generaIv() {

        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private static SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }
    
    public static byte[] xifraAES(String msg, String password) throws Exception {
        
        // Obtenir els bytes de l'String
        byte[] bMsg = msg.getBytes(StandardCharsets.UTF_8);

        // Genera IvParameterSpec
        IvParameterSpec ivSpec = new IvParameterSpec(generaIv());

        // Genera hash
        SecretKeySpec clau = generaHash(password);

        // Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clau, ivSpec);
        byte[] bXifrat = cipher.doFinal(bMsg);

        // Combinar IV i part xifrada
        byte[] resultat = new byte[MIDA_IV + bXifrat.length];
        System.arraycopy(iv, 0, resultat, 0, MIDA_IV);
        System.arraycopy(bXifrat, 0, resultat, MIDA_IV, bXifrat.length);

        // return iv + msgxifrat
        return resultat;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {
        
        // Extreure l'IV
        byte[] ivExtret = new byte[MIDA_IV];
        System.arraycopy(bMsgXifrat, 0, ivExtret, 0, MIDA_IV);

        // Extreure la part xifrada
        byte[] bXifrat = new byte[bMsgXifrat.length - MIDA_IV];
        System.arraycopy(bMsgXifrat, MIDA_IV, bXifrat, 0, bXifrat.length);

        // Fer hash de la clau
        SecretKeySpec clau = generaHash(password);

        // Desxifrar
        IvParameterSpec ivSpec = new IvParameterSpec(ivExtret);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clau, ivSpec);
        byte[] bDesxifrat = cipher.doFinal(bXifrat);

        // return String desxifrat
        return new String(bDesxifrat, StandardCharsets.UTF_8);
    }

} 