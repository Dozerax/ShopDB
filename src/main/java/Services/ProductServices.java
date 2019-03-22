package Services;

import Repository.*;

public class ProductServices {

    private static Repository productRepository;

    private ProductServices() {
    }

    public static Repository getInstance() {
        if (productRepository == null) {
            productRepository = new ProductRepositoryImpl();
        }
        return productRepository;
    }
}