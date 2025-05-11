package car.service.commands;

import car.model.Car;
import car.model.Expense;
import car.model.ExpenseType;
import car.repository.FileFacade;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AddExpenseCommand implements Command {
    private final Scanner scanner;
    private final FileFacade fileFacade;

    public AddExpenseCommand(Scanner scanner, FileFacade fileFacade) {
        this.scanner = scanner;
        this.fileFacade = fileFacade;
    }

    @Override
    public void execute() {
        System.out.println("\n=== Добавление расхода ===");

        // Получаем список автомобилей
        List<Car> cars = fileFacade.loadCars();
        System.out.println("Доступные автомобили:");
        for (Car car : cars) {
            System.out.println(car.getId() + ". " + car);
        }

        System.out.print("Выберите автомобиль (ID): ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        // Получаем список типов расходов
        List<ExpenseType> types = fileFacade.loadExpenseTypes();
        System.out.println("Типы расходов:");
        for (ExpenseType type : types) {
            System.out.println(type.getId() + ". " + type);
        }

        System.out.print("Выберите тип расхода (ID): ");
        int typeId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Дата (гггг-мм-дд): ");
        LocalDate date = LocalDate.parse(scanner.nextLine());

        System.out.print("Сумма: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        System.out.print("Описание: ");
        String description = scanner.nextLine();

        // Создаем и сохраняем новый расход
        List<Expense> expenses = fileFacade.loadExpenses();
        int newId = fileFacade.getLastExpenseId() + 1;
        expenses.add(new Expense(newId, carId, typeId, date, amount, description));
        fileFacade.saveExpenses(expenses);

        System.out.println("Расход добавлен! ID: " + newId);
    }

    @Override
    public String getDescription() {
        return "Добавить расход";
    }
}