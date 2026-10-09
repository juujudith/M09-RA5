public class RotX {

        public static char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 
                            'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
        public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
                            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
        public static int[] desplacament = {0, 2, 4, 6};
    public static void main(String[] args) {
        
        String[] frases = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] frasesXifrades = {"ABC", "ZAÁ", "Ïqoc, Óú. écoèqü", "Úiüht, úiü wx ùxì ív?"};
        String cadenaXifrada = "Úiüht, úiü wx ùxì ív?";

        System.out.println("Xifrat");
        System.out.println("---------");

        for (int i = 0; i < frases.length; i++) {
            String original = frases[i];
            String xifrada = xifraRotX(original, desplacament[i]);
            System.out.println("(" + desplacament[i] + ")-" + original + "  => " + xifrada);
        }

        System.out.println();
        System.out.println("Desxifrat");
        System.out.println("---------");

        for (int i = 0; i < frasesXifrades.length; i++) {
            String original = frasesXifrades[i];
            String desxifrada = desxifraRotX(original, desplacament[i]);
            System.out.println("(" + desplacament[i] + ")" + original + "  => " + desxifrada);
        }

        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("---------");

        forcaBrutaRotX(cadenaXifrada);

    }

    public static String xifraRotX(String cadena, int desplacament) {

        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

                if (Character.isUpperCase(letra)) {
                    int pos = -1;

                    for (int j = 0; j < majuscules.length; j++) {
                        char letraArray = majuscules[j];

                        if (letra == letraArray) {
                            pos = j;
                            int posMasX = (pos + desplacament) % majuscules.length;
                            char letraXifrada = majuscules[posMasX];
                            textXifrat = textXifrat + letraXifrada;
                        }
                    }

                } else if (Character.isLowerCase(letra)) {
                    int pos = -1;

                    for (int k = 0; k < minuscules.length; k++) {
                        char letraArray = minuscules[k];

                        if (letra == letraArray) {
                            pos = k;                           
                            int posMasX = (pos + desplacament) % minuscules.length;
                            char letraXifrada = minuscules[posMasX];
                            textXifrat = textXifrat + letraXifrada;
                        }
                    }
                } else {
                    textXifrat = textXifrat + letra;                   
                }
        }
        return textXifrat;
    }

    public static String desxifraRotX(String cadena, int desplacament) {

        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

                if (Character.isUpperCase(letra)) {
                    int pos = -1;

                    for (int j = 0; j < majuscules.length; j++) {
                        char letraArray = majuscules[j];

                        if (letra == letraArray) {
                            pos = j;
                            int posMasX = (pos - desplacament + majuscules.length) % majuscules.length;
                            char letraXifrada = majuscules[posMasX];
                            textDesxifrat = textDesxifrat + letraXifrada;
                        }
                    }

                } else if (Character.isLowerCase(letra)) {
                    int pos = -1;

                    for (int k = 0; k < minuscules.length; k++) {
                        char letraArray = minuscules[k];

                        if (letra == letraArray) {
                            pos = k;                            
                            int posMasX = (pos - desplacament + minuscules.length) % minuscules.length;
                            char letraXifrada = minuscules[posMasX];
                            textDesxifrat = textDesxifrat + letraXifrada;
                        }
                    }
                } else {
                    textDesxifrat = textDesxifrat + letra;                   
                }
        }
        return textDesxifrat;
    }

    public static void forcaBrutaRotX (String cadenaXifrada) {
        for (int i = 0; i < 40; i++) {
            System.out.println("(" + i + ")->" + desxifraRotX(cadenaXifrada, i));
        }
    }
}