package Xadrez;
import Pecas.Peca;
import Auxiliar.Consts;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Tabuleiro extends JPanel {
    //Armazena as posições das peças para um melhor controle do jogo
    protected Peca tab[][] = new Peca[8][8];
    //Armazena a última peça que foi movida para o movimento especial do peão
    protected Peca pecaAnterior;
    
  @Override //sobrescrita do metodo paintComponent da classe JPanel
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    /*64 é o numedo de quadrantes de um tabuleiro de xadrez*/
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((j+i) % 2 == 0) g2d.setColor(Color.lightGray);
        else g2d.setColor(Color.gray);
        g2d.fillRect(j * Consts.SIZE, i*Consts.SIZE,
                         Consts.SIZE, Consts.SIZE);
      }
    }
  }
  
  //Coloca a peça no tabuleiro
  public void setPeca(Peca peca){
      this.tab[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()]=peca;
  }
  //Pega a peça do tabuleiro
  public Peca getPeca(int i,int j){
      return this.tab[i][j];
  }
  
  //Move a peça no tabuleiro
  public void andar(Peca peca){
      this.tab[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()]=peca;
      this.tab[peca.getAnt().getLinha()][peca.getAnt().getColuna()]=null;
  }
  //Remove a peça do tabuleiro
  public void removePeca(Peca peca){
      this.tab[peca.getPosicao().getLinha()][peca.getPosicao().getColuna()]=null;
  }
  //Verifica se há uma peça na posição
  public boolean temPeca(int i,int j){
      return tab[i][j] != null;
  }
  //Retorna a última peça movida
    public Peca getPecaAnterior() {
        return pecaAnterior;
    }
    //Atribui a última peça movida
    public void setPecaAnterior(Peca pecaAnterior) {
        this.pecaAnterior = pecaAnterior;
    }
    //Limpa o tabuleiro
    public void limpar(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this.tab[i][j]=null;
            }
        }
    }
}