package Exceptions;
public class DimensionException extends Exception{
    private static final long serialVersionUID = 8L;
    public DimensionException(){
        super();
    }
    public DimensionException(String mensagem)
    {
        super(mensagem);
    }
}
