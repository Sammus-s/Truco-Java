import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Player {
    private final Scanner scan = new Scanner(System.in);
    private ArrayList<Carta> mao = new ArrayList<>();    
    private int pontos;
    private int pontosTurno;    
    private String nome;

    public Player(String ANome) { 
        this.pontos = 0;
        this.nome = ANome;
    }      

    public Carta jogaCarta(){
        mostraMao(false);
        System.out.println("Jogada: ");
        int iCarta = scan.nextInt() - 1;
        var Lcarta = this.mao.get(iCarta);
        this.mao.remove(iCarta);
        return Lcarta;                
    }  
    
    public void pegaMao(Baralho Abaralho){
        this.mao.clear();
        for (int i = 0; i < 3; i++) {
            this.mao.add(Abaralho.puxaCarta(i));
        }
    }
        
    public void mostraMao(boolean AEspacar){
        var msg = (AEspacar? "\n\n\n": "");
        msg += "Cartas de " + this.nome + ":\n";
        for (int i = 0; i < mao.size(); i++) {
            msg += "(" + (i + 1) + ") " + mao.get(i).show() + "  "; 
        }        
        
        System.out.println(msg); 
    }
    
    public int pedeTruco(int AVlrTurno){
        int result = 0;
        int respostaAdversario = JOptionPane.showConfirmDialog(null, "Oponente aceita o truco?", "TRUCO!", JOptionPane.YES_NO_OPTION);                                                        
                    
        if((respostaAdversario == JOptionPane.YES_OPTION) && (AVlrTurno < 12)){
            result = (AVlrTurno == 1? 2 : 3);
        } 
        
        return result;
    }
    
    public void incPontosRodada(int AValor){
        this.pontosTurno += AValor;
    }
    
    public void incPontos(int AValor){
        this.pontos += AValor;
    }
    
    public ArrayList<Carta> getMao() {
        return mao;
    }
    
    public void setMao(ArrayList<Carta> Amao){
        this.mao = Amao;
    }           

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    public int getPontosTurno() {
        return pontosTurno;
    }

    public void setPontosTurno(int pontosTurno) {
        this.pontosTurno = pontosTurno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
        
}
