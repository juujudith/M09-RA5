package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class XifradorRotX implements Xifrador {
    // las constantes siguen siendo static (tu alfabeto)

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desp = validaClau(clau);
        String res = xifraRotX(msg, desp);   // tu lógica de antes, ya no static
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
}