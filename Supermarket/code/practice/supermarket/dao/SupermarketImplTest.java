package practice.supermarket.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import practice.supermarket.model.Product;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SupermarketImplTest {
    Supermarkett supermarket;
    Product[] products;
    LocalDate now = LocalDate.now();

    @BeforeEach
    void setUp() {
        supermarket = new SupermarketImpl();
        products = new Product[5];
        products[0] = new Product(5678, "Stolen", "Sweets", "Brand 1", 3.5, now.minusDays(5));
        products[1] = new Product(3456, "Salami", "Meat", "Brand 2", 10, now.plusDays(10));
        products[2] = new Product(9856, "Salmon", "Fish", "Brand 3", 14.6, now.plusDays(1));
        products[3] = new Product(2345, "Porridge", "Porridge", "Brand 4", 4.5, now.plusDays(15));
        for (int i = 0; i <products.length ; i++) {
            supermarket.addProduct(products[i]);
        }
    }

    @Test
    void addProductTest() {
        assertFalse(supermarket.addProduct(null));
        assertFalse(supermarket.addProduct(products[1]));
        Product product5 = new Product(5656, "Porridge", "Porridge", "Brand 4", 4.5,now.minusDays(15));
        assertTrue(supermarket.addProduct(product5));
        assertEquals(5, supermarket.skuQuantity());
        Product product6 = new Product(5656, "Porridge", "Porridge", "Brand 4", 4.5,now.minusDays(15));
        assertFalse(supermarket.addProduct(product6));
        Product product7 = new Product(9000, "Porridge", "Porridge", "Brand 4", 4.5,now.minusDays(15));
        assertTrue(supermarket.addProduct(product7));
    }

    @Test
    void removeProductTest() {
        assertEquals(products[0], supermarket.removeProduct(5678));
        assertEquals(3, supermarket.skuQuantity());
        for (Product p:supermarket) {
            System.out.println(p);
        }
    }

    @Test
    void findByBarcodeTest() {
        assertEquals(products[0], supermarket.findByBarcode(5678));
        System.out.println(products[0]);
    }

    @Test
    void findByCategoryTest() {
        Iterable<Product> foundProducts = supermarket.findByCategory("Sweets");
        assertNotNull(foundProducts);
        for (Product p :foundProducts) {
            assertEquals("Sweets", p.getCategory());
            System.out.println(foundProducts);
        }
    }

    @Test
    void findByBrandTest() {
        Iterable<Product> findBrand = supermarket.findByBrand("Brand 1");
        assertNotNull(findBrand);
        for (Product p :findBrand) {
            assertEquals("Brand 1", p.getBrand());
            System.out.println(findBrand);
        }
    }

    @Test
    void findProductWithExpireDateTest() {
        Iterable<Product> findDateExp = supermarket.findProductWithExpireDate();
        assertNotNull(findDateExp);
        for (Product p:findDateExp) {
            assertEquals(now.minusDays(5), p.getDate());
            System.out.println(findDateExp);
        }
    }
    @Test
    void skuQuantityTest() {
        assertEquals(4, supermarket.skuQuantity());
    }
}