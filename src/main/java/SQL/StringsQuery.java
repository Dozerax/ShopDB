package SQL;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StringsQuery {

    private static Map<String, String> queryMap = new HashMap();
    private static ObjectMapper mapper = new ObjectMapper();

    public static void getList() throws IOException {
        queryMap = mapper.readValue(new File("src/main/resources/Query.json"), Map.class);
    }

    //DB url
    final static String DATABASE_URL = "jdbc:derby:lib/Shop";
    //Customer query
    //public final static String SELECT_ALL_CUSTOMER = queryMap.get("selectCustomer");
    public final static String SELECT_ALL_CUSTOMER = "SELECT ID, firstName, lastName, phoneNumber FROM customers";
    public final static String INSERT_CUSTOMER = "INSERT INTO customers (firstName,lastName,phoneNumber) VALUES";
    public final static String UPDATE_CUSTOMER = "UPDATE customers SET ";
    public final static String DELETE_CUSTOMER = "DELETE FROM customers WHERE ID = ";
    //Product query
    public final static String SELECT_ALL_PRODUCT = "SELECT ID, productName, price, productCode FROM products";
    public final static String INSERT_PRODUCT = "INSERT INTO products (productName,price,productCode) VALUES";
    public final static String UPDATE_PRODUCT = "UPDATE products SET ";
    public final static String DELETE_PRODUCT = "DELETE FROM products WHERE ID = ";
    //Order query
    public final static String SELECT_ALL_ORDER = "SELECT ID,cID FROM orders";
    public final static String SELECT_ORDER = "SELECT ID,pID,oID,amount FROM orderItem WHERE oID = ";
    public final static String INSERT_ORDER = "INSERT INTO orders (cID,orderNumber) VALUES";
    public final static String DELETE_ORDER = "DELETE FROM orders WHERE ID = ";
    public final static String ADD_PRODUCT = "INSERT INTO orderItem (pID,oID,amount) VALUES";
    public final static String DELETE_ORDER_ITEM = "DELETE FROM orderItem where oID = ";

    public static void print() {
        System.out.println(queryMap.get("selectCustomer"));
    }

    public static void setMap() throws IOException {
        queryMap.put("selectCustomer", SELECT_ALL_CUSTOMER);
        queryMap.put("insertCustomer", INSERT_CUSTOMER);
        queryMap.put("updateCustomer", UPDATE_CUSTOMER);
        queryMap.put("deleteCustomer", DELETE_CUSTOMER);
        queryMap.put("selectAllProduct",SELECT_ALL_PRODUCT);
        queryMap.put("insertProduct",INSERT_PRODUCT);
        queryMap.put("updateProduct",UPDATE_PRODUCT);
        queryMap.put("deleteProduct",DELETE_PRODUCT);
        queryMap.put("selectAllOrder",SELECT_ALL_ORDER);
        queryMap.put("selectOrder",SELECT_ORDER);
        queryMap.put("insertOrder",INSERT_ORDER);
        queryMap.put("deleteOrder",DELETE_ORDER);
        queryMap.put("addProduct",ADD_PRODUCT);
        queryMap.put("deleteOrderItem",DELETE_ORDER_ITEM);
        mapper.writeValue(new File("src/main/resources/Query.json"), queryMap);
    }
}