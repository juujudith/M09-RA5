public class Rot13 {

        public static char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 
                            'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
        public static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 
                            'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {

        String original1 = "ABC";
        String frase1 = xifraRot13("ABC");
        String original2 = "XYZ";
        String frase2 = xifraRot13("XYZ");
        String original3 = "Hola, Mr. calçot";
        String frase3 = xifraRot13("Hola, Mr. calçot");
        String original4 = "Perdó, per tu què és?";
        String frase4 = xifraRot13("Perdó, per tu què és?");

        String original5 = "IÏG";
        String frase5 = desxifraRot13("IÏJ");
        String original6 = "FGH";
        String frase6 = desxifraRot13("FGH");
        String original7 = "Òwúi, Ùa. jiúkwb";
        String frase7 = desxifraRot13("Òwúi, Ùá. jiúkwb");
        String original8 = "Zmálx, zmá bc acñ nà?";
        String frase8 = desxifraRot13("Zmálx, zmá bc acñ nà?");

        System.out.println("Xifrat");
        System.out.println("----------");
        System.out.println(original1 + "  => " + frase1);
        System.out.println(original2 + "  => " + frase2);
        System.out.println(original3 + "  => " + frase3);
        System.out.println(original4 + "  => " + frase4);
        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("----------");
        System.out.println(original5 + "  => " + frase5);
        System.out.println(original6 + "  => " + frase6);
        System.out.println(original7 + "  => " + frase7);
        System.out.println(original8 + "  => " + frase8);

    }

    public static String xifraRot13(String cadena) {

        String textXifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

                if (Character.isUpperCase(letra)) {
                    int pos = -1;

                    for (int j = 0; j < majuscules.length; j++) {
                        char letraArray = majuscules[j];

                        if (letra == letraArray) {
                            pos = j;

                            int posMas13 = (pos + 13) % majuscules.length;
                            char letraXifrada = majuscules[posMas13];
                            textXifrat = textXifrat + letraXifrada;
                        }
                    }

                } else if (Character.isLowerCase(letra)) {
                    int pos = -1;

                    for (int k = 0; k < minuscules.length; k++) {
                        char letraArray = minuscules[k];

                        if (letra == letraArray) {
                            pos = k;
                            
                            int posMas13 = (pos + 13) % minuscules.length;
                            char letraXifrada = minuscules[posMas13];
                            textXifrat = textXifrat + letraXifrada;
                        }
                    }
                } else {
                    textXifrat = textXifrat + letra;
                    
                }
        }
        return textXifrat;
    }

    public static String desxifraRot13(String cadena) {

        String textDesxifrat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);

                if (Character.isUpperCase(letra)) {
                    int pos = -1;

                    for (int j = 0; j < majuscules.length; j++) {
                        char letraArray = majuscules[j];

                        if (letra == letraArray) {
                            pos = j;

                            int posMas13 = (pos - 13 + majuscules.length) % majuscules.length;
                            char letraXifrada = majuscules[posMas13];
                            textDesxifrat = textDesxifrat + letraXifrada;
                        }
                    }

                } else if (Character.isLowerCase(letra)) {
                    int pos = -1;

                    for (int k = 0; k < minuscules.length; k++) {
                        char letraArray = minuscules[k];

                        if (letra == letraArray) {
                            pos = k;
                            
                            int posMas13 = (pos - 13 + minuscules.length) % minuscules.length;
                            char letraXifrada = minuscules[posMas13];
                            textDesxifrat = textDesxifrat + letraXifrada;
                        }
                    }
                } else {
                    textDesxifrat = textDesxifrat + letra;
                    
                }
        }
        return textDesxifrat;
    }

}