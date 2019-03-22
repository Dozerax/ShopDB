import SQL.StringsQuery;
import Services.ViewServices;
import View.ViewController;

import java.io.IOException;
import java.sql.SQLException;

public class App {

    private ViewController view;

    public static void main(String[] args) throws SQLException, IOException {
        App app = new App();
        //StringsQuery.setMap();
        //StringsQuery.getList();
        //StringsQuery.print();
        app.view = ViewServices.getView();
        app.view.mainMenu();
    }
}