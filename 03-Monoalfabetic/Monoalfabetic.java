import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic {

    public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
                            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    private static char[] alfabetPermutat;

    public static void main(String[] args) {
        
        String[] frases = {"àrbritre, coixí, Perímetre", "Taüll, DÍA, año", "Peça, Òrrius, Bòvila"};
        alfabetPermutat = permutaAlfabet(majuscules);

        System.out.println("Xifratge:");
        for (int i = 0; i < frases.length; i++) {
            String xifrada = xifraMonoAlfa(frases[i]);
            System.out.println("Test 0" + (i+1) + " " + frases[i] + " -> " + xifrada);
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
        // Pasar frase a mayúsculas
        // Recorrer la frase, encontrar letra por letra en el array de mayúsculas
        // Sustituir esa letra por la letra que está en la misma posición en el abecedario modificado
        
    }

    public static String desxifraMonoAlfa(String cadena) {

    }
}

// Convertir de char[] a List(ArrayList)

// Collections.shuffle(lista) (para la permutación)

// Convertir de list a char[]