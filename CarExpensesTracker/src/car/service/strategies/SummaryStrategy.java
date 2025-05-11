package car.service.strategies;

import car.model.Expense;
import java.util.List;

public interface SummaryStrategy {
    void execute(List<Expense> expenses);
    String getName();
}