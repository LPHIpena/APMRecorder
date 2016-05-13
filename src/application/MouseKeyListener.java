package application;

import java.util.HashMap;
import java.util.Map;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseKeyListener implements NativeKeyListener, NativeMouseInputListener {

	// map to track each time specific key is pressed.
	Map<String, Integer> log = new HashMap<String, Integer>();

	// *********************
	// * Key input events. *
	// *********************
	@Override
	public void nativeKeyPressed(NativeKeyEvent arg0) {
		// platform.runlater() should be on FX application thread
		// use Service for thread safety on THIS thread.
		if (arg0.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			/*try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e) {
				e.printStackTrace();
			}*/
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {

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
