package car;

import car.model.ExpenseType;
import car.repository.FileFacade;
import car.repository.FileLogger;
import car.service.commands.*;
import car.service.strategies.TotalSummaryStrategy;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileFacade fileFacade = new FileFacade();
        fileFacade.addObserver(new FileLogger());

        initializeDefaultData(fileFacade);

        List<Command> commands = new ArrayList<>();
        commands.add(new AddCarCommand(scanner, fileFacade));
        commands.add(new AddExpenseCommand(scanner, fileFacade));
        commands.add(new ShowAllCarsCommand(fileFacade)); // Новая команда
        commands.add(new ShowAllExpensesCommand(fileFacade)); // Исправлено - убран scanner
        commands.add(new SummaryCommand(fileFacade, new TotalSummaryStrategy()));

        while (true) {
            printMenu(commands);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Исправлено - было scanner.nextInt()

            if (choice == commands.size() + 1) {
                break;
            }

            if (choice > 0 && choice <= commands.size()) {
                commands.get(choice - 1).execute();
            } else {
                System.out.println("Неверный выбор!");
            }
        }

        System.out.println("Программа завершена.");
        scanner.close();
    }

    private static void printMenu(List<Command> commands) {
        System.out.println("\n=== Учет расходов на автомобиль ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.println((i + 1) + ". " + commands.get(i).getDescription());
        }
        System.out.println((commands.size() + 1) + ". Выход");
        System.out.print("Выберите действие: ");
    }

    private static void initializeDefaultData(FileFacade fileFacade) {
        if (fileFacade.loadExpenseTypes().isEmpty()) {
            List<ExpenseType> defaultTypes = Arrays.asList(
                    new ExpenseType(1, "Топливо", "Заправка бензина, дизеля"),
                    new ExpenseType(2, "Ремонт", "Ремонтные работы"),
                    new ExpenseType(3, "Страховка", "ОСАГО, КАСКО")
            );
            fileFacade.saveExpenseTypes(defaultTypes);
        }
    }
}