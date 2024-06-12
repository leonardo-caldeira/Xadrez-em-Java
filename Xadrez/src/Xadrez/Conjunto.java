package Xadrez;
import Pecas.Peca;
import Pecas.Rei;
import auxiliar.Posicao;
import java.util.ArrayList;

public class Conjunto extends ArrayList<Peca>{
    public Conjunto(){
        super();
    }
    //Desenha as peças no tabuleiro
    public void AutoDesenho(){
        for (Peca aThi : this) {
            aThi.autoDesenho();
        }
    }
    //Retorna a peça que foi clicada
    public Peca getPecaClicada(Posicao aPosicao){
        for (Peca aThi : this) {
            if (aThi.foiClicada(aPosicao)) {
                return aThi;
            }
        }
        return null;
    }
    //Retira a peça do conjunto
    public void pecaFora(Peca aPeca){
        for(int i = 0; i < this.size(); i++)
            if(this.get(i) == aPeca)
                this.remove(i);
    }
    //Retorna o rei do conjuto
    public Peca getrei(){
        for (Peca aThi : this) {
            if (aThi instanceof Rei) {
                return aThi;
            }
        }
        return null;
    }
    //Remove todas as peças do conjunto
    public void limpar(){
        for(int i=0;i<this.size();i++){
            this.remove(i);
        }
    }
}