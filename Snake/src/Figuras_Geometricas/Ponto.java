package Figuras_Geometricas;
import Exceptions.ValorNegativoException;
import static java.lang.Math.sqrt;

/**
 * Classe Ponto: Esta classe contém um construtor que cria um objeto que é um Ponto
 * @author Márcio Felício
 * @version 1.0
 * @inv Não pode haver um Ponto que esteja fora do primeiro quadrante
 */
public class Ponto {
    /**
     * Definição da estrutura do objeto Ponto que no caso são dois pontos
     */
    private double x;
    private double y;


    /**
     * Definição do construtor por omissão da classe Ponto
     */
    public Ponto()
    {
        this.x = 0; this.y = 0;
    }
    /**
     * Definição do construtor de inicialização da classe Ponto
     */
    public Ponto(double x, double y) throws ValorNegativoException {
        verify(x,y);
        setX(x);
        setY(y);
    }

    /**
     * Definição do construtor de cópia da classe Ponto
     */
    public Ponto(Ponto p){
        this.x = p.getX();
        this.y = p.getY();
    }
    /**
     * método que verifica se um ponto pertence ao primeiro quadrante
     * @param x corresponde à coordenada x do ponto
     * @param y corresponde à coordenada y do ponto
     */

    private void verify(double x,double y) throws ValorNegativoException
    {
        if(x < 0 || y < 0) {
            throw new ValorNegativoException("Ponto:vi");
        }
    }
    /**
     * Estes métodos têm o principal objetivo de retornar a estrutura do objeto Ponto
     */
    public double getX(){ return this.x;}

    public double getY(){ return this.y;}

    /**
     * Estes métodos têm o principal objetivo de modificar a estrutura do objeto Ponto
     */
    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    /**
     * Este método tem o principal objetivo de retornar a distancia euclidiana entre dois pontos
     * @param p é o ponto que irá ser utilizado para calcular a distância com o método recetor
     * @returns (sqrt(dx*dx + dy*dy)) que é a distancia euclidiana entre dois pontos
     */
    public double comprimentoSeg(Ponto p){
        double dx = this.getX() - p.getX();
        double dy = this.getY() - p.getY();
        return sqrt(dx*dx + dy*dy);
    }
    /**
     * Este método rotaciona um ponto
     * @param pivotPoint representa o ponto sobre a qual o poligono será rotacionado
     * @param angleDegrees representa os graus de rotação
     * @return Ponto(Math.round(rotatedX),Math.round(rotatedY));
     */
    public Ponto rotatePoint(Ponto pivotPoint, double angleDegrees) throws ValorNegativoException {
        double angleRad = Math.toRadians(angleDegrees);

        double rotatedX = pivotPoint.getX() + (this.getX() - pivotPoint.getX()) * Math.cos(angleRad) - (this.getY() - pivotPoint.getY()) * Math.sin(angleRad);
        double rotatedY = pivotPoint.getY() + (this.getX() - pivotPoint.getX()) * Math.sin(angleRad) + (this.getY() - pivotPoint.getY()) * Math.cos(angleRad);

        return new Ponto(Math.round(rotatedX),Math.round(rotatedY));
    }


    /**
     * Este método tem o principal objetivo de retornar um clone de uma instancia objeto para não partilharem
     * os mesmos endereços e assim não correr o risco de caso uma instancia seja alterada a outra também o seja,
     * promovo assim o Principio do encapsulamento
     */
    @Override
    public Ponto clone(){
        return new Ponto(this);
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    /**
     * @param obj que serve para verificar se o objeto passado como parametro é ou não igual ao objeto recetor
     * @return true ou false caso seja ou não seja igual
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Ponto p = (Ponto) obj;
        return this.x == p.getX() && this.y == p.getY();
    }

    /**
     * Este método tem o principal objetivo de retornar a representação textual do objeto Ponto, redefino desta
     * maneira o toString da SuperClasse Object.
     */
    @Override
    public String toString(){
        return "(" + Math.round(this.getX()) + "," + Math.round(this.getY()) + ")";
    }
}
