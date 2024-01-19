package example.particleengine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {
    private double posX, posY; // Position of the particle
    private double velX, velY; // Velocity, the speed in X and Y directions
    private double lifespan; // Time of a particle to live
    private double age; // Current age of the particle
    private double transparency; // transparency of particle
    private static final double GRAVITY = 5;  // gravity for now is 0, adjust to -9.8 to have earth gravity for example
    public static double size = 20;


    //constructor
    public Particle(double posX, double posY, double velX, double velY, double lifespan) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = -Math.abs(velY); // negative value to make the particle move upwards on Y ax
        this.lifespan = lifespan;
    }

    // DRAW each particle on the canvas
    public void draw(GraphicsContext displayParticle) {
        // Draw the particle on the canvas
        transparency = 1.0 - Math.pow(age / lifespan,0.5);

        Color particleColor = Color.rgb(150, 150,150, transparency); // make the particle white color for smoke effect
        displayParticle.setFill(particleColor); // set color fill of the particle
        displayParticle.fillOval(posX, posY, size, size);  // set the size of the particle being a circle 20 by 20
    }

    // update the particle position based on velocity and update age
    public void update(double time) {
        // Update particle position based on velocity
        posX += velX * time;
        posY += velY * time;

        // Update age of the particle
        age += time;
        velY += GRAVITY * time; // gravity is affecting the vertical velocity velY

    }

    // based on the age and lifespan check if the particle has to be removed
    public boolean isAlive() {
        if(age < lifespan){
            return true;
        }else{
            return false;
        }
    }
}