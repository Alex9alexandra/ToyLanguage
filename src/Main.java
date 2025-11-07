import controller.Controller;
import controller.IController;
import repository.IRepository;
import repository.Repository;
import view.View;

public class Main {
    public static void main() {
        IRepository repository = new Repository();
        IController controller = new Controller(repository);
        View view = new View(controller);
        view.run();
    }
}
