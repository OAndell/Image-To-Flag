import processing.core.PApplet;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.behaviors.AttractionBehavior;
import toxi.physics2d.behaviors.GravityBehavior;

import java.util.ArrayList;

/**
 * Created by Oscar on 2017-01-31.
 */
public class Canvas extends PApplet {
    private VerletPhysics2D physics = new VerletPhysics2D();
    private GravityBehavior gravity = new GravityBehavior(new Vec2D(0,1));
    private ArrayList<Banner> banners = new ArrayList<>();

    public static void main(String[] args){
        PApplet.main("Canvas", args);
    }

    public void setup(){

        background(0);
        physics.addBehavior(gravity);
        banners.add(new Banner(29,29,"banner.png",new Vec2D(50,0),new Vec2D(450,0),this,physics));
    }

    /**
     * Animation loop
     */
    public void draw(){
        background(0);
        for (int i = 0; i < banners.size(); i++) {
            banners.get(i).display();
        }
        //physics.setTimeStep(0.9f);
        physics.update();
    }

    public void mousePressed(){
        System.out.println("click");
        AttractionBehavior drag = new AttractionBehavior(new Vec2D(mouseX,mouseY),500,10);
        physics.addBehavior(drag);
        physics.update();
        physics.removeBehavior(drag);
    }

    public void settings(){
        size(500,1000);
    }


}
