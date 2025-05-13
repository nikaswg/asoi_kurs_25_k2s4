package contacts.repository;

import contacts.model.Contact;
import contacts.service.exceptions.DuplicateContactException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private final FileStorage fileStorage;
    private final List<Contact> contacts;

    public ContactRepository(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
        this.contacts = loadContacts();
    }

    private List<Contact> loadContacts() {
        try {
            return fileStorage.readContacts();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки контактов: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void addContact(Contact newContact) throws DuplicateContactException {
        if (contacts.stream().anyMatch(c -> c.getPhone().equals(newContact.getPhone()))) {
            throw new DuplicateContactException("Контакт с таким телефоном уже существует");
        }

        contacts.add(newContact);
        saveContacts();
    }

    public List<Contact> searchContacts(String query) {
        String lowerQuery = query.toLowerCase();
        return contacts.stream()
                .filter(c -> c.getName().toLowerCase().contains(lowerQuery) ||
                        c.getPhone().contains(query))
                .toList();
    }

    public void saveContacts() {
        try {
            fileStorage.saveContacts(contacts);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения контактов: " + e.getMessage());
        }
    }

    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }


}