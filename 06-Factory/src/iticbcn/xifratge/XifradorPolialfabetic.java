package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class XifradorPolialfabetic implements Xifrador {

    public static final char[] majuscules = { 'A', 'Á', 'À', 'Ä', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'Ë', 'F', 'G', 'H',
            'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'Ö', 'P',
            'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z' };
    private Random random;
    private char[] permutat;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauLong);
        String xifrat = xifraPoliAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauLong);
        return desxifraPoliAlfa(xifrat.toString());
    }

    public void initRandom(long clau) {
        random = new Random(clau);
    }

    public void permutaAlfabet() {

        List<Character> lista = new ArrayList<>();
        for (int i = 0; i < majuscules.length; i++) {
            lista.add(majuscules[i]);
        }

        Collections.shuffle(lista, random);
        permutat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            permutat[i] = lista.get(i);
        }
    }

    public int buscaIndex(char[] alfabet, char lletraBuscada) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == lletraBuscada) {
                return i;
            }
        }

        return -1;
    }

    public String xifraPoliAlfa(String msg) {
        String resultat = "";

        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            char lletraMajuscula = Character.toUpperCase(lletra);
            int posicio = buscaIndex(majuscules, lletraMajuscula);

            if (posicio == -1) { // Si no és lletra
                resultat = resultat + lletra;

            } else {
                permutaAlfabet();
                char lletraXifrada = permutat[posicio];

                if (esMinuscula) {
                    lletraXifrada = Character.toLowerCase(lletraXifrada);
                }

                resultat = resultat + lletraXifrada;
            }
        }

        return resultat;
    }

    public String desxifraPoliAlfa(String msgXifrat) {
        String resultat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletra = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            char lletraMajuscula = Character.toUpperCase(lletra);

            if (buscaIndex(majuscules, lletraMajuscula) == -1) {
                resultat = resultat + lletra;

            } else {
                permutaAlfabet();
                int posicio = buscaIndex(permutat, lletraMajuscula);
                char lletraDesxifrada = majuscules[posicio];

                if (esMinuscula) {
                    lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                }

                resultat = resultat + lletraDesxifrada;
            }
        }

        return resultat;
    }
}