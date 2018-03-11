package com.Survive.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter{

    Handler handler;
    private int B1 = 1000;
    private int B2 = 2000;
    private int B3 = 1000;

    HUD hud;

    public int getB1() {
        return B1;
    }

    public void setB1(int b1) {
        B1 = b1;
    }

    public int getB2() {
        return B2;
    }

    public void setB2(int b2) {
        B2 = b2;
    }

    public int getB3() {
        return B3;
    }

    public void setB3(int b3) {
        B3 = b3;
    }

    public Shop(Handler handler,HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
    }

    public void render(Graphics g)
    {
        //Box 1
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",0,48));
        g.drawString("SHOP",Game.WIDTH/2,50);

        g.setFont(new Font("arial",0,12));
        g.drawString("Health + 10",Game.WIDTH/2+15,120);
        g.drawString("Cost: " + B1 ,Game.WIDTH/2+15,150);
        g.drawRect(Game.WIDTH/2+10,100,100,80);

        //Box 2

        g.drawString("Speed + 1",Game.WIDTH/2+15,210);
        g.drawString("Cost: " + B2 ,Game.WIDTH/2+15,240);
        g.drawRect(Game.WIDTH/2+10,190,100,80);

        //Box 3

        g.drawString("Refill health",Game.WIDTH/2+15,300);
        g.drawString("Cost: " + B3 ,Game.WIDTH/2+15,330);
        g.drawRect(Game.WIDTH/2+10,280,100,80);

        g.drawString("SCORE: " + hud.getScore(),50,50);
        g.drawString("Press i to go back", 50,70);
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //box1
        if (Game.GameState == Game.STATE.Shop) {
            if (mx >= Game.WIDTH / 20 + 10 && mx <= Game.WIDTH / 2 + 110) {
                if (my >= 100 && my <= 180) {
                    if (hud.getScore() >= B1) {
                        hud.setScore(hud.getScore() - B1);
                         B1 += 1000;
                        hud.bounds += 10;
                        hud.HEALTH = (100 + (hud.bounds) / 2);


                    }
                }
            }

            //box2
            if (mx >= Game.WIDTH / 20 + 10 && mx <= Game.WIDTH / 2 + 110) {
                if (my >= 190 && my <= 270) {
                    if (hud.getScore() >= B2) {
                        hud.setScore(hud.getScore() - B2);
                        B2 += 1000;
                        handler.spd++;

                    }
                }
            }

            //box3
            if (mx >= Game.WIDTH / 20 + 10 && mx <= Game.WIDTH / 2 + 110) {
                if (my >= 280 && my <= 360) {
                    if (hud.getScore() >= B3) {
                        hud.setScore(hud.getScore() - B3);
                        //  B3 += 1000;
                        hud.HEALTH = (100 + (hud.bounds) / 2);


                    }
                }
            }
        }
    }
}
