package com.Survive.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1200, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private boolean coop = false;
    public static boolean paused = false;
    public static backgroundMusic BGM = new backgroundMusic();
    public int diff = 0;

    //0 = normal
    //1 = hard

    private Random random;
    private Handler handler;
    private HUD hud;
    private EnemySpawner spawner;
    private Menu menu;

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public enum STATE {
      Menu,
      Play,
      Help,
        End,
        Select,
    };

    public static STATE GameState = STATE.Menu;

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public STATE getGameState() {
        return GameState;
    }

    public synchronized void stop()
    {
        try {
            thread.join(); // kills the thread
            running = false;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public Game()
    {

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this,handler,hud);
        this.addKeyListener(new KeyInput(handler,this));
        this.addMouseListener(menu);


        new Window(WIDTH,HEIGHT,"Survive",this); // this refers to the class Game that we are in


        spawner = new EnemySpawner(handler,hud,this);

        random = new Random();



        if(GameState == STATE.Play)
        {
            handler.addObject(new Player(WIDTH/2,HEIGHT/2, ID.Player,handler)); // giving objects a random starting area
            handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT -50),ID.BasicEnemy,handler));
        }else{
            for (int i = 0; i < 15; i++) {
                handler.addObject(new MenuParticles(random.nextInt(Game.WIDTH - 50),random.nextInt(Game.HEIGHT -50),ID.MenuParticle,handler));

            }
        }




        if(coop == true)handler.addObject(new Player(WIDTH/2+64,HEIGHT/2, ID.Player2,handler));


    }

    public void run()
    { // game loop that many game companies and big game makers use might be obsolete?

        BGM.play();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick()
    {

        if(GameState == STATE.Play)
        {
            if(!paused){
            hud.tick();
            spawner.tick();
            handler.tick();

            if(HUD.HEALTH <= 0) {
                HUD.HEALTH = 100;

                GameState = STATE.End;
                handler.clearEnemies();
                for (int i = 0; i < 15; i++) {
                    handler.addObject(new MenuParticles(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50), ID.MenuParticle, handler));

                }
            }

            }
        }else if(GameState == STATE.Menu|| GameState == STATE.End||GameState == STATE.Select){
            menu.tick();
            handler.tick();
        }

    }

    private void render()
    {
        this.requestFocus(); // dont have to click on screen to accept keyboard input
        BufferStrategy bs = this.getBufferStrategy(); // will start off with null
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        if(paused)
        {
            g.setColor(Color.white);
            g.drawString("PAUSED",WIDTH/2-48,100);
        }

        if(GameState == STATE.Play) {

            hud.render(g);
        }else if(GameState == STATE.Menu || GameState == STATE.Help|| GameState == STATE.End||GameState == STATE.Select){
         menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) // method that makes it so player cant go out of bounds
    {
        if(var >= max)
        {
            return var = max;
        }else if(var <= min)
        {
            return var = min;
        }
        return var;
    }
    public static void main(String[] args) {
        new Game();
    }
}
