import processing.core.PApplet;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;

/**
 * Created by Oscar on 2017-01-31.
 */
public class Spring extends VerletSpring2D{

    private PApplet canvas;
    private Particle particle1;
    private Particle particle2;

    private float red;
    private float green;
    private float blue;

    public Spring(Particle particle1, Particle particle2, PApplet canvas) {
        super(particle1, particle2, 13 , 0.8f);
        this.canvas = canvas;
        this.particle1 = particle1;
        this.particle2 = particle2;
        float[] colors = particle1.getColor();
        red = colors[0];
        green = colors[1];
        blue = colors[2];
    }

   public void display(){
       canvas.stroke(red,green,blue);
       canvas.strokeWeight(4);
       canvas.line(particle1.x, particle1.y, particle2.x, particle2.y);
   }
}
