package application;

import java.util.HashMap;

import java.util.Map;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
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

//public class 

public class MouseKeyListener implements NativeKeyListener, NativeMouseInputListener {

	// map to track each time specific key is pressed.
	public static Map<Integer, MutableInt> log = new HashMap<Integer, MutableInt>();

	// *********************
	// * Key input events. *
	// *********************
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// TODO: code for when CTRL + KEY is pressed
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// if key is typed we use object->char 
		// rather than object->int
		int keyCode = arg0.getKeyChar();

		if (log.get(keyCode) == null) {
			System.out.println("key: " + keyCode + " is null");
			log.put(keyCode, new MutableInt());
		} else {
			MutableInt count = log.get(keyCode);
			count.increment();
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

}
