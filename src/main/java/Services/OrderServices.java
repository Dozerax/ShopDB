package Services;

import Repository.*;

public class OrderServices {
    private static Repository orderRepository;

    private OrderServices() {
    }

    public static Repository getInstance() {
        if (orderRepository == null) {
            orderRepository = new OrderRepositoryImpl();
        }
        return orderRepository;
    }
}