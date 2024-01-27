package example.particleengine;

import java.util.ArrayList;
import java.util.List;

public class Emitter {
    private double posX, posY;  // Emitter position
    private int emittedParticles; // how many particles the emitter should emit per second
    private List<Particle> particles; // store particles in arraylist (these are alive active particles)

    // constructor
    public Emitter(double posX, double posY, int emittedParticles) {
        this.posX = posX;
        this.posY = posY;
        this.emittedParticles = emittedParticles;
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

    // generate and add particles to ArrayList based on particles per second
    public void emitParticles(double time) {
        for (int i = 0; i < emittedParticles * time; i++) {
            Particle particle = createParticle();
            particles.add(particle);
        }
    }

    // create new particle with random velocity and lifespan
    private Particle createParticle() {
        double velX = Math.random() * 50 - 25;  // Random velocity for demonstration
        double velY = Math.random() * 50 + 25;
        double lifespan = (Math.random() + 2) * 2;  // Random lifespan between 1 and 2

        // Offset particle position to the top of the emitter
        double particlePosX = posX + 5 + Math.random() * 10;
        double particlePosY = posY - 20 + Math.random() * 10;  // Adjust the offset

        return new Particle(particlePosX, particlePosY, velX, velY, lifespan);
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
