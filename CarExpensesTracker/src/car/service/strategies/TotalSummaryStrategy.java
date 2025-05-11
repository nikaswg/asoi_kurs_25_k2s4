package car.service.strategies;

import car.model.Expense;
import java.math.BigDecimal;
import java.util.List;

public class TotalSummaryStrategy implements SummaryStrategy {
    @Override
    public void execute(List<Expense> expenses) {
        BigDecimal total = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("\nОбщая сумма всех расходов: %.2f\n", total);
    }

    @Override
    public String getName() {
        return "Общая сумма расходов";
    }
}