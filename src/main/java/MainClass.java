import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MainClass {

    static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
                connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/java-aug-18", "sa", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Product product = new Product("iPhone", 900.0, "This is iPhone");

        String query = "INSERT INTO PRODUCTS (NAME, PRICE, DESCRIPTION) VALUES ('"
                + product.getName() + "','"
                + product.getPrice() + "','"
                + product.getDescription() + "');";

        Statement statement = connection.createStatement();
        statement.executeUpdate(query);


    }

    static class Product {

        private Long id;
        private String name;
        private Double price;
        private String description;

        public Product() {
        }

        public Product(String name, Double price, String description) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


}
