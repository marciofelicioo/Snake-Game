package Exceptions;
public class MovementException extends Exception{
    private static final long serialVersionUID = 11L;
    public MovementException(){
        super();
    }
    public MovementException(String mensagem)
    {
        super(mensagem);
    }
}