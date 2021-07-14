package Business;

import java.util.Objects;

public class Order {
    private int orderID;
    private int clientID;
    private String orderDate;

    public Order(int orderID, int clientID, String orderDate) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderID == order.orderID && clientID == order.clientID && orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID, orderDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", clientID=" + clientID +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
