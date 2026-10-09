import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
                            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    private static char[] alfabetPermutat;

    public static void main(String[] args) {
        
        String[] frases = {"àrbritre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila"};
        String[] frasesXifrades = new String[frases.length];
        alfabetPermutat = permutaAlfabet(majuscules);

        for (int i = 0; i < majuscules.length; i++) {
            System.out.print(majuscules[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < alfabetPermutat.length; i++) {
            System.out.print(alfabetPermutat[i] + " ");
        }
        System.out.println();

        System.out.println("Xifratge:");
        for (int i = 0; i < frases.length; i++) {
            String textComplet = "Test 0" + (i+1) + " " + frases[i];
            String xifrada = xifraMonoAlfa(textComplet);
            System.out.printf("%-35s -> %s%n", textComplet, xifrada);
            frasesXifrades[i] = xifrada;
        }

        System.out.println("Desxifratge:");
        for (int i = 0; i < frases.length; i++) {
            String desxifrada = desxifraMonoAlfa(frasesXifrades[i]);
            System.out.printf("%-35s -> %s%n", frasesXifrades[i], desxifrada);
        }
    }

    public static char[] permutaAlfabet(char[] alfabet) {

        // Char[] a ArrayList
        List<Character> lista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }

        // Mezclar letras y convertir ArrayList a char[]
        Collections.shuffle(lista);
        char[] arrayChar = new char[lista.size()];

        // Añadir las letras del ArrayList al char[]
        for (int i = 0; i < lista.size(); i++) {
            arrayChar[i] = lista.get(i);
        }

        return arrayChar;
    }

    public static String xifraMonoAlfa(String cadena) {
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
                char lletraMajuscula = majuscules[j];
                
                if (lletraBusqueda == lletraMajuscula) {
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

    public static String desxifraMonoAlfa(String cadena) {
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
                char lletraMajuscula = alfabetPermutat[j];
                
                if (lletraBusqueda == lletraMajuscula) {
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