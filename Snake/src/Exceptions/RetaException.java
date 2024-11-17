package Exceptions;
public class RetaException extends Exception{
    private static final long serialVersionUID = 3L;
    public RetaException(){
        super();
    }
    public RetaException(String mensagem)
    {
        super(mensagem);
    }

}
