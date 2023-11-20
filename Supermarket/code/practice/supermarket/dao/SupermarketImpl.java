package practice.supermarket.dao;

import practice.supermarket.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SupermarketImpl implements Supermarkett{
    private Collection<Product> products = new ArrayList<>();//здесь можно ставить List, ArrayList, Collection

    @Override
    public boolean addProduct(Product product) {
        if (product == null || products.contains(product)) {//contains это метод нашего листа он проверяет содержит ли он наш продукт
            return false;
        } return products.add(product);
    }
    @Override
    public Product removeProduct(long barCode) {
        Product removed = findByBarcode(barCode);//нашли продукт по баркоду
        products.remove(removed);                 //удалили из списка
        return removed;                           //вернули из метода тот что удалили
    }
    @Override
    public Product findByBarcode(long barCode) {
        for (Product p: products) {
            if (p.getBarCode()==barCode){ //сравниваем код что пришел на вход с там что есть
                return p;
            }
        } return null;
    }

    @Override
    public Iterable<Product> findByCategory(String category) {
        return findByPredicate(p -> category.equalsIgnoreCase(p.getCategory()));
    }

    @Override
    public Iterable<Product> findByBrand(String brand) {
        return findByPredicate(p -> brand.equalsIgnoreCase(p.getBrand()));//IgnoreCase-игнорируем все условия важно что буквы одинаковые
    }

    @Override
    public Iterable<Product> findProductWithExpireDate() {
        LocalDate currentDay = LocalDate.now();
        return findByPredicate(p -> currentDay.isAfter(p.getDate()) );
    }
    private Iterable<Product> findByPredicate(Predicate<Product>predicate){
        List<Product> res = new ArrayList<>();
        //перебираем весь список продуктов находим тех кто удовлетв условию предиката
        for (Product p:products) {
            if (predicate.test(p)){
                res.add(p);
            }
        } return res;
    }

    @Override
    public int skuQuantity() {
        return products.size();
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator(); //берем итератор из ArrayList
    }
}
