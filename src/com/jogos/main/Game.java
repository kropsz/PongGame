package com.jogos.main;

import java.awt.Canvas; // classe abstrata que representa uma área de desenho
import java.awt.Color;
import java.awt.Dimension; // classe que representa dimensões (largura e altura)
import javax.swing.JFrame; // classe que representa uma janela
import java.awt.image.BufferStrategy; // classe que representa uma estratégia de buffer
import java.awt.Graphics; // classe que representa um gráfico
import java.awt.image.BufferedImage; // classe que representa uma imagem
import java.awt.event.KeyEvent; // classe que representa um evento de teclado
import java.awt.event.KeyListener; // classe que representa um ouvinte de eventos de teclado



public class Game extends Canvas implements Runnable,KeyListener { // implementa a interface Runnable para poder usar threads
       
        private boolean running = true; // indica se o jogo está rodando
        private Thread thread; // thread do jogo
        public static JFrame frame; // janela
        public final static int WIDTH = 160; // largura da janela
        public final static int HEIGHT = 120; // altura da janela
        private final int SCALE = 3; // escala da janela

        public static Player player; // objeto da classe Player
        public static Enemy enemy; // objeto da classe Enemy 
        public static PingPong ball; // objeto da classe PingPong
        private BufferedImage image; // objeto da classe BufferedImage
        

        public Game(){
            setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE)); // define o tamanho do canvas
            this.addKeyListener(this);
            image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // cria uma nova imagem 
            player = new Player(100, HEIGHT-10); // cria um novo objeto da classe Player
            enemy = new Enemy(100, 0); // cria um novo objeto da classe Enemy
            ball = new PingPong(100, HEIGHT/2 - 1); // cria um novo objeto da classe PingPong
        }

        public void initFrame(){
            
        }
    public static void main(String[] args){
        Game game = new Game(); // cria uma nova instância do jogo
        frame = new JFrame("Game"); // cria uma nova instância da classe JFrame
            frame.add(game); // adiciona o canvas na janela
            frame.setResizable(false); // impede que a janela seja redimensionada
            frame.pack(); // ajusta o tamanho da janela
            frame.setLocationRelativeTo(null); // centraliza a janela
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define a operação padrão ao fechar a janela
            frame.setVisible(true); // torna a janela visível
            game.start(); // inicia o jogo
    }

     
    
    
    
    public synchronized void start() { // método para iniciar o jogo
        running = true; // indica que o jogo está rodando
        new Thread(this).start(); // inicia uma nova thread
    }

    public synchronized void stop() { // método para parar o jogo
        running = false; // indica que o jogo não está rodando
        try {
            thread.join(); // espera a thread terminar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
    }

    public void tick() { // método para atualizar o jogo
        player.tick();
        enemy.tick();
        ball.tick();
        }
    

    public void render() { // método para renderizar o jogo
        BufferStrategy bs = this.getBufferStrategy(); // pega a estratégia de buffer do canvas
        if (bs == null) { // se a estratégia de buffer for nula
            this.createBufferStrategy(3); // cria uma estratégia de buffer com 3 buffers
            return; // retorna
        }
        Graphics g = image.getGraphics(); // pega o gráfico da imagem
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT); // preenche a imagem com a cor preta
        
        player.render(g);
        enemy.render(g);
        ball.render(g);
        g = bs.getDrawGraphics(); // pega o gráfico do buffer
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null); // desenha a imagem na tela
        bs.show(); // mostra o buffer
    }   
    
    public void run() { // método que será executado na thread
        long lastTime = System.nanoTime(); // pega o tempo atual em nanosegundos
        double amountOfTicks = 60.0; // define a quantidade de ticks por segundo
        double ns = 1000000000 / amountOfTicks; // define a quantidade de nanosegundos por tick
        double delta = 0; // define a quantidade de tempo que passou desde o último tick
        int frames = 0; // define a quantidade de frames por segundo
        double timer = System.currentTimeMillis(); // pega o tempo atual em milisegundos
        while (running) { // enquanto o jogo estiver rodando
            long now = System.nanoTime(); // pega o tempo atual em nanosegundos
            delta += (now - lastTime) / ns; // calcula a quantidade de tempo que passou desde o último tick
            lastTime = now; // atualiza o tempo atual
            

            if (delta >= 1) { // se a quantidade de tempo que passou desde o último tick for maior ou igual a 1
                tick(); // atualiza o jogo
                render(); // renderiza o jogo
                frames++; // incrementa a quantidade de frames por segundo
                delta--; // reseta a quantidade de tempo que passou desde o último tick
            }
            if(System.currentTimeMillis() - timer > 1000) { // se a quantidade de tempo que passou desde o último segundo for maior que 1000
                System.out.println("FPS: " + frames); // mostra a quantidade de frames por segundo
                frames = 0; // reseta a quantidade de frames por segundo
                timer += 1000; // reseta a quantidade de tempo que passou desde o último segundo
            }
        }
    
        stop(); // para o jogo
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
    }
       else if(e.getKeyCode() == KeyEvent.VK_LEFT){        
            player.left = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
    }
       else if(e.getKeyCode() == KeyEvent.VK_LEFT){        
            player.left = false;
        }
        
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

    
    
}



    