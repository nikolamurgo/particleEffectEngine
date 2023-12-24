package example.particleengine;

import java.util.ArrayList;
import java.util.List;

public class Emitter {
    private double posX, posY;  // Emitter position
    private int particlesPerSecond;
    private List<Particle> particles;

    public Emitter(double posX, double posY, int particlesPerSecond) {
        this.posX = posX;
        this.posY = posY;
        this.particlesPerSecond = particlesPerSecond;
        this.particles = new ArrayList<>();
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void emitParticles(double time) {
        for (int i = 0; i < particlesPerSecond * time; i++) {
            Particle particle = createParticle();
            particles.add(particle);
        }
    }

    private Particle createParticle() {
        double velX = Math.random() * 100 - 50;  // Random velocity for demonstration
        double velY = Math.random() * 100 + 50;
        double lifespan = Math.random() * 2 + 3;  // Random lifespan

        return new Particle(posX, posY, velX, velY, lifespan);
    }

    public void updateParticles(double deltaTime) {
        List<Particle> deadParticles = new ArrayList<>();

        for (Particle particle : particles) {
            particle.update(deltaTime);

            if (!particle.isAlive()) {
                deadParticles.add(particle);
            }
        }

        particles.removeAll(deadParticles);
    }

    public List<Particle> getParticles() {
        return particles;
    }
}
