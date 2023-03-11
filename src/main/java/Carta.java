public class Carta{
    private String naipe;
    private int numero;

    public Carta(int numero, String naipe) {
        this.naipe = naipe;
        this.numero = numero;
    }
    
    public String show(){
        return String.valueOf(this.numero) + " de " + this.naipe;
    }

    public String getNaipe() {
        return naipe;
    }

    public void setNaipe(String naipe) {
        this.naipe = naipe;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }        
}
