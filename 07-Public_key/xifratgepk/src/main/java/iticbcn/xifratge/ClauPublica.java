package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.KeyPairGenerator;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;

public class ClauPublica {
    
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator generador = KeyPairGenerator.getInstance("RSA");
        generador.initialize(2048);
        KeyPair parell = generador.generateKeyPair();
        return parell;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cpr = Cipher.getInstance("RSA");
        cpr.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] msgXifrat = cpr.doFinal(msg.getBytes(StandardCharsets.UTF_8));
        return msgXifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception {
        Cipher cpr = Cipher.getInstance("RSA");
        cpr.init(Cipher.DECRYPT_MODE, ClauPrivada);
        byte[] msgDesxifrat = cpr.doFinal(msgXifrat);
        return new String(msgDesxifrat, StandardCharsets.UTF_8);
    }
}