package Exceptions;
public class RetanguloException extends Exception{
    private static final long serialVersionUID = 4L;
    public RetanguloException()
    {
        super();
    }
    public RetanguloException(String mensagem){
        super(mensagem);
    }
}
