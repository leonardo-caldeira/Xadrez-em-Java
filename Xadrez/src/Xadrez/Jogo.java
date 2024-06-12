package Xadrez;
import Auxiliar.Consts;
import Pecas.*;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Jogo extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Tabuleiro tTabuleiro;//atributo com a janela de desenho
    //Conjuntos das peças
    Conjunto cBrancas;
    Conjunto cPretas;
    //Variável para determinar de quem é a vez
    boolean bEmJogada;
    //Armazena a peça em movimento
    Peca pecaEmMovimento;
    //Armazena a peça a ser promovida
    Peca promocao;
    //Armazena os reis de cada cor
    Rei ReiB;
    Rei ReiP;
    //Armazena o número de turnos desde a última captura
    int turnos;
    //Armazena a escolha da peça promovida
    int escolha;

    public enum CoresConjuntos {

        BRANCAS, PRETAS
    };

    public Jogo() {
        tTabuleiro = new Tabuleiro();//alocação do painel de desenho
        tTabuleiro.setFocusable(true);
        tTabuleiro.addMouseListener(this);//Adiciona evento de mouse ao Painel de desenho
        tTabuleiro.addKeyListener(this);
        cBrancas = new Conjunto();
        cPretas = new Conjunto();
        bEmJogada = true;
        turnos=0;
        initComponents();
    }
    
    //Inicia um novo jogo
    public void novoJogo(){
        inicializar();
        setVisible(true);
    }
    
    //Limpa o jogo e inicia um novo
    public void limpar(){
        VitoriaB.setVisible(false);
        VitoriaP.setVisible(false);
        Empate.setVisible(false);
        pecaEmMovimento=null;
        ReiB=null;
        ReiP=null;
        cBrancas.limpar();
        cPretas.limpar();
        bEmJogada=true;
        turnos=0;
        tTabuleiro.limpar();
        tTabuleiro=new Tabuleiro();
        tTabuleiro.setFocusable(true);
        tTabuleiro.addMouseListener(this);//Adiciona evento de mouse ao Painel de desenho
        tTabuleiro.addKeyListener(this);
        cBrancas = new Conjunto();
        cPretas = new Conjunto();
        initComponents();
    }
    
    //inicializa as peças
    public void inicializar(){
        addPeca(new Peao("Peao.png", new Posicao(6,0),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,1),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,2),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,3),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,4),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,5),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,6),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("Peao.png", new Posicao(6,7),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,0),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,1),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,2),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,3),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,4),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,5),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,6),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Peao("PeaoP.png", new Posicao(1,7),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Torre("Torre.png", new Posicao(7,0),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Torre("Torre.png", new Posicao(7,7),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Torre("TorreP.png", new Posicao(0,0),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Torre("TorreP.png", new Posicao(0,7),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Cavalo("Cavalo.png", new Posicao(7,1),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Cavalo("Cavalo.png", new Posicao(7,6),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Cavalo("CavaloP.png", new Posicao(0,1),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Cavalo("CavaloP.png", new Posicao(0,6),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Bispo("Bispo.png", new Posicao(7,2),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Bispo("Bispo.png", new Posicao(7,5),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Bispo("BispoP.png", new Posicao(0,2),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Bispo("BispoP.png", new Posicao(0,5),false), Jogo.CoresConjuntos.PRETAS);
        addPeca(new Rainha("Rainha.png", new Posicao(7,3),true), Jogo.CoresConjuntos.BRANCAS);
        addPeca(new Rainha("RainhaP.png", new Posicao(0,3),false), Jogo.CoresConjuntos.PRETAS);
        ReiB=new Rei("Rei.png", new Posicao(7,4),true);
        ReiP=new Rei("ReiP.png", new Posicao(0,4),false);
        addPeca(ReiB, Jogo.CoresConjuntos.BRANCAS);
        addPeca(ReiP, Jogo.CoresConjuntos.PRETAS);
        
    }
    
    //Adiciona as peças ao tabuleiro e ao seu devido conjunto
    public void addPeca(Peca aPeca, CoresConjuntos aCorConjunto) {
        aPeca.setTabuleiro(this.tTabuleiro);
        this.tTabuleiro.setPeca(aPeca);
        if (aCorConjunto == CoresConjuntos.BRANCAS) {
            cBrancas.add(aPeca);
            aPeca.setC(cBrancas);
        } else {
            cPretas.add(aPeca);
            aPeca.setC(cPretas);
        }
    }
    
    //Retorna a peça contida na posição clicada
    public Peca getPecaClicada(Posicao aPosicao) {
        if(bEmJogada){
            Peca pTemp = cBrancas.getPecaClicada(aPosicao);
            if (pTemp != null) {
                return pTemp;
            }
            return cBrancas.getPecaClicada(aPosicao);
        }else{
            Peca pTemp = cPretas.getPecaClicada(aPosicao);
            if (pTemp != null) {
                return pTemp;
            }
            return cPretas.getPecaClicada(aPosicao);
        }
        
    }
    
    //Desenha as peças no tabuleiro
    public void paint(Graphics g) {
        super.paint(g);
        cBrancas.AutoDesenho();
        cPretas.AutoDesenho();
    }
    
    //Retorna a posição correspondente do clique
    public Posicao getPosicaoDoClique(MouseEvent aMouseEvent) {
        return new Posicao(aMouseEvent.getY() / Consts.SIZE, aMouseEvent.getX() / Consts.SIZE);
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
//        int x = e.getX();//pega as coordenadas do mouse
//        int y = e.getY();
//        this.jLabel2.setText("Poisição: [" + y / Consts.SIZE + "," + x / Consts.SIZE + "]");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();//pega as coordenadas do mouse
        int y = e.getY();
        this.jLabel2.setText("Posição: [" + y / Consts.SIZE + "," + x / Consts.SIZE + "]");
        //Limpa a label de aviso
        this.jLabel3.setText("");
        repaint();
        //Se não houver nenhuma janela sendo exibida, o jogo segue normalmente
        if(!Escolha.isVisible()&&!VitoriaB.isVisible()&&!VitoriaP.isVisible()&&!Empate.isVisible()){
            Posicao p=this.getPosicaoDoClique(e);
            Peca pTemp = this.getPecaClicada(p);
            //Armazena a peça clicada
            if (pecaEmMovimento==null) {
                pecaEmMovimento = pTemp;
            }
            //Se clicar novamente na mesma peça ela é deselecionada
            else if(pecaEmMovimento==pTemp){
                pecaEmMovimento=null;
            }
            //Se clicar em outro espaço enquanto uma peça esta selecionada
            else{
                //Verifica se é um movimento válido e se não irá colocar o rei em Xeque
                if(pecaEmMovimento.movVal(p)&&!pecaEmMovimento.Xeque(p)){
                    //inverte a vez para pegar a peça do outro conjunto
                    bEmJogada=!bEmJogada;
                    pTemp = this.getPecaClicada(p);
                    //Remove a peça adversária se houver se não aumenta o número de turnos sem captura
                    if (!bEmJogada) {
                        if (pTemp != null) {
                            this.tTabuleiro.removePeca(pTemp);
                            cPretas.pecaFora(pTemp);
                        }else{
                            turnos++;
                        }
                    } else {
                        if (pTemp != null) {
                            this.tTabuleiro.removePeca(pTemp);
                            cBrancas.pecaFora(pTemp);
                        }else{
                            turnos++;
                        }
                    }
                    //Move a peça selecionada
                    pecaEmMovimento.setAnt(pecaEmMovimento.getPosicao());
                    pecaEmMovimento.getPosicao().setPosicao(p); 
                    this.tTabuleiro.andar(pecaEmMovimento);
                    //Verifiva se foi um movimento especial
                    this.movEsp();
                    //Armazena a peça como a última movida
                    this.tTabuleiro.setPecaAnterior(pecaEmMovimento);
                    //Verifica se foi Xeque
                    this.xeque();
                    //Deseleciona a peça
                    pecaEmMovimento = null;
                }
                //Se não for um movimento válido, deseleciona a peça e mostra Movimento inválido na tela
                else{
                    this.jLabel3.setText("Movimento inválido");
                    pecaEmMovimento=null;
                }
                repaint();
            }
            //Se passarem 50 turnos sem nenhuma captura, declara o jogo como empatado
            if(turnos==50){
                Empate.setVisible(true);
            }
        }
    }
    
    //Função que verifica se foi Xeque
    public void xeque(){
        //Verifica se o rei inimigo foi colocado em Xeque
        if(!bEmJogada){
            if(ReiP.Xeque(ReiP.getPosicao())){
                this.jLabel.setText("Rei Preto está em Xeque");
                ReiP.setEstaEmXeque(true);
                if(!ReiP.temJogadas()){
                    //Xeque mate, mostra vitória das brancas
                    VitoriaB.setVisible(true);
                }
            }
            //Se o rei saiu do xeque, muda o estado e retira o texto da tela
            if(ReiB.isEstaEmXeque()){
                if(!ReiB.Xeque(ReiB.getPosicao())){
                    this.jLabel.setText("");
                    ReiB.setEstaEmXeque(false);
                }
            }
        }else{
            if(ReiB.Xeque(ReiB.getPosicao())){
                this.jLabel.setText("Rei branco está em Xeque");
                ReiB.setEstaEmXeque(true);
                if(!ReiB.temJogadas()){
                    //Xeque mate, mostra vitória das brancas
                    VitoriaP.setVisible(true);
                }
            }
            //Se o rei saiu do xeque, muda o estado e retira o texto da tela
            if(ReiP.isEstaEmXeque()){
                if(!ReiP.Xeque(ReiP.getPosicao())){
                    this.jLabel.setText("");
                    ReiP.setEstaEmXeque(false);
                }
            }
        }
        //Rei afogado
        if(!ReiB.temJogadas()){
            Empate.setVisible(true);
        }else if(!ReiP.temJogadas()){
            Empate.setVisible(true);
        }
    }
    
    //Movimentos especiais
    public void movEsp(){
        EnPassant();
        Roque();
        PopUp();
    }
    
    //Se o peão chegar no final do tabuleiro, mostra a janela de seleção
    public void PopUp(){
        if((pecaEmMovimento instanceof Peao)&&(pecaEmMovimento.getPosicao().getLinha()==0||pecaEmMovimento.getPosicao().getLinha()==7)){
            this.Escolha.setVisible(true);
            promocao=pecaEmMovimento;
        }
    }
    //Função que altera o peão para a peça escolhida
    public void Promocao(){
        String icon;
        String txt;
        CoresConjuntos c;
        //Remove o peão e verifica a cor da peça
        this.tTabuleiro.removePeca(promocao);
        if(promocao.eBranco()){
            cBrancas.pecaFora(promocao);
            txt=".png";
            c=Jogo.CoresConjuntos.BRANCAS;
        }else{
            cPretas.pecaFora(promocao);
            txt="P.png";
            c=Jogo.CoresConjuntos.PRETAS;
        }
        //Adiciona uma nova peça no local do antigo peão
        switch(escolha){
            case 1:
                icon="Rainha"+txt;
                addPeca(new Rainha(icon, new Posicao(promocao.getPosicao().getLinha(),promocao.getPosicao().getColuna()),promocao.eBranco()), c);
                break;
            case 2:
                icon="Torre"+txt;
                addPeca(new Torre(icon, new Posicao(promocao.getPosicao().getLinha(),promocao.getPosicao().getColuna()),promocao.eBranco()), c);
                break;
            case 3:
                icon="Bispo"+txt;
                addPeca(new Bispo(icon, new Posicao(promocao.getPosicao().getLinha(),promocao.getPosicao().getColuna()),promocao.eBranco()), c);
                break;
            case 4:
                icon="Cavalo"+txt;
                addPeca(new Cavalo(icon, new Posicao(promocao.getPosicao().getLinha(),promocao.getPosicao().getColuna()),promocao.eBranco()), c);
                break;
        }
        //limpa a escolha e a variável que armazena o peão a ser promovido
        escolha=0;
        promocao=null;
        repaint();
        //Retira a janela de seleção 
        Escolha.setVisible(false);
    }
    
    //Se o peão se mover En Passant, remove o peão adversário
    public void EnPassant(){
        if(pecaEmMovimento instanceof Peao){
            if(((Peao)pecaEmMovimento).isEnpassant()){
                if(!bEmJogada){
                    cPretas.pecaFora(tTabuleiro.getPecaAnterior());
                }else{
                    cBrancas.pecaFora(tTabuleiro.getPecaAnterior());
                }
                tTabuleiro.removePeca(tTabuleiro.getPecaAnterior());
            }
        }
    }
    
    //Se o rei realizar um roque, move a torre para a posição necessária
    public void Roque(){
        if(pecaEmMovimento instanceof Rei){
            if(pecaEmMovimento.getPosicao().getColuna()-pecaEmMovimento.getAnt().getColuna()>1){
                Peca pTemp=tTabuleiro.getPeca(pecaEmMovimento.getPosicao().getLinha(), 7);
                pTemp.setAnt(pTemp.getPosicao());
                pTemp.getPosicao().setColuna(5); 
                this.tTabuleiro.andar(pTemp);
            }else if(pecaEmMovimento.getPosicao().getColuna()-pecaEmMovimento.getAnt().getColuna()<-1){
                Peca pTemp=tTabuleiro.getPeca(pecaEmMovimento.getPosicao().getLinha(), 0);
                pTemp.setAnt(pTemp.getPosicao());
                pTemp.getPosicao().setColuna(3); 
                this.tTabuleiro.andar(pTemp);
            }
        }
    }
    
    public void keyPressed(KeyEvent e) {
        repaint();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escolha = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        VitoriaB = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        VitoriaP = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Empate = new javax.swing.JDialog();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanelCenario = tTabuleiro;
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        Escolha.setLocationByPlatform(true);
        Escolha.setMinimumSize(new java.awt.Dimension(300, 300));
        Escolha.setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Escolha a peca desejada");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Xadrez/Rainha.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Xadrez/Torre.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Xadrez/Bispo.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Xadrez/Cavalo.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EscolhaLayout = new javax.swing.GroupLayout(Escolha.getContentPane());
        Escolha.getContentPane().setLayout(EscolhaLayout);
        EscolhaLayout.setHorizontalGroup(
            EscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscolhaLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(EscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EscolhaLayout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4))
                    .addGroup(EscolhaLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(EscolhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        EscolhaLayout.setVerticalGroup(
            EscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscolhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(EscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(EscolhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        VitoriaB.setAlwaysOnTop(true);
        VitoriaB.setAutoRequestFocus(false);
        VitoriaB.setMinimumSize(new java.awt.Dimension(300, 125));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Vitória das peças brancas");

        jButton5.setText("Novo jogo");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VitoriaBLayout = new javax.swing.GroupLayout(VitoriaB.getContentPane());
        VitoriaB.getContentPane().setLayout(VitoriaBLayout);
        VitoriaBLayout.setHorizontalGroup(
            VitoriaBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VitoriaBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VitoriaBLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        VitoriaBLayout.setVerticalGroup(
            VitoriaBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VitoriaBLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        VitoriaP.setMinimumSize(new java.awt.Dimension(300, 125));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Vitória das peças pretas");

        jButton6.setText("Novo jogo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VitoriaPLayout = new javax.swing.GroupLayout(VitoriaP.getContentPane());
        VitoriaP.getContentPane().setLayout(VitoriaPLayout);
        VitoriaPLayout.setHorizontalGroup(
            VitoriaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VitoriaPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, VitoriaPLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        VitoriaPLayout.setVerticalGroup(
            VitoriaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VitoriaPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );

        Empate.setMinimumSize(new java.awt.Dimension(125, 125));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Empate");

        jButton7.setText("Novo jogo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EmpateLayout = new javax.swing.GroupLayout(Empate.getContentPane());
        Empate.getContentPane().setLayout(EmpateLayout);
        EmpateLayout.setHorizontalGroup(
            EmpateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmpateLayout.createSequentialGroup()
                .addGroup(EmpateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmpateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7))
                    .addGroup(EmpateLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        EmpateLayout.setVerticalGroup(
            EmpateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmpateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Xadrez");
        setResizable(false);

        jPanelCenario.setMaximumSize(new java.awt.Dimension(800, 800));
        jPanelCenario.setMinimumSize(new java.awt.Dimension(800, 800));
        jPanelCenario.setPreferredSize(new java.awt.Dimension(800, 800));
        jPanelCenario.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanelCenarioLayout = new javax.swing.GroupLayout(jPanelCenario);
        jPanelCenario.setLayout(jPanelCenarioLayout);
        jPanelCenarioLayout.setHorizontalGroup(
            jPanelCenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelCenarioLayout.setVerticalGroup(
            jPanelCenarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel2.setText("Posição:");

        jMenu2.setText("File");

        jMenuItem1.setText("Novo Jogo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar2.add(jMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelCenario, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(191, 191, 191)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelCenario, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel)
                    .addComponent(jLabel3))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Se clicar em novo jogo no menu, reseta o tabuleiro
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        limpar();
        novoJogo();
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    //Se for selecionado a Rainha
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        escolha=1;
        Promocao();
    }//GEN-LAST:event_jButton1ActionPerformed
    //Se for selecionado a Torre
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        escolha=2;
        Promocao();
    }//GEN-LAST:event_jButton2ActionPerformed
    //Se for selecionado o Bispo
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        escolha=3;
        Promocao();
    }//GEN-LAST:event_jButton3ActionPerformed
    //Se for selecionado o Cavalo
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        escolha=4;
        Promocao();
    }//GEN-LAST:event_jButton4ActionPerformed
    //Ao clicar em novo jogo na janela de vitória das peças brancas
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton5ActionPerformed
    //Ao clicar em novo jogo na janela de vitória das peças pretas
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton7ActionPerformed
    //Ao clicar em novo jogo na janela de empate
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        limpar();
    }//GEN-LAST:event_jButton6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Empate;
    private javax.swing.JDialog Escolha;
    private javax.swing.JDialog VitoriaB;
    private javax.swing.JDialog VitoriaP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanelCenario;
    // End of variables declaration//GEN-END:variables

}
