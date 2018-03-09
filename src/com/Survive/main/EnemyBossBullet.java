package com.Survive.main;


import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

    private Handler handler;
    Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = ((r.nextInt(5)- -5)+ -5);
        velY = 5;
    }



    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT - 42) velY *= -1; // making it so the enemy will bounce off the bottom and top of the canvas
        //if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        if(y >= Game.HEIGHT) handler.removeObject(this);

        handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,Color.YELLOW,16,16,0.02f,handler));
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }


    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect((int)x,(int)y,16,16);

    }
}
