package View;

import Services.CustomerServices;
import Services.OrderServices;
import Services.ProductServices;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ViewController {

    private Scanner scanner = new Scanner(System.in);

    public void mainMenu() throws SQLException {
        for (; ; ) {
            printMenu();
            String x = scanner.next();
            switch (x) {
                case "1": {
                    CustomerServices.getInstance().selectAll();
                }
                break;
                case "2": {
                    CustomerServices.getInstance().insert();
                }
                break;
                case "3": {
                    CustomerServices.getInstance().update();
                }
                break;
                case "4": {
                    CustomerServices.getInstance().delete();
                }
                break;
                case "5": {
                    ProductServices.getInstance().selectAll();
                }
                break;
                case "6": {
                    ProductServices.getInstance().insert();
                }
                break;
                case "7": {
                    ProductServices.getInstance().update();
                }
                break;
                case "8": {
                    ProductServices.getInstance().delete();
                }
                break;
                case "9": {
                    OrderServices.getInstance().selectAll();
                }
                break;
                case "10": {
                    OrderServices.getInstance().insert();
                }
                break;
                case "11": {
                    OrderServices.getInstance().select();
                }
                break;
                case "12": {
                    OrderServices.getInstance().delete();
                }
                break;
                case "13": {
                    System.exit(0);
                }
                break;
                default: {
                    System.out.println("Error enter 1,2,3,4,5,6,7,8,9,10,11,12 or 13");
                }
                break;
            }
        }
    }

    private void printMenu() {
        String[][] arrray = {{"1 - All customers\t", "2 - Insert customer\t", "3 - Update customer\t", "4 - Delete customer"},
                {"5 - All products\t", "6 - Insert product\t", "7 - Update product\t", "8 - Delete product"},
                {"9 - All orders\t\t", "10 - Create order\t\t", "11 - Select order\t\t", "12 - Delete order"}};
        for (String[] anArrray : arrray) {
            System.out.println(Arrays.toString(anArrray));
        }
        System.out.println("[13 - Exit]");
    }
}