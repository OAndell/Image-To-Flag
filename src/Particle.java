import processing.core.PApplet;
import processing.core.PImage;
import toxi.physics2d.VerletParticle2D;

/**
 * Created by Oscar on 2017-01-31.
 */
public class Particle extends VerletParticle2D{

    private PApplet canvas;
    private float red;
    private float green;
    private float blue;

    public Particle(float x, float y, PApplet canvas, float[] color) {
        super(x, y);
        this.canvas = canvas;
        this.red = color[0];
        this.green = color[1];
        this.blue = color[2];
    }

    public Particle(float x, float y, PApplet canvas) {
        super(x, y);
        this.canvas = canvas;
        this.red = 0;
        this.green =0;
        this.blue = 0;
    }

    public void display(){
        canvas.fill(red,green,blue);
        canvas.noStroke();
        canvas.ellipse(x,y,13,13);
    }

    public float[] getColor(){
        float[] colors = {red,green,blue};
        return colors;
    }

}
