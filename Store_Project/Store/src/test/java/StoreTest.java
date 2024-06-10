
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    private Store store;
    private Cashier cashier;
    private Product foodProduct;
    private Product nonFoodProduct;

    @BeforeEach
    void setUp() {
        store = new Store();
        cashier = new Cashier(1, "John Doe", 3000.0);
        foodProduct = new Product("Milk", 1.5, Product.Category.FOOD, LocalDate.now().plusDays(10), 10);
        nonFoodProduct = new Product("Shampoo", 5.0, Product.Category.NON_FOOD, null, 20);
    }

    @Test
    void testAddAndRemoveCashier() {
        store.addCashier(cashier);
        List<Cashier> cashiers = store.getCashiers();
        assertEquals(1, cashiers.size());
        assertEquals(cashier, cashiers.get(0));

        store.removeCashier(cashier.getId());
        cashiers = store.getCashiers();
        assertTrue(cashiers.isEmpty());
    }

    @Test
    void testAddAndRemoveProduct() {
        store.addProduct(foodProduct);
        List<Product> inventory = store.getInventory();
        assertEquals(1, inventory.size());
        assertEquals(foodProduct, inventory.get(0));

        store.removeProduct(foodProduct);
        inventory = store.getInventory();
        assertTrue(inventory.isEmpty());
    }

    @Test
    void testCalculatePriceForFood() {
        double price = store.calculatePriceForFood(foodProduct.getUnitDeliveryPrice(), foodProduct.getExpirationDate());
        double expectedPrice = 1.5 * (1 + store.getMarkupPercentageFood() / 100.0);
        assertEquals(expectedPrice, price);
    }

    @Test
    void testCalculatePriceForNonFood() {
        double price = store.calculatePriceForNonFood(nonFoodProduct.getUnitDeliveryPrice());
        double expectedPrice = 5.0 * (1 + store.getMarkupPercentageNonFood() / 100.0);
        assertEquals(expectedPrice, price);
    }

    @Test
    void testAddReceipt() {
        Receipt receipt = new Receipt(cashier, store);
        store.addReceipts(receipt);
        List<Receipt> receipts = store.getReceipts();
        assertEquals(1, receipts.size());
        assertEquals(receipt, receipts.get(0));
    }

    @Test
    void testGetProfit() {
        store.addProduct(foodProduct);
        Receipt receipt = new Receipt(cashier, store);
        store.addReceipts(receipt);
        double profit = store.getProfit();
        assertEquals(store.getTotalRevenue() - store.getTotalExpenses(), profit);
    }
}
