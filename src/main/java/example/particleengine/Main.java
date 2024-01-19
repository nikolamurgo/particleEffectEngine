package example.particleengine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private Emitter emitter;
    private Canvas canvas; // canvas on which i render particles

    public static void main(String[] args) {
        launch(args); // start application javafx
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Particle Effect Engine");

        StackPane root = new StackPane();
        canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, 800, 600, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        initialize();
        startSimulation();
    }

    // initialize method for emitter
    private void initialize() {
        emitter = new Emitter(400, 580, 3000);  // Adjust emitter position and particle emission rate
    }

    // gc for canvas and set timer for handle method to calculate time to call update and render
    private void startSimulation() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime(); // i have to figure how to change this in something else

            @Override
            public void handle(long currentNanoTime) {
                double timeT = (currentNanoTime - lastNanoTime) / 1e9;  // Convert to seconds
                lastNanoTime = currentNanoTime;

                update(timeT);
                render(gc);
            }
        }.start();
    }

    private void update(double timeT) {
        emitter.emitParticles(timeT);
        emitter.updateParticles(timeT);
    }

    private void render(GraphicsContext gc) {
        // Clear the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        renderEmitter(gc);
        // Draw particles
        for (Particle particle : emitter.getParticles()) {
            particle.draw(gc);
        }
    }
    private void renderEmitter(GraphicsContext gc) {
        // Draw the emitter as a filled circle with a different color
        gc.setFill(Color.WHITESMOKE);  // Set the color of the emitter (e.g., blue)
        gc.fillOval(emitter.getPosX()-5, emitter.getPosY()-5, 20, 20);  // Adjust the size of the emitter
    }
}
