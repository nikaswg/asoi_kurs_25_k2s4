package contacts.service.commands;

import contacts.repository.ContactRepository;
import java.util.Scanner;

public class DeleteContactCommand implements Command {
    private final Scanner scanner;
    private final ContactRepository repository;

    public DeleteContactCommand(Scanner scanner, ContactRepository repository) {
        this.scanner = scanner;
        this.repository = repository;
    }

    @Override
    public void execute() {
        try {
            System.out.println("\n=== Удаление контакта ===");
            System.out.print("Введите телефон контакта для удаления: ");
            String phone = scanner.nextLine();

            // Получаем список всех контактов
            var contacts = repository.getAllContacts();

            // Ищем контакт по телефону
            var contactToRemove = contacts.stream()
                    .filter(c -> c.getPhone().equals(phone))
                    .findFirst();

            if (contactToRemove.isPresent()) {
                contacts.remove(contactToRemove.get());
                repository.saveContacts();
                System.out.println("✅ Контакт успешно удален");
            } else {
                System.out.println("❌ Контакт с таким телефоном не найден");
            }

        } catch (Exception e) {
            System.err.println("❌ Ошибка при удалении контакта: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Удалить контакт";
    }
}