package Repository;

import SQL.ConnectionDB;
import SQL.StringsQuery;

import java.sql.*;
import java.util.Scanner;

public class CustomerRepositoryImpl implements Repository {

    private Statement statement;
    private PreparedStatement preparedStatement;

    private Scanner scanner = new Scanner(System.in);
    private String string;

    private int customerId;
    private String firstName;
    private String lastName;
    private long phoneNumber;

    @Override
    public void selectAll() throws SQLException {
        System.out.println("Loading");
        statement = ConnectionDB.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(StringsQuery.SELECT_ALL_CUSTOMER);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        System.out.println("Customers table\n");

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
        System.out.println("Enter firstName");
        firstName = scanner.next();
        System.out.println("Enter lastName");
        lastName = scanner.next();
        System.out.println("Enter phoneNumber");
        phoneNumber = scanner.nextLong();
        string = "('" + firstName + "','" + lastName + "',"+ phoneNumber +")";
        System.out.println(string);
        statement.executeUpdate(StringsQuery.INSERT_CUSTOMER + string);
        System.out.println("Customer added");
    }

    @Override
    public void update() throws SQLException {
        System.out.println("Enter customerID");
        customerId = scanner.nextInt();
        System.out.println("Enter new firstName");
        firstName = scanner.next();
        System.out.println("Enter new lastName");
        lastName = scanner.next();
        System.out.println("Enter new phoneNumber");
        phoneNumber = scanner.nextLong();
        string = "firstName = '" + firstName + "', lastName = '" + lastName + "', phoneNumber = "+phoneNumber+" WHERE ID = " + customerId;
        System.out.println(string);
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.UPDATE_CUSTOMER + string);
        preparedStatement.executeUpdate();
        System.out.println("Customer update");
    }

    @Override
    public void delete() throws SQLException {
        scanner = new Scanner(System.in);
        System.out.println("Enter customerID");
        customerId = scanner.nextInt();
        preparedStatement = ConnectionDB.getConnection().prepareStatement(StringsQuery.DELETE_CUSTOMER + customerId);
        preparedStatement.executeUpdate();
        System.out.println("Customer deleted");
    }

    @Override
    public void select() {

    }
}