package Pecas;
import Auxiliar.Consts;
import Xadrez.Conjunto;
import Xadrez.Tabuleiro;
import auxiliar.Posicao;
import java.awt.Graphics2D;
import java.io.IOException;
import static java.lang.Math.abs;
import javax.swing.ImageIcon;

//Classe abstrata principal
public abstract class Peca {
    protected ImageIcon iImage;
    protected Posicao pPosicao;
    protected Tabuleiro tTabuleiro;
    protected Conjunto c;
    protected boolean Cor;
    //Armazena a posição anterior da peça para verificar movimentos especiais;
    protected Posicao ant;
    //Armazena as posições atuais e anteriores das peças para verificação de Xeque
    protected Posicao auxPos;
    protected Posicao auxAnt;

    protected Peca(String sAFileName, Posicao aPosicao, boolean Cor) {
        this.pPosicao = aPosicao;
        this.ant = new Posicao(aPosicao.getLinha(),aPosicao.getColuna());
        this.auxAnt=new Posicao(0,0);
        this.auxPos=new Posicao(0,0);
        this.Cor=Cor;
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath()+Consts.PATH + sAFileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void setTabuleiro(Tabuleiro aTabuleiro){
        this.tTabuleiro = aTabuleiro;
    }
    //Desenha a peça no tabuleiro
    public void autoDesenho(){
        iImage.paintIcon(tTabuleiro, (Graphics2D)tTabuleiro.getGraphics(),
                         pPosicao.getColuna() * Consts.SIZE, pPosicao.getLinha() * Consts.SIZE);     
    }
    public boolean foiClicada(Posicao aPosicao){
        return this.pPosicao.igual(aPosicao);
    }
    public Posicao getPosicao(){
        return pPosicao;
    }
    //Verifica se a peça é branca
    public boolean eBranco() {
        return Cor;
    }
    //Armazena uma nova posição anterior da peça
    public void setAnt(Posicao ant) {
        this.ant.setLinha(ant.getLinha());
        this.ant.setColuna(ant.getColuna());
    }
    public Posicao getAnt() {
        return ant;
    }

    public void setC(Conjunto c) {
        this.c = c;
    }
    
    /*Função que verifica se o rei estará em Xeque caso a peça se mova para a posição p
    Para isso move-se a peça para a posição e depois retorna a posição original*/
    public boolean Xeque(Posicao p){
        auxAnt.setPosicao(ant);
        ant.setPosicao(pPosicao);
        auxPos.setPosicao(pPosicao);
        pPosicao.setPosicao(p);
        tTabuleiro.andar(this);
        Rei r=(Rei)c.getrei();
        if(r.isXeque()){
            Voltar();
            return true;
        }
        Voltar();
        return false;
    }
    
    //Volta a posição original da peça
    public void Voltar(){
        ant.setPosicao(pPosicao);
        pPosicao.setPosicao(auxPos);
        tTabuleiro.andar(this);
        ant.setPosicao(auxAnt);
        
    }
    /*Verifica se é possível capturar a peça, a lógica é a mesma do Xeque*/
    public boolean capPeca(){
        Posicao p=this.getPosicao();
        int aux=0;
        Peca pTemp;
        //Verifica se uma torre ou rainha pode eliminar esta peça
        for(int a=-1;a<2;a+=2){
            //Verifica se está na mesma linha
            for(int i=a;abs(i)<=abs(aux-p.getColuna());i+=a){
                pTemp=tTabuleiro.getPeca(p.getLinha(), i+p.getColuna());
                if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }else if(pTemp instanceof Torre&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }else if(pTemp!=null){
                    break;
                }
            }
            //Verifica se está na mesma coluna
            for(int i=a;abs(i)<=abs(aux-p.getLinha());i+=a){
                pTemp=tTabuleiro.getPeca(p.getLinha()+i, p.getColuna());
                if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }else if(pTemp instanceof Torre&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }else if(pTemp!=null){
                    break;
                }
            }
            aux=7;
        }
        //Verifica se um bispo ou uma rainha pode eliminar esta peça
        for(int a=-1;a<2;a+=2){
            for(int b=-1;b<2;b+=2){
                int i=a;
                int j=b*a;
                //Percorre as posicoes que o bispo pode se mover no tabuleiro
                while(getPosicao().getLinha()+i>=0&&getPosicao().getLinha()+i<8&&getPosicao().getColuna()+j>=0&&getPosicao().getColuna()+j<8){
                    pTemp=tTabuleiro.getPeca(i+p.getLinha(), j+p.getColuna());
                    if(pTemp instanceof Rainha&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        return true;
                    }else if(pTemp instanceof Bispo&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        return true;
                    }else if(pTemp!=null){
                        break;
                    }
                    i+=a;
                    j+=a*b;
                }
            }
        }
        //Verifica se um cavalo pode eliminar esta peça
        for(int a=-1;a<2;a+=2){
            for(int i=a;abs(i)<=2;i+=a){
                for(int j=-(a*(3-abs(i)));abs(j)<=3-abs(i);j+=2*a*(3-abs(i))){
                    if(getPosicao().getLinha()+i<0||getPosicao().getLinha()+i>7||getPosicao().getColuna()+j<0||getPosicao().getColuna()+j>7){
                        continue;
                    }
                    pTemp=tTabuleiro.getPeca(p.getLinha()+i, p.getColuna()+j);
                    if(pTemp instanceof Cavalo&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                        return true;
                    }
                }
            }
        }
        //Verifica se um peão pode eliminar esta peça
        if(Cor){
            if(p.getLinha()>0&&p.getColuna()<7){
                pTemp=tTabuleiro.getPeca(p.getLinha()-1, p.getColuna()+1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }
            }
            if(p.getLinha()>0&&p.getColuna()>0){
                pTemp=tTabuleiro.getPeca(p.getLinha()-1, p.getColuna()-1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }
            }
        }else{
            if(p.getLinha()<7&&p.getColuna()<7){
                pTemp=tTabuleiro.getPeca(p.getLinha()+1, p.getColuna()+1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }
            }
            if(p.getLinha()<7&&p.getColuna()>0){
                pTemp=tTabuleiro.getPeca(p.getLinha()+1, p.getColuna()-1);
                if(pTemp instanceof Peao&&((pTemp.eBranco()&&!this.Cor)||(!pTemp.eBranco()&&this.Cor))){
                    return true;
                }
            }
        }
        return false;
    }
    
    //Função que verifica se o movimento é válido
    public abstract boolean movVal(Posicao p);
    //Função que verifica se a peça pode se mover de modo a tirar o rei do Xeque
    public abstract boolean sairXeque();
}
