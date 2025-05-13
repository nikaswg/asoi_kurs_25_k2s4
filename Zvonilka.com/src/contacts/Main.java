package contacts;

import contacts.repository.ContactRepository;
import contacts.repository.FileStorage;
import contacts.service.commands.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileStorage fileStorage = new FileStorage();
            ContactRepository repository = new ContactRepository(fileStorage);
            Scanner scanner = new Scanner(System.in);

            List<Command> commands = new ArrayList<>();
            commands.add(new AddContactCommand(scanner, repository));
            commands.add(new SearchCommand(scanner, repository));
            commands.add(new ListAllCommand(repository));
            commands.add(new DeleteContactCommand(scanner, repository));

            while (true) {
                printMenu(commands);
                try {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("q")) break;

                    int choice = Integer.parseInt(input);
                    if (choice < 1 || choice > commands.size() + 1) {
                        System.err.println("Неверный пункт меню");
                        continue;
                    }

                    if (choice == commands.size() + 1) break;
                    commands.get(choice - 1).execute();

                } catch (NumberFormatException e) {
                    System.err.println("Введите число от 1 до " + (commands.size() + 1));
                } catch (Exception e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }

            System.out.println("Работа завершена");
        } catch (Exception e) {
            System.err.println("Критическая ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printMenu(List<Command> commands) {
        System.out.println("\n=== Менеджер контактов ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d. %s\n", i+1, commands.get(i).getDescription());
        }
        System.out.printf("%d. Выход\n", commands.size()+1);
        System.out.print("Выберите действие: ");
    }
}