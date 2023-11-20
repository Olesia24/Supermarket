package practice.supermarket.dao;

import practice.supermarket.model.Product;

public interface Supermarkett extends Iterable<Product>{
    boolean addProduct(Product product);
    Product removeProduct(long barCode);
    Product findByBarcode(long barCode);
    Iterable<Product> findByCategory(String category);
    Iterable<Product> findByBrand(String brand);
    Iterable<Product> findProductWithExpireDate();
    int skuQuantity(); //количество складских единиц 1 продукт = 1 sku
}
