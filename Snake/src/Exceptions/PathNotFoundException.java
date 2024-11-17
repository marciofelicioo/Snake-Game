package Exceptions;
public class PathNotFoundException extends Exception{
    private static final long serialVersionUID = 9L;
    public PathNotFoundException(){
        super();
    }
    public PathNotFoundException(String mensagem)
    {
        super(mensagem);
    }
}
