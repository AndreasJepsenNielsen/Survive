package com.Survive.main;

import java.awt.*;
import java.util.Random;

public class HardEnemy extends GameObject {

    private Handler handler;
    private Random r = new Random();

    public HardEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 8;
        velY = 8;
    }



    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 42) { velY *= -1; } // making it so the enemy will bounce off the bottom and top of the canvas
        if(x <= 0 || x >= Game.WIDTH - 16) { velX *= -1; }

        handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,Color.BLUE,32,32,0.02f,handler));
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }


    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect((int)x,(int)y,32,32);

    }
}

