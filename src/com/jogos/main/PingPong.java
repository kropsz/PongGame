package com.jogos.main;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Rectangle;

public class PingPong {
     
    public double x, y;
    public int width, height;

    public double dx, dy;
    public double speed = 0.9;

    public PingPong(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 4;
        this.height = 4;

        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    }

    public void tick(){

            if (x+(dx*speed) + width >= Game.WIDTH) {
                dx*=-1;
            }
            else if (x+(dx*speed) < 0) {
                dx*=-1;
            }

            if (y >= Game.HEIGHT) {
                // Ponto do inimigo
                System.out.println("Ponto do inimigo!");
                new Game();
                return;

                
            }
            else if (y < 0) {
                // Ponto do jogador
                System.out.println("Ponto do jogador!");
                new Game();
                return;
                
            }

            Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);// cria um retângulo com a posição e tamanho da bola
            Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);// cria um retângulo com a posição e tamanho do jogador
            Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);// cria um retângulo com a posição e tamanho do inimigo


            if (bounds.intersects(boundsPlayer)) { // verifica se a bola colidiu com o jogador
                int angle = new Random().nextInt(120 - 45) + 45 + 1;
                dx = Math.cos(Math.toRadians(angle));
                dy = Math.sin(Math.toRadians(angle));
                if (dy > 0) {
                    dy*=-1;
                }
            }
            else if (bounds.intersects(boundsEnemy)) { // verifica se a bola colidiu com o inimigo
                int angle = new Random().nextInt(120 - 45) + 45 + 1;
                dx = Math.cos(Math.toRadians(angle));
                dy = Math.sin(Math.toRadians(angle));
                if (dy < 0) {
                    dy*=-1;
                }
            }
        x+=dx*speed; // incrementa a posição x do jogador
        y+=dy*speed; // incrementa a posição y do jogador
    }
    
    public void render(Graphics g){ 
            g.setColor(new Color(255)); 
            g.fillRect((int) x, (int)y, width, height );   
        }
    }

    // Path: src\com\jogos\main\Game.java}
    

    