package com.jogos.main;
import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Rectangle;

public class Enemy {
     
    public double x, y;
    public int width, height;

    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 10;
    }

    public void tick(){
        x+= (Game.ball.x - x - 6) * 0.03; // incrementa a posição x do jogador
        
        
    }
    
    public void render(Graphics g){ // método que renderiza o jogador na tela
            g.setColor(new Color(255)); // define a cor do jogador
            g.fillRect((int) x, (int)y, width, height ); // desenha o jogador na tela   
        }
    }
    

    


