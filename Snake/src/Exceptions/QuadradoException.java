package Exceptions;
public class QuadradoException extends Exception{
    private static final long serialVersionUID = 2L;
    public QuadradoException()
    {
        super();
    }
    public QuadradoException(String mensagem){
        super(mensagem);
    }
}