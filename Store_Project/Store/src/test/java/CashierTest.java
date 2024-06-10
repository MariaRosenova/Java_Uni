import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.Cashier;
import org.project.InsufficientFundsException;

import static org.junit.jupiter.api.Assertions.*;

public class CashierTest {
    private Cashier cashier;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(1, "John Doe", 3000.0);
    }

    @Test
    void testCashierCreation() {
        assertNotNull(cashier);
        assertEquals("John Doe", cashier.getName());
        assertEquals(3000.0, cashier.getSalary());
    }

    @Test
    void testGetId() {
        int initialId = cashier.getId();
        Cashier newCashier = new Cashier(2, "Jane Smith", 3200.0);
        assertEquals(initialId + 1, newCashier.getId());
    }

    @Test
    void testSetName() {
        cashier.setName("Jane Doe");
        assertEquals("Jane Doe", cashier.getName());
    }

    @Test
    void testSetSalary() {
        cashier.setSalary(3500.0);
        assertEquals(3500.0, cashier.getSalary());
    }

    @Test
    void testSetAndGetRegisterId() {
        cashier.setRegisterId(101);
        assertEquals(101, cashier.getRegisterId());
    }

    @Test
    void testProcessOrderSuccessExactAmount() {
        assertDoesNotThrow(() -> cashier.processOrder(100.0, 100.0));
    }

    @Test
    void testProcessOrderSuccessWithChange() {
        assertDoesNotThrow(() -> cashier.processOrder(100.0, 150.0));
    }

    @Test
    void testProcessOrderInsufficientFunds() {
        InsufficientFundsException exception = assertThrows(InsufficientFundsException.class, () -> {
            cashier.processOrder(100.0, 50.0);
        });
        assertEquals("The money is not enough, you have to pay $50.0 more.", exception.getMessage());
    }

    @Test
    void testToString() {
        String expected = "Cashier{id=" + cashier.getId() + ", name='John Doe', salary=3000.0, registerId=0}";
        assertEquals(expected, cashier.toString());
    }
}
