package car.service.commands;

import car.model.Car;
import car.repository.FileFacade;
import java.util.List;
import java.util.Scanner;

public class AddCarCommand implements Command {
    private final Scanner scanner;
    private final FileFacade fileFacade;

    public AddCarCommand(Scanner scanner, FileFacade fileFacade) {
        this.scanner = scanner;
        this.fileFacade = fileFacade;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Добавление автомобиля ===");
        System.out.print("Модель: ");
        String model = scanner.nextLine();

        System.out.print("Год выпуска: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Госномер: ");
        String licensePlate = scanner.nextLine();

        List<Car> cars = fileFacade.loadCars();
        int newId = fileFacade.getLastCarId() + 1;
        cars.add(new Car(newId, model, year, licensePlate));
        fileFacade.saveCars(cars);

        System.out.println("Автомобиль добавлен! ID: " + newId);
    }

    @Override
    public String getDescription() {
        return "Добавить автомобиль";
    }
}