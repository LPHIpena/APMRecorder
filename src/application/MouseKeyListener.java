package application;

import java.util.HashMap;
import java.util.Map;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

// mutable int class so that we can apply a count to key presses
// count is saved in the HashMap
class MutableInt {

	int count = 1;

	public void increment() {
		++count;
	}

	public int get() {
		return count;
	}
}

public class MouseKeyListener implements NativeKeyListener, NativeMouseInputListener {

	// Class must be implemented as singleton because of the issue with
	// pausing and restarting the listener. When the app is paused and restarted
	// another listener is added. This double all key and mouse events counts.
	// by implementing the class as a singleton we can get around this issue.
	private static MouseKeyListener instance;

	// map to track each time specific key is pressed.
	public static Map<Integer, MutableInt> log;
	public static int mouseClickCount;

	private MouseKeyListener() {
		log = new HashMap<Integer, MutableInt>();
		mouseClickCount = 0;
	}

	static {
		instance = new MouseKeyListener();
	}

	public static MouseKeyListener getInstance() {
		return instance;
	}

	// *********************
	// * Key input events. *
	// *********************
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO: code for when CTRL + KEY is pressed

		int key = arg0.getKeyCode();
		System.out.println("Pressed: " + key + ":" + NativeKeyEvent.getKeyText(key));
		if (log.get(key) == null) {
			log.put(key, new MutableInt());
		} else {
			MutableInt count = log.get(key);
			count.increment();
		}

	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// ***********************
	// * Mouse input events. *
	// ***********************
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		mouseClickCount++;
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
	}

}
