// Nothing to see here, just copied from somewhere at StackOverflow and customized a bit

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.glfw.GLFWvidmode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Graphics {

	private static GLFWErrorCallback errorCallback
			= Callbacks.errorCallbackPrint(System.err);

	/**
	 * This key callback will check if ESC is pressed and will close the window
	 * if it is pressed.
	 */
	private static GLFWKeyCallback keyCallback = new GLFWKeyCallback() {

		@Override
		public void invoke(long window, int key, int scancode, int action, int mods) {
			if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
				glfwSetWindowShouldClose(window, GL_TRUE);
			}
		}
	};

	public static void main(String[] args) {
		long window;
		glfwSetErrorCallback(errorCallback);
		if (glfwInit() != GL_TRUE) {
			throw new IllegalStateException("Unable to initialize GLFW");
		}
		window = glfwCreateWindow(640, 480, "Fuck yea, sistemi!", NULL, NULL);
		if (window == NULL) {
			glfwTerminate();
			throw new RuntimeException("Failed to create the GLFW window");
		}
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window,
				(GLFWvidmode.width(vidmode) - 640) / 2,
				(GLFWvidmode.height(vidmode) - 480) / 2
		);
		glfwMakeContextCurrent(window);
		GLContext.createFromCurrent();
		glfwSwapInterval(1);
		glfwSetKeyCallback(window, keyCallback);
		IntBuffer width = BufferUtils.createIntBuffer(1);
		IntBuffer height = BufferUtils.createIntBuffer(1);

		Simulazione simulazione = new Simulazione();

		while (glfwWindowShouldClose(window) != GL_TRUE) {
			glfwGetFramebufferSize(window, width, height);
			width.get();
			height.get();
			width.rewind();
			height.rewind();
			int w = width.get(), h = height.get();
			glViewport(0, 0, w, h);
			glClear(GL_COLOR_BUFFER_BIT);
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0.0f, w, h, 0f, 0f, 1f);
			glMatrixMode(GL_MODELVIEW);

			/* <code>  */
			simulazione.tick();
			/* </code> */

			glfwSwapBuffers(window);
			glfwPollEvents();
			width.flip();
			height.flip();
		}
		glfwDestroyWindow(window);
		keyCallback.release();
		glfwTerminate();
		errorCallback.release();
	}
}
