package iticbcn.xifratge;

public class AlgorismeAES extends AlgorismeFactory {
    @Override
    public Xifrador creaXifrador() { return new XifradorAES(); }
}
// Igual para AlgorismeMonoalfabetic, AlgorismePolialfabetic y AlgorismeRotX