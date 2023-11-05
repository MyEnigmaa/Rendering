package engine;

import org.lwjgl.Version;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    int width;
    int height;
    String title;
    private static Window window = null;

    private long glfwWindow;
    public Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Rendering";
    }

    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    private void init(){
        if(!glfwInit()) {
            throw new IllegalStateException("Unable to initialize");
        }
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        glfwWindow = glfwCreateWindow(width, height, title,NULL,NULL);
        if ( glfwWindow == NULL ){
            throw new RuntimeException("Failed to create the GLFW window");
        }
        // Make the OpenGL context current
        glfwMakeContextCurrent(glfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);
        // Make the window visible
        glfwShowWindow(glfwWindow);
    }



    private void loop(){
        GL.createCapabilities();
        // Set the clear color
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        while ( !glfwWindowShouldClose(glfwWindow) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            glfwSwapBuffers(glfwWindow); // swap the color buffers

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }

    }
    public void run(){
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }




}
