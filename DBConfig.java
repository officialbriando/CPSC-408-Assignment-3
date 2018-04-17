import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig
{
    private Connection pgSqlConnection = null;

    public static Connection getMySqlConnection()
    {
        Connection mysqlConnection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://localhost:3306/408assignment3?useSSL=false";
            mysqlConnection = DriverManager.getConnection(connectionUrl, "username", "password");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mysqlConnection;
    }
}