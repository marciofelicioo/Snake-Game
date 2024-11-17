package Exceptions;
public class TrianguloException extends Exception{
    private static final long serialVersionUID = 6L;
    public TrianguloException()
    {
        super();
    }
    public TrianguloException(String mensagem){
        super(mensagem);
    }
}
