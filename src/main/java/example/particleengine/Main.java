package example.particleengine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.Duration;

public class Main extends Application {
    private Emitter emitter;
    private Canvas canvas; // canvas on which I render particles

    public static void main(String[] args) {
        launch(args); // start application javafx
    }
    double canvasX=800;
    double canvasY=600;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Particle Smoke Effect Engine");

        StackPane root = new StackPane();
        canvas = new Canvas(canvasX, canvasY);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, canvasX, canvasY, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();

        initialize();
        startSimulation();
    }

    // initialize method for emitter
    private void initialize() {
        emitter = new Emitter(canvasX/2, canvasY-100, 1000);  // Adjust emitter position and particle emission rate
    }

    // gc for canvas and set timer for handle method to calculate time to call update and render
    private void startSimulation() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime) {
                long nanoDuration = currentNanoTime - lastNanoTime;

                if (nanoDuration > 0) {
                    Duration duration = Duration.ofNanos(nanoDuration);
                    double timeT = duration.toMillis() / 1000.0;  // Convert to seconds

                    lastNanoTime = currentNanoTime;

                    update(timeT);
                    render(gc);
                }
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
        gc.setFill(Color.LIGHTGRAY);  // Set the color of the emitter
        gc.fillOval(emitter.getPosX(), emitter.getPosY(), 40, 40);  // Adjust the size of the emitter
    }
}
