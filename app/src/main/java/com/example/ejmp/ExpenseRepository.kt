import com.example.ejmp.Model.Expense

object ExpenseRepository {
    private val expenses: MutableList<Expense> = mutableListOf(
        Expense(1, "PC", "09/06/2023", 200.000),
        Expense(2, "RTX", "08/06/2023", 100.000),
        Expense(3, "Casa", "02/07/2023", 950.000),

    )


    fun getAll(): List<Expense> = expenses

    fun search(query: String): List<Expense> {
        if (query.isBlank())
            return emptyList()
        return expenses.filter { expense ->
            val regex = query.toRegex(RegexOption.IGNORE_CASE)
            regex.containsMatchIn(expense.description)
        }
    }
}
