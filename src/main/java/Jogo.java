import javax.swing.JOptionPane;

public class Jogo {        
    private Baralho baralho;
    private Player p1, p2;
    private int rodada;
    private int pontosRodada;
    private int pontosTurno;
    private final String Separador = "\n\n\n";
    private boolean empatado = false;

    public Jogo(Player p1, Player p2) {
        this.rodada = 0;
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public void mainLoop(){
        var run = true;
        while (run) {
            start();            
            rodada();
            imprimeLayout();
            winCheck();
        }
    }
    
    private void start(){      
        this.pontosRodada = 1;
        this.pontosTurno = 1;
        this.empatado = false;
        this.rodada ++;
        this.baralho = new Baralho();
        baralho.pegaManilha();                
        
        p1.pegaMao(baralho);   
//        p1.getMao().add(new Carta(1, "espadas"));
//        p1.getMao().add(new Carta(1, "paus"));
//        p1.getMao().add(new Carta(3, "paus"));
//        p1.setPontos(11);
        p1.setPontosTurno(0);
        
        p2.pegaMao(baralho);
//        p2.getMao().add(new Carta(1, "copas"));
//        p2.getMao().add(new Carta(1, "ouros"));
//        p2.getMao().add(new Carta(2, "ouros"));
        p2.setPontosTurno(0);
    }  
    
    private void rodada(){        
        Carta cartaP1, cartaP2, cartaMaior;
        for (int turno = 1; turno <= 3 ; turno++) {
            cartaP1 = tomadaDescisao(p1, turno);
            cartaP2 = tomadaDescisao(p2, turno);
            cartaMaior = baralho.comparaCarta(cartaP1, cartaP2);

            if (cartaMaior == cartaP1) {
                p1.incPontosRodada(this.pontosTurno);
            } else if (cartaMaior == cartaP2) {
                p2.incPontosRodada(this.pontosTurno);
            } else {
                this.pontosTurno = 2;
                this.empatado = true;
            }

            if (((p1.getPontosTurno() >= 2) || (p2.getPontosTurno() >= 2)) ||
                 ((p1.getPontosTurno() == 1 && this.empatado) || (p2.getPontosTurno() == 1 && this.empatado))){                
                break;
            }
        }        
        Player vencedor = ((p1.getPontosTurno() > p2.getPontosTurno())? p1 : p2);
        vencedor.incPontos(this.pontosRodada);
    }
    
    private void winCheck(){
        if ((p1.getPontos() < 12) && (p2.getPontos() < 12)) {
            return;
        }
        
        System.out.println(Separador + "VITÓRIA DO " + (p1.getPontos() >= 12? p1.getNome() : p2.getNome()));
        System.exit(0);                
    }
    
    private void imprimeLayout(){
        System.out.println(Separador + "Rodada: " + this.rodada);
        System.out.println(p1.getNome() + " - " + p2.getNome());
        System.out.println(p1.getPontos() + " - " + p2.getPontos());
    }
    
    private void imprimeLayoutRodada(int ATurno){
        System.out.println(Separador + "Turno: " + ATurno + " Valor: " + this.pontosRodada);
        System.out.println(p1.getNome() + " - " + p2.getNome());
        if (this.pontosTurno == 1) {
            System.out.println(p1.getPontosTurno()+ " - " + p2.getPontosTurno());
        } else {
            System.out.println("EMPATE");
        }
        
        System.out.println("\nCarta Virada: " + baralho.getCartaVirada().show());
    }
    
    private Carta tomadaDescisao(Player APlayer, int ATuno){
        Carta result = null;         
        APlayer.mostraMao(true);
        switch (JOptionPane.showConfirmDialog(null, "Pedir truco " + APlayer.getNome() + "?", "TRUCO!", JOptionPane.YES_NO_OPTION)) {
            case JOptionPane.YES_OPTION:
                if (APlayer.getPontos() < 11) {
                    this.pontosRodada += APlayer.pedeTruco(this.pontosRodada);
                } else {
                    break;
                }
            default:           
                imprimeLayoutRodada(ATuno);
                result = APlayer.jogaCarta();
                break;                
        }
        
        return result;        
    }
}
