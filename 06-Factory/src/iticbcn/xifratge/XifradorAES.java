package iticbcn.xifratge;

import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] bXifrat = xifraAES(msg, clau);
            return new TextXifrat(bXifrat);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clau);
        } catch (Exception e) {
            System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    private byte[] generaIv() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }

    private SecretKeySpec generaHash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }

    public byte[] xifraAES(String msg, String password) throws Exception {

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

    public String desxifraAES(byte[] bMsgXifrat, String password) throws Exception {

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