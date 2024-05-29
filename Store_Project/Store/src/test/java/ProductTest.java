import org.junit.Before;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import org.project.Product;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {
    private Product product;

    @Before
    public void setUp() {
        product = new Product(1, "Test Product", 10.0, "Category A", LocalDate.now().plusDays(10), 5);
    }

    @Test
    public void testConstructor() {
        assertNotNull(product);
        assertEquals(1, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals(10.0, product.getUnitDeliveryPrice(), 0.0001);
        assertEquals("Category A", product.getCategory());
        assertEquals(LocalDate.now().plusDays(10), product.getExpirationDate());
        assertEquals(5, product.getQuantity());


    }

    @Test
    public void testGettersAndSetters() {
        product.setId(2);
        assertEquals(2, product.getId());

        product.setName("Updated Product");
        assertEquals("Updated Product", product.getName());

        product.setUnitDeliveryPrice(15.0);
        assertEquals(15.0, product.getUnitDeliveryPrice(), 0.001);

        product.setCategory("Category B");
        assertEquals("Category B", product.getCategory());

        product.setExpirationDate(LocalDate.now().plusDays(20));
        assertEquals(LocalDate.now().plusDays(20), product.getExpirationDate());

        product.setQuantity(10);
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testCalculateSellingPriceNoDiscount() {
        double sellingPrice = product.calculateSellingPrice(20, 15, 10);
        assertEquals(12.0, sellingPrice, 0.001); // 10 + 20% of 10 = 12
    }

    @Test
    public void testCalculateSellingPriceWithDiscount() {
        double sellingPrice = product.calculateSellingPrice(20, -5, 10);
        assertEquals(10.8, sellingPrice, 0.001); // 12 - 10% of 12 = 10.8
    }

    @Test
    public void testCalculateSellingPriceOnExpirationDate() {
        double sellingPrice = product.calculateSellingPrice(20, 10, 50);
        assertEquals(6.0, sellingPrice, 0.001); // 12 - 50% of 12 = 6
    }
}