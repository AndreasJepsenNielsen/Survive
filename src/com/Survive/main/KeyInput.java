package com.Survive.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private boolean[] keyDown = new boolean[4];
    private boolean muted = false;
    private Game game;


    public KeyInput(Handler handler, Game game) {
        this.handler = handler;

        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); // will find the key we press on the keyboard

        if (muted == false) {
            if (key == KeyEvent.VK_M) {
                muted = true;
               Game.BGM.stop();
            }
        }else if (muted == true) {
            if(key == KeyEvent.VK_M) {
                muted = false;
                Game.BGM.play();
            }
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);




            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W||key == KeyEvent.VK_UP) { tempObject.setVelY(-handler.spd); keyDown[0] = true; }
                if (key == KeyEvent.VK_S||key == KeyEvent.VK_DOWN) { tempObject.setVelY(handler.spd); keyDown[1] = true; }
                if (key == KeyEvent.VK_D||key == KeyEvent.VK_RIGHT) { tempObject.setVelX(handler.spd); keyDown[2] = true; }
                if (key == KeyEvent.VK_A||key == KeyEvent.VK_LEFT) { tempObject.setVelX(-handler.spd); keyDown[3] = true; }

            }
        }

        if(key == KeyEvent.VK_P)
        {// pause game
            if(game.GameState == Game.STATE.Play) {
                if (Game.paused) Game.paused = false;
                else Game.paused = true;
            }
        }
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);

        if(key == KeyEvent.VK_I)
        {
            if(game.GameState == Game.STATE.Play) Game.GameState = Game.STATE.Shop;
            else if(Game.GameState == Game.STATE.Shop) Game.GameState = Game.STATE.Play;
        }


        // System.out.println(key);
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode(); // will find the key we press on the keyboard

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W||key == KeyEvent.VK_UP) keyDown[0] = false; // tempObject.setVelY(0);
                if (key == KeyEvent.VK_S||key == KeyEvent.VK_DOWN) keyDown[1] = false; //tempObject.setVelY(0);
                if (key == KeyEvent.VK_D||key == KeyEvent.VK_RIGHT) keyDown[2] = false; //tempObject.setVelX(0);
                if (key == KeyEvent.VK_A||key == KeyEvent.VK_LEFT) keyDown[3] = false; //tempObject.setVelX(0);

                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);

                //Horizontal movement
                if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
            }
           /* if (tempObject.getId() == ID.Player2) {
                //player2
                if (key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if (key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
            }*/
        }

    }
}
