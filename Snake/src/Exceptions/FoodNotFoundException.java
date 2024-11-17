package Exceptions;
public class FoodNotFoundException extends Exception{
    private static final long serialVersionUID = 10L;
    public FoodNotFoundException(){
        super();
    }
    public FoodNotFoundException(String mensagem)
    {
        super(mensagem);
    }
}