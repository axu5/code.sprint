import controller.SystemController;

public class RunApp {
    public static void main(String[] args) {
        SystemController controller = new SystemController("CLI");
        controller.start();
    }
}
