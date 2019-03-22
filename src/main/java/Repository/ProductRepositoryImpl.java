package Repository;

import SQL.ConnectionDB;
import SQL.StringsQuery;

import java.sql.*;
import java.util.Scanner;

public class ProductRepositoryImpl implements Repository {

    private Statement statement;
    private PreparedStatement preparedStatement;

    private Scanner scanner = new Scanner(System.in);
    private String string;

    private int productId;
    private String productName;
    private double price;
    private String productCode;

    @Override
    public void selectAll() throws SQLException {
        System.out.println("Loading");
        statement = ConnectionDB.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(StringsQuery.SELECT_ALL_PRODUCT);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        System.out.println("Products table\n");

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
        statement = ConnectionDB.getConnection().createStatement();
        System.out.println("Enter productName");
        productName = scanner.next();
        System.out.println("Enter productPrice");
        price = scanner.nextDouble();
        System.out.println("Enter productCode");
        productCode = scanner.next();
        string = "('" + productName + "'," + price + ",'" + productCode + "')";
        System.out.println(string);
        statement.executeUpdate(StringsQuery.INSERT_PRODUCT + string);
        System.out.println("Product added");
    }

    @Override
    public void update() throws SQLException {
        System.out.println("Enter productID");
        productId = scanner.nextInt();
        System.out.println("Enter new productName");
        productName = scanner.next();
        System.out.println("Enter new productPrice");
        price = scanner.nextDouble();
        System.out.println("Enter new productCode");
        productCode = scanner.next();
        string = "productName = '" + productName + "', price = " + price + ", productCode = '" + productCode + "' WHERE ID = " + productId;
        System.out.println(string);
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.UPDATE_PRODUCT + string);
        preparedStatement.executeUpdate();
        System.out.println("Product update");
    }

    @Override
    public void delete() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter productID");
        productId = scanner.nextInt();
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.DELETE_PRODUCT + productId);
        preparedStatement.executeUpdate();
        System.out.println("Product deleted");
    }

    @Override
    public void select() {

    }
}