package com.Survive.main;

import java.awt.*;
import java.util.Random;

public class Boss1 extends GameObject {

    private Handler handler;
    Random r = new Random();

    private int timer = 140;
    private int timer2 = 50;

    public Boss1(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 2;
    }



    public void tick() {
        x += velX;
        y += velY;

        if(timer <= 0) velY = 0;
        else timer--;

        if(timer <= 0)timer2--;
        if(timer2 <= 0)
        {
            if(velX == 0)velX = 5;
            if(velX > 0)
            velX += 0.005f;
            else if(velX < 0)
                velX -= 0.005f;

            velX = Game.clamp(velX,-10, 10);
            int spawn = r.nextInt(6);
            if(spawn == 0) handler.addObject(new EnemyBossBullet((int)x+48,(int)y+48,ID.BasicEnemy,handler));

        }

        //if(y <= 0 || y >= Game.HEIGHT - 120) velY *= -1; // making it so the enemy will bounce off the bottom and top of the canvas
        if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;

       // handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,Color.RED,96,96,0.02f,handler));
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,96,96);
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y,96,96);

    }
}

