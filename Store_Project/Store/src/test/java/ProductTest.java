
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import org.project.Product;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product("TestProduct", 10.0, Product.Category.FOOD, LocalDate.of(2023, 12, 31), 100);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("TestProduct", product.getName());
        assertEquals(10.0, product.getUnitDeliveryPrice());
        assertEquals(Product.Category.FOOD, product.getCategory());
        assertEquals(LocalDate.of(2023, 12, 31), product.getExpirationDate());
        assertEquals(100, product.getQuantity());
    }

    @Test
    void testSetName() {
        product.setName("NewName");
        assertEquals("NewName", product.getName());
    }

    @Test
    void testSetNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> product.setName(null));
        assertThrows(IllegalArgumentException.class, () -> product.setName(""));
    }

    @Test
    void testSetUnitDeliveryPrice() {
        product.setUnitDeliveryPrice(15.0);
        assertEquals(15.0, product.getUnitDeliveryPrice());
    }

    @Test
    void testSetUnitDeliveryPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> product.setUnitDeliveryPrice(-1.0));
    }

    @Test
    void testSetCategory() {
        product.setCategory(Product.Category.NON_FOOD);
        assertEquals(Product.Category.NON_FOOD, product.getCategory());
    }

    @Test
    void testSetCategoryThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> product.setCategory(null));
    }

    @Test
    void testSetExpirationDate() {
        LocalDate newDate = LocalDate.of(2024, 1, 1);
        product.setExpirationDate(newDate);
        assertEquals(newDate, product.getExpirationDate());
    }

    @Test
    void testSetExpirationDateThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> product.setExpirationDate(null));
    }

    @Test
    void testSetQuantity() {
        product.setQuantity(200);
        assertEquals(200, product.getQuantity());
    }

    @Test
    void testSetQuantityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> product.setQuantity(-1));
    }

    @Test
    void testToString() {
        String expected = "Product{id=" + product.getId() + ", name='TestProduct', unitDeliveryPrice=10.0, category=FOOD, expirationDate=2023-12-31, quantity=100}";
        assertEquals(expected, product.toString());
    }
}