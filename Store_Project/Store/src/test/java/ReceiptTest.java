
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {
    private Cashier cashier;
    private Store store;
    private Product product;
    private Receipt receipt;

    @BeforeEach
    void setUp() {
        cashier = new Cashier(1, "Peter Petrov", 2500);
        store = new Store();
        product = new Product("Milk", 1.5, Product.Category.FOOD, LocalDate.now().plusDays(10), 10);
        receipt = new Receipt(cashier, store);
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(receipt.getDateTime());
        assertEquals(cashier, receipt.getCashier());
        assertEquals(store, receipt.getStore());
        assertTrue(receipt.getProducts().isEmpty());
        assertEquals(0.0, receipt.getTotalAmount());
    }

    @Test
    void testAddProduct() throws InsufficientQuantityException, ExpiredProductException {
        receipt.addProduct(product, 2);
        assertEquals(1, receipt.getProducts().size());
        assertEquals(8, product.getQuantity());
        assertEquals(1.5 * 2, receipt.getTotalAmount()); // Assuming store.calculatePriceForFood returns the unit price
    }

    @Test
    void testAddProductInsufficientQuantity() {
        Product insufficientProduct = new Product("Bread", 2.0, Product.Category.FOOD, LocalDate.now().plusDays(5), 1);
        assertThrows(InsufficientQuantityException.class, () -> receipt.addProduct(insufficientProduct, 2));
    }

    @Test
    void testAddProductExpired() {
        Product expiredProduct = new Product("Cheese", 3.0, Product.Category.FOOD, LocalDate.now().minusDays(1), 5);
        assertThrows(ExpiredProductException.class, () -> receipt.addProduct(expiredProduct, 1));
    }

    @Test
    void testCalculateTotal() throws InsufficientQuantityException, ExpiredProductException {
        receipt.addProduct(product, 2);
        receipt.calculateTotal();
        assertEquals(1.5 * 2, receipt.getTotalAmount());
    }

    @Test
    void testPrintReceipt() {
        // Since printReceipt() prints to console, we will redirect the output and verify the printed content
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        receipt.printReceipt();

        String expectedOutput = "Receipt #" + receipt.getReceiptNumber() + "\n" +
                "Cashier: " + cashier.getName() + "\n" +
                "Date: " + receipt.getDateTime() + "\n" +
                "Total: " + receipt.getTotalAmount() + "\n";
        assertTrue(outContent.toString().contains(expectedOutput));

        // Reset System.out
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    void testSaveToFile() throws IOException, InsufficientQuantityException, ExpiredProductException {
        String fileName = "receipt_" + receipt.getReceiptNumber() + ".txt";
        receipt.addProduct(product, 2);
        receipt.saveToFile();

        File file = new File(fileName);
        assertTrue(file.exists());
        file.delete();
    }

    @Test
    void testReadFromFile() throws IOException, ClassNotFoundException, InsufficientQuantityException, ExpiredProductException {
        String fileName = "receipt_" + receipt.getReceiptNumber() + ".txt";
        receipt.addProduct(product, 2);
        receipt.saveToFile();

        Receipt readReceipt = Receipt.readFromFile(fileName);
        assertEquals(receipt.getReceiptNumber(), readReceipt.getReceiptNumber());
        assertEquals(receipt.getCashier().getName(), readReceipt.getCashier().getName());
        assertEquals(receipt.getDateTime(), readReceipt.getDateTime());
        assertEquals(receipt.getTotalAmount(), readReceipt.getTotalAmount());

        File file = new File(fileName);
        file.delete();
    }
}
