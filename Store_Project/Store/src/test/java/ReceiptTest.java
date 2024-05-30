import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private Receipt receipt;
    private Cashier cashier;
    private List<Product> products;


    @Before
    public void setUp() {
        cashier = new Cashier(1, "Peter Petrov", 2000);
        products = new ArrayList<>();
    }


    @Test
    public void testConstructor() {
        int receiptNumber = 1;
        double totalAmount = 0.0;

        receipt = new Receipt(receiptNumber, cashier, products, totalAmount);

        assertEquals(receiptNumber, receipt.getReceiptNumber());
        assertEquals(cashier, receipt.getCashier());
        assertNotNull(receipt.getDateTime());
        assertEquals(0, receipt.getProducts().size());
        assertEquals(totalAmount, receipt.getTotalAmount(), 0.01);
    }

    @Test
    public void testAddProduct() throws InsufficientQuantityException, ExpiredProductException {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        Product product = new Product(1, "Test Product", 10.0, "Category", LocalDate.now().plusDays(1), 10); // Assuming Product class has this constructor
        receipt.addProduct(product, 1, 10.0);
        assertEquals(9, product.getQuantity());
        assertEquals(1, receipt.getProducts().size());
    }

    @Test
    public void testAddProductInsufficientQuantity() {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        Product product = new Product(1, "Test Product", 10.0, "Category", LocalDate.now().plusDays(1), 10);

        InsufficientQuantityException thrown = assertThrows(
                InsufficientQuantityException.class,
                () -> receipt.addProduct(product, 20, 10.0),
                "Expected addProduct() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Insufficient quantity"));
    }

    @Test
    public void testAddProductExpired() {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        Product expiredProduct = new Product(2, "Expired Product", 10.0, "Category", LocalDate.now().minusDays(1), 10);

        ExpiredProductException thrown = assertThrows(
                ExpiredProductException.class,
                () -> receipt.addProduct(expiredProduct, 1, 10.0),
                "Expected addProduct() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Product has expired"));
    }

    @Test
    public void testCalculateTotal() throws InsufficientQuantityException, ExpiredProductException {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        Product product1 = new Product(3, "Product 1", 10.0, "Category", LocalDate.now().plusDays(1), 10);
        Product product2 = new Product(4, "Product 2", 20.0, "Category", LocalDate.now().plusDays(1), 10);
        receipt.addProduct(product1, 1, 10.0);
        receipt.addProduct(product2, 1, 20.0);
        receipt.calculateTotal();
        double expectedTotal = product1.calculateSellingPrice(20, 5, 10) + product2.calculateSellingPrice(20, 5, 10);
        assertEquals(expectedTotal, receipt.getTotalAmount(), 0.01);
    }

    @Test
    public void testPrintReceipt() {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        receipt.printReceipt();
    }

    @Test
    public void testSaveAndReadFromFile() throws IOException, ClassNotFoundException {
        Receipt receipt = new Receipt(1, cashier, products, 0.0);
        Product product = new Product(1, "Test Product", 10.0, "Category", LocalDate.now().plusDays(1), 10);
        try {
            receipt.addProduct(product, 1, 10.0);
        } catch (InsufficientQuantityException e) {
            throw new RuntimeException(e);
        } catch (ExpiredProductException e) {
            throw new RuntimeException(e);
        }
        receipt.calculateTotal();
        receipt.saveToFile();

        Receipt readReceipt = Receipt.readFromFile("receipt_1.txt");
        assertNotNull(readReceipt);
        assertEquals(receipt.getReceiptNumber(), readReceipt.getReceiptNumber());
        assertEquals(receipt.getCashier().getName(), readReceipt.getCashier().getName());
        assertEquals(receipt.getTotalAmount(), readReceipt.getTotalAmount(), 0.01);
    }

}
