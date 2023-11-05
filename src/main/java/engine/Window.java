package engine;

public class Window {

    int width;
    int height;
    String title;
    private static Window window = null;
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




}
