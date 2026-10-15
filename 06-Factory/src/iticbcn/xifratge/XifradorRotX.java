package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {

    public static final char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
                        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
                        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desp = validaClau(clau);
        String res = xifraRotX(msg, desp);
        return new TextXifrat(res.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desp = validaClau(clau);
        return desxifraRotX(xifrat.toString(), desp);
    }

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int n = Integer.parseInt(clau);
            if (n < 0 || n > 40) throw new NumberFormatException();
            return n;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX(String cadena, int desplacament) {

        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

            if (Character.isUpperCase(letra)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (letra == majuscules[j]) {
                        int posMasX = (j + desplacament) % majuscules.length;
                        textXifrat = textXifrat + majuscules[posMasX];
                    }
                }

            } else if (Character.isLowerCase(letra)) {
                for (int k = 0; k < minuscules.length; k++) {
                    if (letra == minuscules[k]) {
                        int posMasX = (k + desplacament) % minuscules.length;
                        textXifrat = textXifrat + minuscules[posMasX];
                    }
                }

            } else {
                textXifrat = textXifrat + letra;
            }
        }
        return textXifrat;
    }

    public String desxifraRotX(String cadena, int desplacament) {

        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

            if (Character.isUpperCase(letra)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (letra == majuscules[j]) {
                        int posMenysX = (j - desplacament + majuscules.length) % majuscules.length;
                        textDesxifrat = textDesxifrat + majuscules[posMenysX];
                    }
                }

            } else if (Character.isLowerCase(letra)) {
                for (int k = 0; k < minuscules.length; k++) {
                    if (letra == minuscules[k]) {
                        int posMenysX = (k - desplacament + minuscules.length) % minuscules.length;
                        textDesxifrat = textDesxifrat + minuscules[posMenysX];
                    }
                }

            } else {
                textDesxifrat = textDesxifrat + letra;
            }
        }
        return textDesxifrat;
    }
}