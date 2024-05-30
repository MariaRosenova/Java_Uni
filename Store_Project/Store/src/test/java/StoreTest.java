import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.project.Product;
import org.project.Receipt;
import org.project.Store;
import org.project.Cashier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    private Store store;
    private Product product1;
    private Product product2;
    private Cashier cashier1;
    private Cashier cashier2;


    @Before
    public void setUp() {
        store = new Store();
        product1 = new Product(1, "Product1", 10.0, "Category1", LocalDate.of(2024, 12, 31), 5);
        product2 = new Product(2, "Product2", 20.0, "Category2", LocalDate.of(2024, 12, 31), 10);
        cashier1 = new Cashier(1, "Cashier1", 3000.0);
        cashier2 = new Cashier(2, "Cashier2", 3500.0);
    }

    @Test
    public void testAddProduct() {
        store.addProduct(product1);
        assertTrue(store.getInventory().contains(product1));
    }

    @Test
    public void testRemoveProduct() {
        store.addProduct(product1);
        store.removeProduct(product1);
        assertFalse(store.getInventory().contains(product1));
    }

    @Test
    public void testAddCashier() {
        store.addCashier(cashier1);
        assertTrue(store.getCashiers().contains(cashier1));
    }

    @Test
    public void testRemoveCashier() {
        store.addCashier(cashier1);
        store.removeCashier(cashier1);
        assertFalse(store.getCashiers().contains(cashier1));
    }

    @Test
    public void testSetCashiers() {
        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(cashier1);
        cashiers.add(cashier2);
        store.setCashiers(cashiers);
        assertEquals(cashiers, store.getCashiers());
    }

    @Test
    public void testSetInventory() {
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        store.setInventory(products);
        assertEquals(products, store.getInventory());
    }

    @Test
    public void testSetReceipts() {
        List<Receipt> receipts = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        receipts.add(new Receipt(1, cashier1, products, 1));
        receipts.add(new Receipt(2, cashier2, products, 2));
        store.setReceipts(receipts);
        assertEquals(receipts, store.getReceipts());
    }
}
