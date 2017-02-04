import processing.core.PApplet;
import processing.core.PImage;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletPhysics2D;

import java.util.ArrayList;

/**
 * Created by Oscar on 2017-02-02.
 */

public class Banner {
    private PApplet canvas;
    private ArrayList<ArrayList<Particle>> particlesRows = new ArrayList<>();
    private ArrayList<Spring> springs = new ArrayList<>();
    private PImage img;

    public Banner(int rowSize, int colSize, String imagePath, Vec2D fixPos1, Vec2D fixPos2, PApplet canvas, VerletPhysics2D physics){
        this.canvas = canvas;
        img = canvas.loadImage(imagePath);
        img.loadPixels();

        //create all banner particles
        for (int i = 0; i < colSize; i++) {
            ArrayList<Particle> row  = new ArrayList<>();
            for (int j = 0; j < rowSize; j++) {
                Particle p = new Particle(fixPos1.x + j*10 ,fixPos1.y, canvas, getColor(i,j));//All spawn at same place
                physics.addParticle(p);
                row.add(p);
            }
            particlesRows.add(row);
        }
        //connects particles with springs
        for (int i = 0; i < particlesRows.size()-1; i++) {
            for (int j = 0; j < particlesRows.get(i).size(); j++) {
                if(j == particlesRows.get(i).size()-1){
                    Spring sVer = new Spring(particlesRows.get(i).get(j), particlesRows.get(i+1).get(j), canvas);
                    springs.add(sVer);
                    physics.addSpring(sVer);
                }
                else{
                    Spring sHor = new Spring( particlesRows.get(i).get(j), particlesRows.get(i).get(j+1), canvas);
                    Spring sVer = new Spring( particlesRows.get(i).get(j), particlesRows.get(i+1).get(j), canvas);
                    springs.add(sVer);
                    springs.add(sHor);
                    physics.addSpring(sVer);
                    physics.addSpring(sHor);
                }

            }
        }

        //create the particles from which the banner is suspended.
        Particle holdPoint1 = new Particle(fixPos1.x, fixPos1.y,canvas);
        Particle holdPoint2 = new Particle(fixPos2.x, fixPos2.y,canvas);
        holdPoint1.lock();
        holdPoint2.lock();

        //Connect to first and last particle in top row
        Spring holdSpring1 = new Spring(holdPoint1, particlesRows.get(0).get(0),canvas);
        Spring holdSpring2 = new Spring(holdPoint2, particlesRows.get(0).get(rowSize-1),canvas);
        physics.addSpring(holdSpring1);
        physics.addSpring(holdSpring2);
        springs.add(holdSpring1);
        springs.add(holdSpring2);
    }

    /**
     * Updates and paints the banner on the screen
     */
    public void display(){
        for (int i = 0; i < particlesRows.size(); i++) {
            for (int j = 0; j < particlesRows.get(i).size(); j++) {
                particlesRows.get(i).get(j).display();
            }
        }
        for (int i = 0; i < springs.size(); i++) {
            springs.get(i).display();
        }
    }
    /**
     * Get the color form specific pixel
     */
    public float[] getColor(int i, int j){
        int index = j + i*img.width;
        float[] colors = new float[3];
        colors[0] = canvas.red(img.pixels[index]);
        colors[1] = canvas.green(img.pixels[index]);
        colors[2] = canvas.blue(img.pixels[index]);
        return colors;
    }
}
