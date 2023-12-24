package example.particleengine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Particle {
    private double posX, posY; // Position of the particle
    private double velX, velY; // Velocity, the speed in a given direction.
    private double lifespan; // Time to live (in seconds)
    private double age; // Current age of the particle
    private double transparency;
    private static final double GRAVITY = 0.0;  // Adjust gravity strength



    //constructor
    public Particle(double posX, double posY, double velX, double velY, double lifespan) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.lifespan = lifespan;
        this.velY = -Math.abs(velY);

    }

    // DRAW each particle
    public void draw(GraphicsContext displayParticle) {
        // Draw the particle on the canvas
        transparency = 1.0 - (age / lifespan);
        double rd = Math.random();
        Color particleColor = Color.rgb(255, 255, 255, transparency); // make the particle white color
        displayParticle.setFill(particleColor); // set color fill of the particle
        displayParticle.fillOval(posX, posY, 10, 10);  // set the size of the particle being a circle
    }

    public void update(double time) {
        // Update particle position based on velocity
        posX += velX * time;
        posY += velY * time;

        // Update age of the particle
        age += time;
        velY += GRAVITY * time;

    }

    public boolean isAlive() {
        if(age < lifespan){
            return true;
        }else{
            return false;
        }
    }
}

