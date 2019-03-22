package Services;

import Repository.*;

public class CustomerServices {

    private static Repository customerRepository;

    private CustomerServices() {
    }

    public static Repository getInstance() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepositoryImpl();
        }
        return customerRepository;
    }
}