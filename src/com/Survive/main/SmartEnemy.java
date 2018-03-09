package com.Survive.main;

import java.awt.*;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        for (int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() == ID.Player ) player = handler.object.get(i);

        }



    }



    public void tick() {
        x += velX;
        y += velY;

        //smart enemy code
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt((x-player.getX())* (x-player.getX()) + (y-player.getY())*(y-player.getY()));

        velX = (float) ((-1.0/distance)*diffX)*2; // enemy speed
        velY = (float) ((-1.0/distance)*diffY)*2; // enemy speed

        // smart enemy code end

        if(y <= 0 || y >= Game.HEIGHT - 42) velY *= -1; // making it so the enemy will bounce off the bottom and top of the canvas
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,Color.GREEN,16,16,0.02f,handler));
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }


    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int)x,(int)y,16,16);

    }
}
