package Exceptions;
public class ValorNegativoException extends Exception{
    private static final long serialVersionUID = 7L;
    public ValorNegativoException(){ super();}
    public ValorNegativoException(String mensagem) {
        super(mensagem);
    }
}
