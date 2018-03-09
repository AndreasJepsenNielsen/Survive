package com.Survive.main;


import java.awt.*;
import java.util.Random;

public class MenuParticles extends GameObject {

    private Handler handler;

    Random r = new Random();

    private int red = r.nextInt(255);
    private int green = r.nextInt(255);
    private int blue = r.nextInt(255);



    private Color color;

    public MenuParticles(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

      velX = (r.nextInt(10 - -5) + -5);
      velY = (r.nextInt(10 - -5) + -5);
      if(velX == 0) velX = 1;
      if(velY == 0) velY = 1;

      color = new Color(red,green,blue);
    }



    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 42) velY *= -1; // making it so the enemy will bounce off the bottom and top of the canvas
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,color,16,16,0.02f,handler));
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }


    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect((int)x,(int)y,16,16);

    }
}
