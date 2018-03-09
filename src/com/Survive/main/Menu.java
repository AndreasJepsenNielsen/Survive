package com.Survive.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter{

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    public Menu(Game game,Handler handler,HUD hud)
    {
        this.game = game;
        this.hud = hud;
        this.handler = handler;
    }







    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
            //Normal button
        if(game.GameState == Game.STATE.Select) {
            if (mouseOver(mx, my, Game.getWIDTH() / 2 - 200, 150, 500, 64)) {
                game.GameState = Game.STATE.Play;
                handler.addObject(new Player(Game.getWIDTH() / 2, Game.getHEIGHT() / 2, ID.Player, handler)); // giving objects a random starting area
                handler.clearEnemies();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 0;

            }
        }


        //Hard button
        if(game.GameState == Game.STATE.Select) {
            if (mouseOver(mx, my, Game.getWIDTH() / 2 - 200, 250, 500, 64)) {
                game.GameState = Game.STATE.Play;
                handler.addObject(new Player(Game.getWIDTH() / 2, Game.getHEIGHT() / 2, ID.Player, handler)); // giving objects a random starting area
                handler.clearEnemies();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

                game.diff = 1;
            }
        }

        //back button SELECT
        if(game.GameState == Game.STATE.Select) {
            boolean menu = false;
            if(mouseOver(mx,my,150, 70, 100, 64))
            {

                menu = true;
            }
            if(menu) game.GameState = Game.STATE.Menu;

        }

        if(game.GameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Game.getWIDTH() / 2 - 200, 150, 500, 64)) {

                game.GameState = Game.STATE.Select;
            }
        }

        //MENU
        //help button
        if(game.GameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Game.getWIDTH() / 2 - 200, 250, 500, 64)) {
                game.GameState = Game.STATE.Help;
            }
        }
        //back button
        if(game.GameState == Game.STATE.Help)
        {
            if(mouseOver(mx,my,Game.getWIDTH() / 2 - 200, 600, 500, 64))
            {

                game.GameState = Game.STATE.Menu;
            }
        }

        //quit button


        if (game.GameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, Game.getWIDTH() / 2 - 200, 350, 500, 64)) {
                System.exit(1);
            }
        }


        //MENU END
        if(game.GameState == Game.STATE.End)
        {
            if(mouseOver(mx,my,Game.getWIDTH() / 2 - 240, 600, 500, 64))
            {

                game.GameState = Game.STATE.Play;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));

            }
        }


    }

    public void mouseRelease(MouseEvent e)
    {

    }

    private boolean mouseOver(int mx, int my,int x, int y, int width, int height)
    {
        if(mx > x && mx < x + width)
        {
            if(my > y && my < y + height)
            {
                return true;
            }else return false;
        }else return false;
    }

    public void render(Graphics g)
    {     Font font = new Font("arial", 1, 70);
        Font font2 = new Font("arial", 1, 40);
        Font font3 = new Font("arial",1,25);

        if(game.GameState == Game.STATE.Menu) {



            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("Menu", Game.getWIDTH() / 2 - 45, 60);

            g.setFont(font2);
            g.setColor(Color.WHITE);
            g.drawRect((Game.getWIDTH() / 2) - 200, 150, 500, 64);
            g.drawString("Play", Game.getWIDTH() / 2 + 5, 195);

            g.setFont(font2);
            g.setColor(Color.WHITE);
            g.drawRect((Game.getWIDTH()) / 2 - 200, 250, 500, 64);
            g.drawString("Help", Game.getWIDTH() / 2 + 5, 295);

            g.setFont(font2);
            g.setColor(Color.WHITE);
            g.drawRect((Game.getWIDTH() / 2) - 200, 350, 500, 64);
            g.drawString("Quit", Game.getWIDTH() / 2 + 5, 395);
        }else if(game.GameState == Game.STATE.Help)
        {
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("Help", Game.getWIDTH() / 2 - 45, 60);

            g.setFont(font2);
            g.setColor(Color.WHITE);
            g.drawRect((Game.getWIDTH() / 2) - 200, 600, 500, 64);
            g.drawString("Back", Game.getWIDTH() / 2 + 5, 642);

            g.setFont(font3);
            g.drawString("Controls: WASD Keys, M mutes background music",Game.getWIDTH() / 2 -200, 150);
            g.drawString("Press ESC to exit game, And P to pause",Game.getWIDTH() / 2 -200, 200);
            g.drawString("Dodge enemies and projectiles!?!",Game.getWIDTH()/2-200,250);
            g.drawString("Difficulty will slowly increase!",Game.getWIDTH()/2-200,300);
            g.drawString("Try to survive as long as possible good luck!",Game.getWIDTH()/2-200,350);
        }else if(game.GameState == Game.STATE.End)
        {
            g.setColor(Color.WHITE);
            g.setFont(font);
            g.drawString("Game Over", Game.getWIDTH() / 2 -200, 60);

            g.setFont(font2);
            g.setColor(Color.WHITE);
            g.drawRect((Game.getWIDTH() / 2) - 240, 600, 500, 64);
            g.drawString("Go to menu", Game.getWIDTH() / 2 -80 , 642);

            g.setFont(font3);
            g.drawString("You died with a score of: " + hud.getScore() ,Game.getWIDTH() / 2 -180, 150);

        } else if(game.GameState == Game.STATE.Select) {



        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("SELECT DIFFICULTY", Game.getWIDTH() / 2 - 245, 60);

        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawRect((Game.getWIDTH() / 2) - 200, 150, 500, 64);
        g.drawString("Normal", Game.getWIDTH() / 2 + 5, 195);

        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawRect((Game.getWIDTH()) / 2 - 200, 250, 500, 64);
        g.drawString("Hard", Game.getWIDTH() / 2 + 5, 295);

        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawRect(150, 70, 100, 64);
        g.drawString("Back", 150, 110);
    }

    }

    public void tick(){

    }
}
