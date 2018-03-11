package com.Survive.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler { // used to maintain and render all objects

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public int spd = 5;

    public void tick()
    {
        for (int i = 0; i < object.size() ; i++) {
            GameObject tempObject = object.get(i); // setting temporary object to object.get(i) which allows us to get the id of what object we have

            tempObject.tick();
        }
    }

    public void clearEnemies()
    {
        for (int i = 0; i < object.size() ; i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getId() != ID.Player)
            {
                removeObject(tempObject);


                i--;


                    }
                    else if(Game.GameState == Game.STATE.End) {
                if(tempObject.getId() == ID.Player) {
                    removeObject(tempObject);
                }
            }
                }
        }



    public void render(Graphics g)
    {
        for (int i = 0; i < object.size() ; i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object)
    {
        this.object.add(object);
    }

    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
}
