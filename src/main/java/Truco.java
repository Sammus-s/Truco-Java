public class Truco {                
    public static void main(String[] args) {                                               
       Player p1 = new Player("Player 1");
       Player p2 = new Player("Player 2");          
       Jogo jogo = new Jogo(p1, p2);
       jogo.mainLoop();
    }     
    
    
}
