package Business;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class DeliveryService extends Observable implements IDeliveryServiceProcessing {

    private HashMap<Order, Collection<MenuItem>> orders; //toate comenzile
    private HashSet<MenuItem> allProducts;               //toate produsele citite din CSV
    private static final String CSV_FILE_PATH = "./products.csv";

    public DeliveryService() {
        orders = new HashMap<>();
        allProducts = new HashSet<>();
    }

    public void readFromCSV() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
        ) {
            ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
            strategy.setType(BaseProduct.class);
            String[] memberFieldsToBindTo = {"title", "rating", "calories",
                    "protein", "fat", "sodium", "price"};
            strategy.setColumnMapping(memberFieldsToBindTo);

            CsvToBean<BaseProduct> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(strategy)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            allProducts = csvToBean.stream().map(row -> new BaseProduct(row.getTitle(), row.getRating(),
                    row.getCalories(), row.getProtein(), row.getFat(),
                    row.getSodium(), row.getPrice())).collect(Collectors.toCollection(HashSet::new));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addProduct(MenuItem item) {
        allProducts.add(item);
    }

    public boolean deleteProduct(String title) {
        for (MenuItem m : allProducts) {
            if (m.getTitle().equals(title)) {
                allProducts.remove(m);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean modifyProduct(MenuItem item) {
        String title = item.getTitle();
        MenuItem product = searchProductByName(title);
        if (product instanceof BaseProduct) {
            ((BaseProduct) product).setAllAttributes(item.getRating(),
                    item.getCalories(), item.getProtein(), item.getFat(), item.getSodium(), item.getPrice());
            return true;
        } else
            return false;

    }

    public MenuItem searchProductByName(String title) {
        MenuItem notFound = new BaseProduct("notfound", 0, 0, 0, 0, 0, 0);
        for (MenuItem m : allProducts) {
            if (m.getTitle().contains(title))
                return m;
        }
        return notFound;
    }

    @Override
    public void createOrder(List<MenuItem> orderList, Order order) {
        orders.put(order, orderList);
    }

    @Override
    public void createNewComposedProduct(List<MenuItem> itemList, String menuName) {

    }

    public HashMap<Order, Collection<MenuItem>> getOrders() {
        return orders;
    }

    public HashSet<MenuItem> getAllProducts() {
        return allProducts;
    }

    public List<MenuItem> getProductList(Order order)
    {
        return (List<MenuItem>) orders.get(order);
    }

    public int getTotalPrice(Order order)
    {int price=0;
        for(MenuItem m:orders.get(order))
            price+=m.getPrice();
        return price;
    }
}