package car.service.commands;

import car.model.Car;
import car.model.Expense;
import car.model.ExpenseType;
import car.repository.FileFacade;
import java.util.List;

public class ShowAllExpensesCommand implements Command {
    private final FileFacade fileFacade;

    public ShowAllExpensesCommand(FileFacade fileFacade) {
        this.fileFacade = fileFacade;
    }

    @Override
    public void execute() {
        List<Expense> expenses = fileFacade.loadExpenses();
        List<Car> cars = fileFacade.loadCars();
        List<ExpenseType> types = fileFacade.loadExpenseTypes();

        System.out.println("\n=== Все расходы ===");
        System.out.println("+----+------------+-------------------+-----------------+--------+----------------+");
        System.out.println("| ID |    Дата    |    Автомобиль     |   Тип расхода   | Сумма  |   Описание     |");
        System.out.println("+----+------------+-------------------+-----------------+--------+----------------+");

        for (Expense expense : expenses) {
            Car car = findCarById(cars, expense.getCarId());
            ExpenseType type = findTypeById(types, expense.getTypeId());

            String carInfo = car != null ? car.getModel() + " (" + car.getLicensePlate() + ")" : "Неизвестно";
            String typeInfo = type != null ? type.getName() : "Неизвестно";

            System.out.printf("| %2d | %10s | %-17s | %-15s | %6.2f | %-14s |\n",
                    expense.getId(),
                    expense.getDate(),
                    carInfo,
                    typeInfo,
                    expense.getAmount(),
                    expense.getDescription());
        }

        System.out.println("+----+------------+-------------------+-----------------+--------+----------------+");
    }

    private Car findCarById(List<Car> cars, int id) {
        return cars.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private ExpenseType findTypeById(List<ExpenseType> types, int id) {
        return types.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public String getDescription() {
        return "Показать все расходы";
    }
}