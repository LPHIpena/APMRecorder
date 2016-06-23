package application;

import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.mouse.NativeMouseEvent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application {

	private GridPane root;

	private Button startButton;
	private Button pauseButton;
	private Button printButton;
	private Button resetButton;

	private Button exitButton;

	private Text timerText;

	private String timeDisplayString;
	private long time = 0;
	private Timer timer = new Timer();
	
	private MouseKeyListener mouseKeyListener;
	private boolean isListenerAdded = false;

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("APM Recorder");
		initElements(primaryStage);

		// set the pane
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setMinSize(220, 130);
		root.setVgap(3);
		root.setHgap(3);
		root.setStyle("-fx-background-color: #336699;");

		Scene scene = new Scene(root, 230, 130);

		// add elements
		root.add(startButton, 5, 0, 2, 1);
		root.add(pauseButton, 5, 1, 2, 1);
		root.add(printButton, 3, 1, 2, 2);
		root.add(exitButton, 1, 1, 1, 1);
		root.add(timerText, 2, 1, 1, 1);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initElements(Stage primaryStage) {
		root = new GridPane();

		// ****************
		// * Start Button *
		// ****************
		startButton = new Button("Start/Resume");
		startButton.setOnAction((event) -> {
			startKeyListener();
			start();
		});

		// ****************
		// * Pause Button *
		// ****************
		pauseButton = new Button("Pause Recording");
		pauseButton.setOnAction((event) -> {
			try {
				GlobalScreen.unregisterNativeHook();
				GlobalScreen.removeNativeKeyListener(mouseKeyListener);
				GlobalScreen.removeNativeMouseListener(mouseKeyListener);
				isListenerAdded = false;
				pause();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		// ****************
		// * Reset Button *
		// ****************
		resetButton = new Button("Reset");
		resetButton.setOnAction((event) -> {
			// TODO: code for reset button
			// need to reset clear map of all values
			// reset and reset time
		});

		// ****************
		// * Print Button *
		// ****************
		printButton = new Button("Print Results");
		printButton.setOnAction((event) -> {
			for (Entry<Integer, MutableInt> entry : MouseKeyListener.log.entrySet()) {
				int key = entry.getKey();

				MutableInt value = entry.getValue();
				System.out.println("key, " + NativeKeyEvent.getKeyText(key) + " value " + value.get());
			}
			
			System.out.println("click count " + MouseKeyListener.mouseClickCount);
		});

		// ***************
		// * Exit Button *
		// ***************
		exitButton = new Button("Exit");
		exitButton.setOnAction(actionEvent -> Platform.exit());

		// Text to display timer
		timerText = new Text("00:00");

	}

	public class TimingTask extends TimerTask {
		@Override
		public void run() {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					// Long.toString(time++))
					time++;
					timeDisplayString = String.format("%02d:%02d", time / 60000, time / 1000);
					timerText.setText(timeDisplayString);
				}
			});
		}
	}

	private void pause() {
		timer.cancel();
	}

	private void start() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimingTask(), 0, 1);
	}

	private void resetTime() {
		time = 0;
	}

	private void startKeyListener() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					GlobalScreen.registerNativeHook();
					
					// disable JNative logging.
					LogManager.getLogManager().reset();
					Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
					logger.setLevel(Level.OFF);

				} catch (NativeHookException e) {
					System.err.println("There was a problem registering the native hook.");
					System.err.println(e.getMessage());

					System.exit(1);
				}
				mouseKeyListener = MouseKeyListener.getInstance(); 
				if (!isListenerAdded) {
					GlobalScreen.addNativeKeyListener(mouseKeyListener);
					GlobalScreen.addNativeMouseListener(mouseKeyListener);
					isListenerAdded = true;
				}
				
			}

		}).start();

	}

	public static void main(String[] args) throws NativeHookException {
		launch(args);
		GlobalScreen.unregisterNativeHook();

		if (!GlobalScreen.isNativeHookRegistered())
			System.out.println("it's done hoe");
	}
}
