import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
            System.out.printf("Hash: %s\n", aHashes[i]);
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

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String charset = "abcdefABCDEF1234567890!";
        int maxLongitud = 6;
        npass = 0;

        for (int longitud = 1; longitud <= maxLongitud; longitud++) {
            char[] password = new char[longitud];
            String trobat = provaCombinacions(password, 0, charset, alg, hash, salt);
            
            if (trobat != null) {
                return trobat;
            }
        }
        return null;
    }

    private String provaCombinacions(char[] password, int pos, String charset, String alg, String hash, String salt) throws Exception {
        if (pos == password.length) {
            npass++;
            String pwActual = new String(password);
            String hashActual;

            if (alg.equals("SHA-512")) {
                hashActual = getSHA512AmbSalt(pwActual, salt);
            } else {
                hashActual = getPBKDF2AmbSalt(pwActual, salt);
            }

            if (hashActual.equals(hash)) {
                return pwActual;
            } else {
                return null;
            }       
        }

        for (int i = 0; i < charset.length(); i++) {
            password[pos] = charset.charAt(i);
            String trobat = provaCombinacions(password, pos + 1, charset, alg, hash, salt);
            
            if (trobat != null) {
                return trobat;
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long millis = diff % 1000;
        long segons = (diff / 1000) % 60;
        long minuts = (diff / (1000 * 60)) % 60;
        long hores = (diff / (1000 * 60 * 60)) % 24;
        long dies = diff / (1000L * 60 * 60 * 24);

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }
}