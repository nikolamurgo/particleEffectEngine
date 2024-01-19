package example.particleengine;

import java.util.ArrayList;
import java.util.List;

public class Emitter {
    private double posX, posY;  // Emitter position
    private int particlesPerSecond; // how many particles the emitter should emit per second
    private List<Particle> particles; // store particles in arraylist (these are alive active particles)

    // constructor
    public Emitter(double posX, double posY, int particlesPerSecond) {
        this.posX = posX;
        this.posY = posY;
        this.particlesPerSecond = particlesPerSecond;
        this.particles = new ArrayList<>(); // initialize list of particles
    }

    // getter for position X emitter
    public double getPosX() {
        return posX;
    }
    // getter for position Y emitter
    public double getPosY() {
        return posY;
    }

    // generate and add particles to ArrayList based on partivles per second
    public void emitParticles(double time) {
        for (int i = 0; i < particlesPerSecond * time; i++) {
            Particle particle = createParticle();
            particles.add(particle);
        }
    }

    // create new particle with random velocity and lifespan
    private Particle createParticle() {
        double velX = Math.random() * 100 - 50;  // Random velocity for demonstration
        double velY = Math.random() * 100 + 50;
        double lifespan = Math.random() * 2 + 3;  // Random lifespan between 3 and 5

        return new Particle(posX, posY, velX, velY, lifespan);
    }

    // update each particle in list using provided time and remove dead particles
    public void updateParticles(double t) {
        List<Particle> deadParticles = new ArrayList<>(); // dead Particles List

        for (Particle particle : particles) {
            particle.update(t);

            if (!particle.isAlive()) {
                deadParticles.add(particle);
            }
        }

        particles.removeAll(deadParticles); // remove all dead particles from PARTICLES LIST
    }

    // getter for list of particles
    public List<Particle> getParticles() {
        return particles;
    }
}
