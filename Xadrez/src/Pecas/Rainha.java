package Pecas;
import auxiliar.Posicao;
import static java.lang.Math.abs;

public class Rainha extends Peca{
    public Rainha(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao,Cor);
    }
    /*Verifica se a posição selecionada é um movimento válido para a rainha
    A implementação dela combina as implementações dos bispos e das torres*/
    @Override
    public boolean movVal(Posicao p){
        if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
        if(abs(p.getLinha()-this.getPosicao().getLinha())>0&&abs(p.getColuna()-this.getPosicao().getColuna())==0){
            int s=(p.getLinha()-this.getPosicao().getLinha())/abs(p.getLinha()-this.getPosicao().getLinha());
            for(int i=s*1;abs(i)<abs(p.getLinha()-this.getPosicao().getLinha());i=i+s){
                if(this.tTabuleiro.temPeca(i+this.getPosicao().getLinha(), this.getPosicao().getColuna())){
                    return false;
                }
            }
            return true;
        }else if(abs(p.getLinha()-this.getPosicao().getLinha())==0&&abs(p.getColuna()-this.getPosicao().getColuna())>0){
            int s=(p.getColuna()-this.getPosicao().getColuna())/abs(p.getColuna()-this.getPosicao().getColuna());
            for(int i=s*1;abs(i)<abs(p.getColuna()-this.getPosicao().getColuna());i=i+s){
                if(this.tTabuleiro.temPeca(this.getPosicao().getLinha(), i+this.getPosicao().getColuna())){
                    return false;
                }
            }
            return true;
        }else if(abs(p.getLinha()-this.getPosicao().getLinha())==abs(p.getColuna()-this.getPosicao().getColuna())){
            int sl=(p.getLinha()-this.getPosicao().getLinha())/abs(p.getLinha()-this.getPosicao().getLinha());
            int sc=(p.getColuna()-this.getPosicao().getColuna())/abs(p.getColuna()-this.getPosicao().getColuna());
            while(abs(sl)<abs(p.getLinha()-this.getPosicao().getLinha())){
                if(this.tTabuleiro.temPeca(sl+this.getPosicao().getLinha(), sc+this.getPosicao().getColuna())){
                    return false;
                }
                sl+=sl;
                sc+=sc;
            }
            return true;
        }
        return false;
    }
    /*Verifica se a rainha pode se mover de modo a tirar o rei do Xeque
    A implementação dela combina as implementações dos bispos e das torres*/
    @Override
    public boolean sairXeque(){
        Posicao p=new Posicao(getPosicao().getLinha(),getPosicao().getColuna());
        for(int a=-1;a<2;a+=2){
            for(int b=-1;b<2;b+=2){
                int i=a;
                int j=b*a;
                while(getPosicao().getLinha()+i>=0&&getPosicao().getLinha()+i<8&&getPosicao().getColuna()+j>=0&&getPosicao().getColuna()+j<8){
                    p.setPosicao(getPosicao().getLinha()+i, getPosicao().getColuna()+j);
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
        int aux=0;
        for(int a=-1;a<2;a+=2){
            for(int i=a;abs(i)<=abs(aux-p.getLinha());i+=a){
                if(getPosicao().getLinha()+i<0||getPosicao().getLinha()+i>7){
                    continue;
                }
                p.setPosicao(getPosicao().getLinha()+i, getPosicao().getColuna());
                if(!movVal(p)){
                    break;
                }else if(Xeque(p)){
                    continue;
                }
                return true;
            }
            for(int i=a;abs(i)<=abs(aux-p.getColuna());i+=a){
                if(getPosicao().getColuna()+i<0||getPosicao().getColuna()+i>7){
                        continue;
                    }
                p.setPosicao(getPosicao().getLinha(), getPosicao().getColuna()+i);
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
