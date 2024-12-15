package interfazVisual;

public class Tupla<T, U> {
    private final T primero;
    private final U segundo;

    public Tupla(T primero, U segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }

    public T getPrimerElemento() {
        return primero;
    }

    public U getSegundoElemento() {
        return segundo;
    }
    
    public boolean compararTuplas(Tupla tupla) {
    	return (this.primero == tupla.primero && this.segundo ==  tupla.segundo) || (this.primero == tupla.segundo && this.segundo == tupla.primero); 
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "primero=" + primero +
                ", segundo=" + segundo +
                '}';
    }
}
