package Repository;

import java.sql.SQLException;

public interface Repository {

    void selectAll() throws SQLException;

    void select() throws SQLException;

    void insert() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;
}