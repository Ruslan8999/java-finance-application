## This is my first project "Financial Application"

My application **can**:
1. Calculate expenses.
2. Distribute expenses by categories.
3. Сonverts Russian rubles to any currency specified by the user (USD, EUR, JPY or another)
4. Requests in real time through the API the current exchange rate for conversion.

The application was written in Java. Code example:
```java
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер остатка на вашем счёте:");
        double balance = scanner.nextDouble();

        FinanceApplication fin = new FinanceApplication(balance, scanner);
        ...
    }
}
```
------
