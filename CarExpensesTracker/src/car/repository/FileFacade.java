package car.repository;

import car.model.Car;
import car.model.Expense;
import car.model.ExpenseType;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileFacade {
    private static final String DATA_DIR = "data/";
    private final List<RepositoryObserver> observers = new ArrayList<>();

    public FileFacade() {
        new File(DATA_DIR).mkdirs();
    }

    public void addObserver(RepositoryObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (RepositoryObserver observer : observers) {
            observer.update(message);
        }
    }

    public void saveCars(List<Car> cars) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "cars.dat"))) {
            oos.writeObject(cars);
            notifyObservers("Список автомобилей сохранён");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Car> loadCars() {
        File file = new File(DATA_DIR + "cars.dat");
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Car>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveExpenseTypes(List<ExpenseType> types) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "types.dat"))) {
            oos.writeObject(types);
            notifyObservers("Список типов расходов сохранён");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<ExpenseType> loadExpenseTypes() {
        File file = new File(DATA_DIR + "types.dat");
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<ExpenseType>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveExpenses(List<Expense> expenses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_DIR + "expenses.dat"))) {
            oos.writeObject(expenses);
            notifyObservers("Список расходов сохранён");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Expense> loadExpenses() {
        File file = new File(DATA_DIR + "expenses.dat");
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public int getLastCarId() {
        List<Car> cars = loadCars();
        return cars.isEmpty() ? 0 : cars.get(cars.size() - 1).getId();
    }

    public int getLastExpenseTypeId() {
        List<ExpenseType> types = loadExpenseTypes();
        return types.isEmpty() ? 0 : types.get(types.size() - 1).getId();
    }

    public int getLastExpenseId() {
        List<Expense> expenses = loadExpenses();
        return expenses.isEmpty() ? 0 : expenses.get(expenses.size() - 1).getId();
    }
}