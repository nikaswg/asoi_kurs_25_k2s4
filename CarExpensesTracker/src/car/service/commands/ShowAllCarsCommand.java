package car.service.commands;

import car.model.Car;
import car.repository.FileFacade;
import java.util.List;

public class ShowAllCarsCommand implements Command {
    private final FileFacade fileFacade;

    public ShowAllCarsCommand(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @Override
    public void execute() {
        List<Car> cars = fileFacade.loadCars();

        System.out.println("\n=== Список автомобилей ===");
        System.out.println("+----+------------+------+----------------+");
        System.out.println("| ID |   Модель   | Год  |   Госномер     |");
        System.out.println("+----+------------+------+----------------+");

        for (Car car : cars) {
            System.out.printf("| %2d | %-10s | %4d | %-14s |\n",
                    car.getId(),
                    car.getModel(),
                    car.getYear(),
                    car.getLicensePlate());
        }

        System.out.println("+----+------------+------+----------------+");
        System.out.println("Всего автомобилей: " + cars.size());
    }

    @Override
    public String getDescription() {
        return "Показать все автомобили";
    }
}