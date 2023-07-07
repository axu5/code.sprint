import controller.SystemController;

public class App {
    public static void main(String[] args) {
        SystemController controller = new SystemController("CLI");
        controller.start();
    }
}
