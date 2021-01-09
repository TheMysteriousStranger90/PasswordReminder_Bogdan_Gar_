package dao;

import bean.Record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecordDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/users_password_reminder?useUnicode=true&serverTimezone=UTC";  // Локальная база данных(нужно явно указать serverTimezone в строке подключения)
    private static final String LOGIN = "usertmp";  // Локальный логин от базы данных
    private static final String PASS = "usertmp";  // Локальный пароль от базы данных
    private static final String ADD = "INSERT INTO users (address, login, password, time) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
    public static final String SELECT = "SELECT * FROM users";
    public static ArrayList<Record> arrPR = new ArrayList();

    // Загрузка драйвера
    static {
        try {
            Class.forName(DRIVER);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Создание соединения
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASS);
    }

    // Выполнение запроса
    public static PreparedStatement prepareStatement(Connection c, String sql) throws SQLException {
        return c.prepareStatement(sql);
    }

    // Метод добавления в базу данных
    public static void add(Record r) {
        try (Connection c = getConnection()) {
            PreparedStatement ps = prepareStatement(c, ADD);
            ps.setString(1, r.getAddress());
            ps.setString(2, r.getLogin());
            ps.setString(3, r.getPassword());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    // Вывод результатов на экран
    public static void printResultRecord() {
        try (Connection c = getConnection()) {
            PreparedStatement ps = c.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("ID\tАдрес\t\tЛогин\t\tПароль\t\tВремя\n");
            System.out.println("-----------------------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String a = rs.getString("address");
                String l = rs.getString("login");
                String p = rs.getString("password");
                String t = rs.getString("time");
                System.out.printf(id + "\t" + a + "\t" + l + "\t" + p + "\t" + t + "\t" + "\n");
                System.out.println();
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
}
