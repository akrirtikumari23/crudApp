package hexa.wynkong.sql;

public class ProjectSql {
    public static final String QUERY_FOR_DELETE = "DELETE FROM users WHERE id = ?";
    public static final String SQL_GET_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_GET_USER_DETAILS = "SELECT * FROM users where id=?";
    public static final String SQL_ADD_DATA = "INSERT INTO users (id, name, pancardno, address) VALUES (nextval('sequence_users'), ?, ?, ?)";
    public static final String SQL_UPDATE_DATA = "UPDATE users SET name = ?, panCardNo = ?, address = ? WHERE id = ?";
}

