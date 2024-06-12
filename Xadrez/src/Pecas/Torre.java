package Pecas;
import auxiliar.Posicao;
import static java.lang.Math.abs;

public class Torre extends Peca{
    public Torre(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao,Cor);
    }
    /*Verifica se a posição selecionada é um movimento válido para a torre*/
    @Override
    public boolean movVal(Posicao p){
        //Se tiver uma peça da mesma cor no espaço o movimento é inválido
        if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
        //Verifica se a posição esta na mesma coluna que a torre
        if(abs(p.getLinha()-this.getPosicao().getLinha())>0&&abs(p.getColuna()-this.getPosicao().getColuna())==0){
            int s=(p.getLinha()-this.getPosicao().getLinha())/abs(p.getLinha()-this.getPosicao().getLinha());
            //Verifica se há alguma peça entre a torre e a posição selecionada
            for(int i=s*1;abs(i)<abs(p.getLinha()-this.getPosicao().getLinha());i=i+s){
                if(this.tTabuleiro.temPeca(i+this.getPosicao().getLinha(), this.getPosicao().getColuna())){
                    return false;
                }
            }
            return true;
        }
        //Verifica se a posição esta na mesma linha que a torre
        else if(abs(p.getLinha()-this.getPosicao().getLinha())==0&&abs(p.getColuna()-this.getPosicao().getColuna())>0){
            int s=(p.getColuna()-this.getPosicao().getColuna())/abs(p.getColuna()-this.getPosicao().getColuna());
            //Verifica se há alguma peça entre a torre e a posição selecionada
            for(int i=s*1;abs(i)<abs(p.getColuna()-this.getPosicao().getColuna());i=i+s){
                if(this.tTabuleiro.temPeca(this.getPosicao().getLinha(), i+this.getPosicao().getColuna())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    //Verifica se a torre pode se mover de modo a tirar o rei do Xeque
    @Override
    public boolean sairXeque(){
        Posicao p=new Posicao(getPosicao().getLinha(),getPosicao().getColuna());
        int aux=0;
        for(int a=-1;a<2;a+=2){
            //Percorre as linhas que a torre pode se mover no tabuleiro
            for(int i=a;abs(i)<=abs(aux-p.getLinha());i+=a){
                if(getPosicao().getLinha()+i<0||getPosicao().getLinha()+i>7){
                    continue;
                }
                p.setPosicao(getPosicao().getLinha()+i, getPosicao().getColuna());
                //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                if(!movVal(p)){
                    break;
                }else if(Xeque(p)){
                    continue;
                }
                return true;
            }
            //Percorre as colunas que a torre pode se mover no tabuleiro
            for(int i=a;abs(i)<=abs(aux-p.getColuna());i+=a){
                if(getPosicao().getColuna()+i<0||getPosicao().getColuna()+i>7){
                        continue;
                    }
                p.setPosicao(getPosicao().getLinha(), getPosicao().getColuna()+i);
                //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                if(!movVal(p)){
                    break;
                }else if(Xeque(p)){
                    continue;
                }
                return true;
            }
            aux=7;
        }
        return false;
    }
}
