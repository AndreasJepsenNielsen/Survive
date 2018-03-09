package com.Survive.main;

import java.awt.*;
import java.util.Random;

public class Player extends GameObject{

    Random random = new Random();
    Handler handler;



    public Player(int x, int y, ID id,Handler handler)
    {
        super(x,y,id);
        this.handler = handler;


    }
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x,0,Game.WIDTH-38);
        y = Game.clamp(y,0,Game.HEIGHT-62);

        handler.addObject(new BasicEnemyTrail((int)x,(int)y,ID.BasicEnemyTrail,Color.white,32,32,0.05f,handler));

        collision();
    }

    private void collision()
    {
        for (int i = 0; i < handler.object.size() ; i++) { // runs through all objects in the game
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy||
                    tempObject.getId() == ID.FastEnemy ||
                    tempObject.getId() == ID.SmartEnemy) // if the id is a ENemy
            {
                if(getBounds().intersects(tempObject.getBounds())) // if the basic enemy intersects with the player
                {

                    dmgTaken sound = new dmgTaken();
                    sound.play(); // evt fjern
                    // collision code
                    HUD.HEALTH -= 2;
                }
            }


        }
    }


    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,32,32);
    }

    public void render(Graphics g) {
        if(id == ID.Player)g.setColor(Color.WHITE);
        else if(id == ID.Player2)g.setColor(Color.RED);

        g.fillRect((int)x,(int)y,32,32);

    }
}
