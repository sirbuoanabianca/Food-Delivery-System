package Business;

import java.util.List;


public interface IDeliveryServiceProcessing {
    public void addProduct(MenuItem item);
    public boolean deleteProduct(String title);
    public boolean modifyProduct(MenuItem item);

    /*Client*/
    public void createOrder(List<MenuItem> orderList,Order order);


    /*Administrator*/
    public void createNewComposedProduct(List<MenuItem> itemList,String menuName);
}
