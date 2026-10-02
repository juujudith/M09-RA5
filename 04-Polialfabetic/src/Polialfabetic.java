import java.util.*;

public class Polialfabetic {

    public static char[] majuscules = { 'A', 'Á', 'À', 'Ä', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'Ë', 'F', 'G', 'H',
            'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'Ö', 'P',
            'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z' };
    public static Random random;
    public static int clauSecreta = 12345;
    public static char[] permutat;

    public static void main(String[] args) {
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila" };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void initRandom(int clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet() {

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

    public static int buscaIndex(char[] alfabet, char lletraBuscada) {
        for (int i = 0; i < alfabet.length; i++) {
            if (alfabet[i] == lletraBuscada) {
                return i;
            }
        }

        return -1;
    }

    public static String xifraPoliAlfa(String msg) {
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

    public static String desxifraPoliAlfa(String msgXifrat) {
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