import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
    private String[] naipes = {"ouros", "espadas", "copas", "paus"};
    private int[] numeros = {4,5,6,7,10,11,12,1,2,3};
    private final ArrayList<Carta> cartas = new ArrayList<>();   
    private int manilha;
    private Carta cartaVirada;
    
    public Baralho(){
        for (String np : this.naipes) {
            for (int num: this.numeros) {
                this.cartas.add(new Carta(num, np));
            }
        }
        Collections.shuffle(this.cartas);
    }
    
    public Carta puxaCarta(int ICarta){
        var Lcarta = this.cartas.get(ICarta);
        this.getCartas().remove(ICarta);
        return Lcarta;
    }
    
    public void pegaManilha(){
        Carta carta = puxaCarta(0);
        this.cartaVirada = carta;
        int indexNumero = findIndexNumero(carta.getNumero()) + 1;
        this.manilha = this.numeros[(indexNumero > 9 ? 0 : indexNumero)];
    }
    
    public int findIndexCarta(Carta ACarta){
        var result = -1;
        for (int i = 0; i < this.cartas.size(); i++) {
            if (this.cartas.get(i) == ACarta) {
                result = i;
            }
        }
        return result;
    }
    
    public int findIndexNumero(int ACarta){
        var result = -1;
        for (int i = 0; i < this.numeros.length; i++) {
            if (this.numeros[i] == ACarta) {
                result = i;
            }
        }
        return result;
    }
    
    public int findIndexNaipe(String ACarta){
        int result = -1;
        for (int i = 0; i < this.naipes.length; i++) {
            if (this.naipes[i].equals(ACarta)) {
                result = i;
            }
        }
        return result;
    }
    
    public Carta comparaCarta(Carta ACarta1, Carta ACarta2){
        Carta result = null;
        var duasManilhas = ehManilia(ACarta1) && ehManilia(ACarta2);
        var umaManilha   = ehManilia(ACarta1) ^ ehManilia(ACarta2);        
        
        if (duasManilhas) {
            boolean carta1Maior = (findIndexNaipe(ACarta1.getNaipe()) >
                                   findIndexNaipe(ACarta2.getNaipe()));
            result = (carta1Maior? ACarta1 : ACarta2);                            
        } else if (umaManilha){
            result = (ehManilia(ACarta1)? ACarta1 : ACarta2);
        } else {
            if (ACarta1.getNumero() != ACarta2.getNumero()){                
                boolean carta1Maior = (findIndexNumero(ACarta1.getNumero()) >
                                       findIndexNumero(ACarta2.getNumero()));
                result = (carta1Maior? ACarta1 : ACarta2);
            }
        }                       
        return result;
    }
    
    private boolean ehManilia(Carta ACarta){
        return ACarta.getNumero() == this.manilha;
    }
    
    public void setNumeros(int[] numeros) {
        this.numeros = numeros;
    }
    
    public String[] getNaipes() {
        return naipes;
    }

    public void setNaipes(String[] naipes) {
        this.naipes = naipes;
    }

    public int[] getNumeros() {
        return numeros;
    }    

    public ArrayList<Carta> getCartas() {
        return cartas;
    }                    

    public int getManilha() {
        return manilha;
    }

    public void setManilha(int manilha) {
        this.manilha = manilha;
    }        

    public Carta getCartaVirada() {
        return cartaVirada;
    }

    public void setCartaVirada(Carta cartaVirada) {
        this.cartaVirada = cartaVirada;
    }
    
}
