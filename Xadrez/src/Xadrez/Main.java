/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Xadrez;

import Pecas.Peao;
import auxiliar.Posicao;

/**
 *
 * @author junio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Jogo tMeuJogo = new Jogo();
                tMeuJogo.novoJogo();
            }
        });
    }
}

