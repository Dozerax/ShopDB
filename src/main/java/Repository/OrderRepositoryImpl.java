package Repository;

import SQL.ConnectionDB;
import SQL.StringsQuery;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class OrderRepositoryImpl implements Repository {

    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private Scanner scanner = new Scanner(System.in);
    private String string;
    private int numberOfColumns;

    private int orderId;

    @Override
    public void selectAll() throws SQLException {
        System.out.println("Loading");
        statement = ConnectionDB.getConnection().createStatement();
        resultSet = statement.executeQuery(StringsQuery.SELECT_ALL_ORDER);
        metaData = resultSet.getMetaData();
        numberOfColumns = metaData.getColumnCount();

        System.out.println("Order table\n");

        for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void select() throws SQLException {
        System.out.println("Loading");
        Statement statement = ConnectionDB.getConnection().createStatement();
        System.out.println("Enter orderID");
        orderId = scanner.nextInt();
        resultSet = statement.executeQuery(StringsQuery.SELECT_ORDER + orderId);
        metaData = resultSet.getMetaData();
        numberOfColumns = metaData.getColumnCount();

        System.out.println("Order table\n");

        for (int i = 1; i <= numberOfColumns; i++)
            System.out.printf("%-8s\t", metaData.getColumnName(i));
        System.out.println();

        while (resultSet.next()) {
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", resultSet.getObject(i));
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void insert() throws SQLException {
        System.out.println("Enter customerID");
        int customerId = scanner.nextInt();
        int orderNumber = new Random().nextInt(99999);
        string = "(" + customerId + "," + orderNumber + ")";
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.INSERT_ORDER + string, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        addProduct();
        System.out.println("Order created");
    }

    private void addProduct() throws SQLException {
        String count;
        do {
            System.out.println("1 - Add product");
            System.out.println("2 - Complete order");
            count = scanner.next();
            switch (count) {
                case "1": {
                    statement = ConnectionDB.getConnection().createStatement();
                    System.out.println("Enter productID");
                    int productId = scanner.nextInt();
                    System.out.println("Enter amount");
                    int amount = scanner.nextInt();
                    string = "(" + productId + "," + this.resultSet.getObject(1) + "," + amount + ")";
                    statement.executeUpdate(StringsQuery.ADD_PRODUCT + string);
                }
                break;
                case "2": {
                    count = "stop";
                }
                break;
            }
        } while (!count.equals("stop"));
    }

    @Override
    public void delete() throws SQLException {
        System.out.println("Enter orderID");
        orderId = scanner.nextInt();
        deleteOrderItem(orderId);
        System.out.println(StringsQuery.DELETE_ORDER + orderId);
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.DELETE_ORDER + orderId);
        preparedStatement.executeUpdate();
        System.out.println("Order deleted");
    }

    private void deleteOrderItem(int orderId) throws SQLException {
        System.out.println(StringsQuery.DELETE_ORDER_ITEM + orderId);
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.DELETE_ORDER_ITEM + orderId);
        preparedStatement.executeUpdate();
    }

    @Override
    public void update() {

    }
}