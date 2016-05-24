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

// enum to track whether to print keyCode or keyChar
enum MapKeyMode {
	PRESSED, TYPED
};

class KeyCode {
	private MapKeyMode state;
	private int intCode;
	private int charCode;

	// getters
	public MapKeyMode getMapKeyMode() {
		return state;
	}

	public int getIntCode() {
		return intCode;
	}

	public int getCharCode() {
		return intCode;
	}

	// setters
	public void setMapKeyMode(MapKeyMode state) {
		this.state = state;
	}

	public void setIntCode(int code) {
		intCode = code;
	}

	public void setCharCode(int code) {
		charCode = code;
	}
}

public class MouseKeyListener implements NativeKeyListener, NativeMouseInputListener {

	// map to track each time specific key is pressed.
	public static Map<Integer, MutableInt> log = new HashMap<Integer, MutableInt>();

	// *********************
	// * Key input events. *
	// *********************
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO: code for when CTRL + KEY is pressed
		//loadKeyEventToMap(arg0);
		/*
		 * int id = e.getID(); String keyString; if (id == KeyEvent.KEY_TYPED) {
		 * 
		 * } else {
		 * 
		 * }
		 */

		int key = arg0.getKeyCode();
		System.out.println("Pressed: " + key + ":" + arg0.getRawCode());
		if (log.get(key) == null) {
			// System.out.println("key pressed: " + key + " is null");
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
		// if key is typed we use object->char
		// rather than object->int
		/*
		 * String keyCode = NativeKeyEvent.getKeyText(arg0.getKeyCode());
		 * 
		 * if (log.get(keyCode) == null) { System.out.println("key: " + keyCode
		 * + " is null"); log.put(keyCode, new MutableInt()); } else {
		 * MutableInt count = log.get(keyCode); count.increment(); }
		 */
	}

	private void loadKeyEventToMap(NativeKeyEvent e) {
		int id = e.getID();
		String keyString;
		if (id == NativeKeyEvent.NATIVE_KEY_TYPED) {
			char c = e.getKeyChar();
			keyString = "key character = '" + c + "'";
		} else {
			int keyCode = e.getKeyCode();
			keyString = "key code = " + keyCode + "(" + NativeKeyEvent.getKeyText(keyCode) + ")";
		}

	}

	// ***********************
	// * Mouse input events. *
	// ***********************
	@Override
	public void nativeMouseClicked(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMousePressed(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	// TODO: function to add all inputs from map and calculate APM

}
