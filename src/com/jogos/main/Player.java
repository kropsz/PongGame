package com.jogos.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

    public int x, y;    
    public boolean right, left;
    public int width, height;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }
    
    public void tick(){ // método que atualiza o jogador
        if(right){
        x++; // incrementa a posição x do jogador
    }
        else if(left){ // se a seta para a esquerda estiver pressionada
        x--; // decrementa a posição x do jogador
    }
        if(x + width > Game.WIDTH){ // se a posição x do jogador for maior que a largura da tela
        x = Game.WIDTH - width; // define a posição x do jogador como a largura da tela menos a largura do jogador
    }
        else if(x < 0){ // se a posição x do jogador for menor que 0
        x = 0; // define a posição x do jogador como 0
    }
    
}

    public void render(Graphics g){ // método que renderiza o jogador na tela
        g.setColor(new Color (255, 255, 255)); // define a cor do jogador
        g.fillRect(x, y, width, height ); // desenha o jogador na tela   
    }
}
