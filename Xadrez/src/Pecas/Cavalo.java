package Pecas;
import auxiliar.Posicao;
import static java.lang.Math.abs;

public class Cavalo extends Peca{
    public Cavalo(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao,Cor);
    }
    /*Verifica se a posição selecionada é um movimento válido para o cavalo*/
    @Override
    public boolean movVal(Posicao p){
       //Verifica se é um movimento em L
       if(abs(p.getLinha()-this.getPosicao().getLinha())+abs(p.getColuna()-this.getPosicao().getColuna())!=3){
           return false;
       }else if(abs(p.getLinha()-this.getPosicao().getLinha())!=1&&abs(p.getLinha()-this.getPosicao().getLinha())!=2){
           return false;
       }else if(abs(p.getColuna()-this.getPosicao().getColuna())!=1&&abs(p.getColuna()-this.getPosicao().getColuna())!=2){
           return false;
       }
       //Se tiver uma peça da mesma cor no espaço o movimento é inválido
       if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
       return true;
   }
    //Verifica se o cavalo pode se mover de modo a tirar o rei do Xeque
    @Override
    public boolean sairXeque(){
        Posicao p=new Posicao(getPosicao().getLinha(),getPosicao().getColuna());
        //Percorre as posicoes que o cavalo pode se mover no tabuleiro
        for(int a=-1;a<2;a+=2){
            for(int i=a;abs(i)<=2;i+=a){
                for(int j=-(a*(3-abs(i)));abs(j)<=3-abs(i);j+=2*a*(3-abs(i))){
                    if(getPosicao().getLinha()+i<0||getPosicao().getLinha()+i>7||getPosicao().getColuna()+j<0||getPosicao().getColuna()+j>7){
                        continue;
                    }
                    p.setPosicao(getPosicao().getLinha()+i, getPosicao().getColuna()+j);
                    //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                    if(!movVal(p)){
                        continue;
                    }else if(Xeque(p)){
                        continue;
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
