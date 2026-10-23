import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.KeyStore.SecretKeyEntry;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int npass = 0;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = {h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt)};
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("==============================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("------------------------------\n");
            System.out.printf("--- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps: %s\n", h.getInterval(t1, t2));
            System.out.printf("------------------------------\n\n");
        }

    }

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        String text = pw + salt;
        byte[] bytesHash = md.digest(text.getBytes());

        HexFormat hex = HexFormat.of();
        String resultat = hex.formatHex(bytesHash);

        return resultat;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] pwChars = pw.toCharArray();
        byte[] saltBytes = salt.getBytes();
        int iteracions = 65536;
        int longitudBits = 512;

        PBEKeySpec spec = new PBEKeySpec(pwChars, saltBytes, iteracions, longitudBits);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

        byte[] bytesHash = factory.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        String resultat = hex.formatHex(bytesHash);

        return resultat;
    }

    public String forcaBruta(String alg, String hash, String salt) {

    }

    public String getInterval(long t1, long t2) {

    }

}