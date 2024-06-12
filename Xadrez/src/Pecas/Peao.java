package Pecas;
import auxiliar.Posicao;

public class Peao extends Peca {
    //Variável especial do Peão para o movimento En Passant
    protected boolean enpassant=false;
    
    public Peao(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao, Cor);
    }
    //Verifica se o peão pode se mover para cima
    public boolean MoveUp(Posicao p){
        //Para o peão branco
        if(this.Cor){
            //Se houver uma peça na posição ou se a posição não for no espaço em frente ao peão, o movimento é inválido
            if(this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()-1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()){
                return false;
            }
        }
        //Para o peão preto
        else{
            //Se houver uma peça na posição ou se a posição não for no espaço em frente ao peão, o movimento é inválido
            if(this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()+1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()){
                return false;
            }
        }
        
        return true;
    }
    //Verifica se o peão pode se mover para cima direita
    public boolean MoveUpRight(Posicao p){
        //Para o peão branco
        if(this.Cor){
            //Se não houver uma peça na posição ou se a posição não for em cima e a direita do peão, o movimento é inválido
            if(!this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()-1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()+1){
                return false;
            }
        }
        //Para o peão preto
        else{
            //Se não houver uma peça na posição ou se a posição não for em cima e a direita do peão, o movimento é inválido
            if(!this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()+1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()-1){
                return false;
            }
        }
        return true;
    }
    //Verifica se o peão pode se mover para cima direita
    public boolean MoveUpLeft(Posicao p){
        //Para o peão branco
        if(this.Cor){
            //Se não houver uma peça na posição ou se a posição não for em cima e a esquerda do peão, o movimento é inválido
            if(!this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()-1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()-1){
                return false;
            }
        }
        //Para o peão preto
        else{
            //Se não houver uma peça na posição ou se a posição não for em cima e a esquerda do peão, o movimento é inválido
            if(!this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()+1){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()+1){
                return false;
            }
        }
        return true;
    }
    /*Verificação especial no caso do primeiro movimento do peão, no qual ele pode se mover 2 casas
    A lógica é a mesma da anterior com a diferença de verificar se ele não se moveu*/
    public boolean PrimeiroMov(Posicao p){
        if(this.Cor){
            if(this.getPosicao().getLinha()!=6){
                return false;
            }else if(this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()-2){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()){
                return false;
            }
        }else{
            if(this.getPosicao().getLinha()!=1){
                return false;
            }else if(this.tTabuleiro.temPeca(p.getLinha(),p.getColuna())){
                return false;
            }else if(p.getLinha()!=this.getPosicao().getLinha()+2){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()){
                return false;
            }
        }
        return true;
    }
    
    //Retorna verdadeiro caso o movimento do peão seja En Passant
    public boolean isEnpassant() {
        return enpassant;
    }
    
    //Atribui o movimento do peão como EnPassant
    public void setEnpassant(boolean enpassant) {
        this.enpassant = enpassant;
    }
    
    //Verifica se o movimento En Passant é possível
    public boolean enPassant(Posicao p){
        /*Verifica se o peão se encontra na penúltima fileira antes da fileira de peões adversária
        e se um peão adversário avançou, no turno anterior, duas casas nas colunas ao lado
        */
        if(this.Cor){
            if(this.getPosicao().getLinha()!=3){
                return false;
            }else if(p.getLinha()!=2){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()+1&&p.getColuna()!=this.getPosicao().getColuna()-1){
                return false;
            }else if(!(tTabuleiro.getPecaAnterior() instanceof Peao)){
                return false;
            }else if(tTabuleiro.getPeca(3,p.getColuna())!=tTabuleiro.getPecaAnterior()&&tTabuleiro.getPeca(3,p.getColuna())!=tTabuleiro.getPecaAnterior()){
                return false;
            }else if(tTabuleiro.getPecaAnterior().getAnt().getLinha()!=1){
                return false;
            }
        }else{
            if(this.getPosicao().getLinha()!=4){
                return false;
            }else if(p.getLinha()!=5){
                return false;
            }else if(p.getColuna()!=this.getPosicao().getColuna()+1&&p.getColuna()!=this.getPosicao().getColuna()-1){
                return false;
            }else if(!(tTabuleiro.getPecaAnterior() instanceof Peao)){
                return false;
            }else if(tTabuleiro.getPeca(4,p.getColuna())!=tTabuleiro.getPecaAnterior()&&tTabuleiro.getPeca(4,p.getColuna())!=tTabuleiro.getPecaAnterior()){
                return false;
            }else if(tTabuleiro.getPecaAnterior().getAnt().getLinha()!=6){
                return false;
            }
        }
        enpassant=true;    
        return true;
    }
    //Verifica se a posição selecionada é um movimento válido para o peão
    @Override
    public boolean movVal(Posicao p){
        //Se houver uma peça da mesma cor o movimento é inválido
        if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
        if(this.enPassant(p)){
            return true;
        }else if(this.PrimeiroMov(p)){
            return true;
        }else if(this.MoveUp(p)||this.MoveUpRight(p)||this.MoveUpLeft(p)){
            return true;
        }
        return false;
    }
    
    //Verifica se o peão pode se mover de modo a tirar o rei do Xeque
    @Override
    public boolean sairXeque(){
        Posicao p=new Posicao(0,0);
        //Para o peão branco
        if(this.Cor){
            //Para peão movendo-se 1 ou 2 cassas
            for(int i=-1;i>=-2;i--){
                if(getPosicao().getLinha()+i<0){
                    continue;
                }
                p.setPosicao(getPosicao().getLinha()+i,getPosicao().getColuna());
                //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                if(!movVal(p)){
                    continue;
                }else if(Xeque(p)){
                    continue;
                }
                return true;
            }
        }
        //Para o peão preto
        else{
            //Para peão movendo-se 1 ou 2 cassas
            for(int i=1;i>=2;i++){
                if(getPosicao().getLinha()+i>7){
                    continue;
                }
                p.setPosicao(getPosicao().getLinha()+i,getPosicao().getColuna());
                //Verifica se o movimento é válido e se o rei sai do Xeque após esse movimento
                if(!movVal(p)){
                    continue;
                }else if(Xeque(p)){
                    continue;
                }
                return true;
            }
        }
        return false;
    }
}