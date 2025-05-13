package contacts.service.commands;

import contacts.model.Contact;
import contacts.model.Group;
import contacts.repository.ContactRepository;
import contacts.service.exceptions.DuplicateContactException;
import java.util.Scanner;

public class AddContactCommand implements Command {
    private final Scanner scanner;
    private final ContactRepository repository;

    public AddContactCommand(Scanner scanner, ContactRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n=== Добавление контакта ===");

            System.out.print("Имя: ");
            String name = scanner.nextLine();

            System.out.print("Телефон: ");
            String phone = scanner.nextLine();

            System.out.print("Email (не обязательно): ");
            String email = scanner.nextLine();
            if (email.isEmpty()) email = null;

            System.out.println("Группы: ");
            for (int i = 0; i < Group.values().length; i++) {
                System.out.printf("%d. %s\n", i+1, Group.values()[i]);
            }
            System.out.print("Выберите группу (1-4): ");
            int groupChoice = Integer.parseInt(scanner.nextLine());
            Group group = Group.values()[groupChoice-1];

            Contact contact = new Contact(name, phone, email, group);
            repository.addContact(contact);
            System.out.println("✅ Контакт успешно добавлен!");

        } catch (DuplicateContactException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Ошибка ввода: " + e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("❌ Неверный номер группы");
//        } catch (NumberFormatException e) {
//            System.err.println("❌ Введите число для выбора группы");
        } catch (Exception e) {
            System.err.println("❌ Неизвестная ошибка: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Добавить контакт";
    }
}