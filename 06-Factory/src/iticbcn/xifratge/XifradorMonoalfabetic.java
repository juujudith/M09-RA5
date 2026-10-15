package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {

    public static final char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
                            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    private char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        alfabetPermutat = permutaAlfabet(majuscules);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String xifrat = xifraMonoAlfa(msg);
        return new TextXifrat(xifrat.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        return desxifraMonoAlfa(xifrat.toString());
    }

    public char[] permutaAlfabet(char[] alfabet) {
        List<Character> lista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }

        Collections.shuffle(lista);
        char[] arrayChar = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            arrayChar[i] = lista.get(i);
        }

        return arrayChar;
    }

    public String xifraMonoAlfa(String cadena) {
        String fraseXifrada = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletraFrase = cadena.charAt(i);

            if (!Character.isLetter(lletraFrase)) {
                fraseXifrada = fraseXifrada + lletraFrase;
                continue;
            }

            boolean esMinuscula = Character.isLowerCase(lletraFrase);
            char lletraBusqueda = Character.toUpperCase(lletraFrase);

            for (int j = 0; j < majuscules.length; j++) {
                if (lletraBusqueda == majuscules[j]) {
                    if (esMinuscula) {
                        fraseXifrada = fraseXifrada + Character.toLowerCase(alfabetPermutat[j]);
                    } else {
                        fraseXifrada = fraseXifrada + alfabetPermutat[j];
                    }
                }
            }
        }

        return fraseXifrada;
    }

    public String desxifraMonoAlfa(String cadena) {
        String fraseDesxifrada = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletraFrase = cadena.charAt(i);

            if (!Character.isLetter(lletraFrase)) {
                fraseDesxifrada = fraseDesxifrada + lletraFrase;
                continue;
            }

            boolean esMinuscula = Character.isLowerCase(lletraFrase);
            char lletraBusqueda = Character.toUpperCase(lletraFrase);

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (lletraBusqueda == alfabetPermutat[j]) {
                    if (esMinuscula) {
                        fraseDesxifrada = fraseDesxifrada + Character.toLowerCase(majuscules[j]);
                    } else {
                        fraseDesxifrada = fraseDesxifrada + majuscules[j];
                    }
                }
            }
        }

        return fraseDesxifrada;
    }
}