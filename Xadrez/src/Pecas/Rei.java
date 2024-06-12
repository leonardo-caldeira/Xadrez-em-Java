package Pecas;
import auxiliar.Posicao;
import static java.lang.Math.abs;

public class Rei extends Peca{
    //Variável que armazena a peça que colocou o rei em Xeque
    protected Peca pe;
    //Variável que armazena o estado de Xeque do rei
    protected boolean estaEmXeque;
    public Rei(String sAFileName, Posicao aPosicao, boolean Cor) {
        super(sAFileName, aPosicao,Cor);
        estaEmXeque=false;
    }
    
    //Movimento especial do rei no qual ele se move duas casas
    public boolean roque(Posicao p){
        /*Verifica se o rei e a torre não se moveram e se a posição selecionada é compátivel com esse movimento*/
        if(ant.getLinha()!=pPosicao.getLinha()){
            return false;
        }else if(ant.getColuna()!=pPosicao.getColuna()){
            return false;
        }else if(p.getLinha()!=this.pPosicao.getLinha()){
            return false;
        }else if(abs(p.getColuna()-this.pPosicao.getColuna())!=2){
            return false;
        }
        //Roque menor
        if(p.getColuna()-this.pPosicao.getColuna()>0){
            //Verifica se as peças estão na posição necessária e se não há peças entre elas
            if(!(tTabuleiro.getPeca(pPosicao.getLinha(), 7) instanceof Torre)){
                return false;
            }else if(tTabuleiro.getPeca(pPosicao.getLinha(),7).ant.getLinha()!=tTabuleiro.getPeca(pPosicao.getLinha(),7).pPosicao.getLinha()){
                return false;
            }else if(tTabuleiro.getPeca(pPosicao.getLinha(),7).ant.getColuna()!=tTabuleiro.getPeca(pPosicao.getLinha(),7).pPosicao.getColuna()){
                return false;
            }
            for(int i=1;i<3;i++){
                if(this.tTabuleiro.temPeca(this.getPosicao().getLinha(), i+this.getPosicao().getColuna())){
                    return false;
                }
            }
        }
        //Roque maior
        else{
            //Verifica se as peças estão na posição necessária e se não há peças entre elas
            if(!(tTabuleiro.getPeca(pPosicao.getLinha(), 0) instanceof Torre)){
                return false;
            }else if(tTabuleiro.getPeca(pPosicao.getLinha(),0).ant.getLinha()!=tTabuleiro.getPeca(pPosicao.getLinha(),0).pPosicao.getLinha()){
                return false;
            }else if(tTabuleiro.getPeca(pPosicao.getLinha(),0).ant.getColuna()!=tTabuleiro.getPeca(pPosicao.getLinha(),0).pPosicao.getColuna()){
                return false;
            }
            for(int i=-1;i>-4;i--){
                if(this.tTabuleiro.temPeca(this.getPosicao().getLinha(), i+this.getPosicao().getColuna())){
                    return false;
                }
            }
        }
        return true;
    }
    /*Verifica se a posição selecionada é um movimento válido para o rei*/
    @Override
    public boolean movVal(Posicao p){
        //Se tiver uma peça da mesma cor no espaço o movimento é inválido
        if(tTabuleiro.getPeca(p.getLinha(), p.getColuna())!=null){
            if(tTabuleiro.getPeca(p.getLinha(), p.getColuna()).Cor==Cor){
               return false;
            }
        }
        if(roque(p)){
            return true;
        }else if(abs(p.getLinha()-this.getPosicao().getLinha())>1){
            return false;
        }else if(abs(p.getColuna()-this.getPosicao().getColuna())>1){
            return false;
        }
        return true;
    }
    
    //Verifica se o rei está em Xeque
    public boolean isXeque(){
        Posicao p = this.getPosicao();
        int aux=0;
        Peca pTemp;
        //Verifica se há uma torre ou rainha causando Xeque
        for(int a=-1;a<2;a+=2){
            //Verifica se está na mesma linha
            for(int i=a;abs(i)<=abs(aux-p.getColuna());i+=a){
                pTemp=tTabuleiro.getPeca(p.getLinha(), i+p.getColuna());
                if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }else if(pTemp instanceof Torre&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }else if(pTemp!=null){
                    break;
                }
            }
            //Verifica se está na mesma coluna
            for(int i=a;abs(i)<=abs(aux-p.getLinha());i+=a){
                pTemp=tTabuleiro.getPeca(p.getLinha()+i, p.getColuna());
                if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }else if(pTemp instanceof Torre&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }else if(pTemp!=null){
                    break;
                }
            }
            aux=7;
        }
        //Verifica se há um bispo ou uma rainha causando Xeque
        for(int a=-1;a<2;a+=2){
            for(int b=-1;b<2;b+=2){
                int i=a;
                int j=b*a;
                //Percorre as posicoes que o bispo pode se mover no tabuleiro
                while(getPosicao().getLinha()+i>=0&&getPosicao().getLinha()+i<8&&getPosicao().getColuna()+j>=0&&getPosicao().getColuna()+j<8){
                    pTemp=tTabuleiro.getPeca(i+p.getLinha(), j+p.getColuna());
                    if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        this.pe=pTemp;
                        return true;
                    }else if(pTemp instanceof Bispo&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        this.pe=pTemp;
                        return true;
                    }else if(pTemp!=null){
                        break;
                    }
                    i+=a;
                    j+=a*b;
                }
            }
        }
        //Verifica se há um cavalo causando Xeque
        for(int a=-1;a<2;a+=2){
            for(int i=a;abs(i)<=2;i+=a){
                for(int j=-(a*(3-abs(i)));abs(j)<=3-abs(i);j+=2*a*(3-abs(i))){
                    if(getPosicao().getLinha()+i<0||getPosicao().getLinha()+i>7||getPosicao().getColuna()+j<0||getPosicao().getColuna()+j>7){
                        continue;
                    }
                    pTemp=tTabuleiro.getPeca(p.getLinha()+i, p.getColuna()+j);
                    if(pTemp instanceof Cavalo&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        this.pe=pTemp;
                        return true;
                    }
                }
            }
        }
        //Verifica se um peão está causando Xeque
        if(Cor){
            if(p.getLinha()>0&&p.getColuna()<7){
                pTemp=tTabuleiro.getPeca(p.getLinha()-1, p.getColuna()+1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }
            }
            if(p.getLinha()>0&&p.getColuna()>0){
                pTemp=tTabuleiro.getPeca(p.getLinha()-1, p.getColuna()-1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }
            }
        }else{
            if(p.getLinha()<7&&p.getColuna()<7){
                pTemp=tTabuleiro.getPeca(p.getLinha()+1, p.getColuna()+1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }
            }
            if(p.getLinha()<7&&p.getColuna()>0){
                pTemp=tTabuleiro.getPeca(p.getLinha()+1, p.getColuna()-1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    this.pe=pTemp;
                    return true;
                }
            }
        }
        //Se não for Xeque atribui que a peça causadora do Xeque é nula
        this.pe=null;
        return false;
    }
    
    //Função que verifica se o rei possui jogadas possíveis
    public boolean temJogadas(){
        //Se o rei estiver em Xeque, verifica se é possível capturar a peça causando o Xeque
        if(pe!=null&&pe.capPeca()){
            return true;
        }else if(movPeca()){
            return true;
        }
        return false;
    }
    
    //Percorre todas as peças da mesma cor e verifica se movendo alguma delas é possível sair do Xeque
    public boolean movPeca(){
        Peca p;
        for(int i = 0; i < c.size(); i++){
            p=c.get(i);
            if(p.sairXeque()){
                return true;
            }
        }
        return false;
    }
    //Verifica se o rei pode se mover de modo a sair do Xeque
    @Override
    public boolean sairXeque(){
        //Percorre um quadrado em volta do rei
        for(int i=-1;i<=1;i++){
            if(i+getPosicao().getLinha()<0||i+getPosicao().getLinha()>7){
                    continue;
                }
            for(int j=-1;j<=1;j++){
                if(j+getPosicao().getColuna()<0||j+getPosicao().getColuna()>7||(i==0&&j==0)){
                    continue;
                }
                Posicao p=new Posicao(getPosicao().getLinha()+i,getPosicao().getColuna()+j);
                if(movVal(p)){
                    if(!Xeque(p)){
                        return true;
                    }
                }
            }
        }
        //Verifica se o roque tira o rei do Xeque
        for(int i=-2;i<3;i+=4){
            if(i+getPosicao().getColuna()<0||i+getPosicao().getColuna()>7){
                continue;
            }
            Posicao p=new Posicao(getPosicao().getLinha(),getPosicao().getColuna()+i);
            if(roque(p)){
                if(!Xeque(p)){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Set e Get para o estado de Xeque do rei
    public void setEstaEmXeque(boolean estaEmXeque) {
        this.estaEmXeque = estaEmXeque;
    }

    public boolean isEstaEmXeque() {
        return estaEmXeque;
    }
    
    
}
