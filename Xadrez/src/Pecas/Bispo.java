package Pecas;
import auxiliar.Posicao;
import static java.lang.Math.abs;

public class Bispo extends Peca {
     public Bispo(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao,Cor);
    }
     /*Verifica se a posição selecionada é um movimento válido para o bispo*/
     @Override
     public boolean movVal(Posicao p){
        //Se tiver uma peça da mesma cor no espaço o movimento é inválido
        if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
        //Verifica se é um movimento na diagonal
        if(abs(p.getLinha()-this.getPosicao().getLinha())==abs(p.getColuna()-this.getPosicao().getColuna())){
            int sl=(p.getLinha()-this.getPosicao().getLinha())/abs(p.getLinha()-this.getPosicao().getLinha());
            int sc=(p.getColuna()-this.getPosicao().getColuna())/abs(p.getColuna()-this.getPosicao().getColuna());
            int lin=sl;
            int col=sc;
            //Verifica se há alguma peça entre ela e a posição selecionada
            while(abs(lin)<abs(p.getLinha()-this.getPosicao().getLinha())){
                if(this.tTabuleiro.temPeca(lin+this.getPosicao().getLinha(), col+this.getPosicao().getColuna())){
                    return false;
                }
                lin+=sl;
                col+=sc;
            }
            return true;
        }
        return false;
    }
    
     //Verifica se o bispo pode se mover de modo a tirar o rei do Xeque
     @Override
    public boolean sairXeque(){
        Posicao p=new Posicao(getPosicao().getLinha(),getPosicao().getColuna());
        for(int a=-1;a<2;a+=2){
            for(int b=-1;b<2;b+=2){
                int i=a;
                int j=b*a;
                //Percorre as posicoes que o bispo pode se mover no tabuleiro
                while(getPosicao().getLinha()+i>=0&&getPosicao().getLinha()+i<8&&getPosicao().getColuna()+j>=0&&getPosicao().getColuna()+j<8){
                    p.setPosicao(getPosicao().getLinha()+i, getPosicao().getColuna()+j);
                    //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                    if(!movVal(p)){
                        break;
                    }else if(!Xeque(p)){
                        return true;
                    }
                    i+=a;
                    j+=a*b;
                }
            }
        }
        return false;
    }
}
