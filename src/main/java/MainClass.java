import java.sql.*;

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

        String query = "SELECT * FROM PRODUCTS WHERE NAME = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, "iPhone");
        ResultSet resultSet = preparedStatement.executeQuery();
        Product product = resultSet.next() ? getProduct(resultSet) : null;

        System.out.println(product);
    }

    private static Product getProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getLong(1),
                resultSet.getString(2),
                resultSet.getDouble(3),
                resultSet.getString(4));
    }

    static class Product {
        private Long id;
        private String name;
        private Double price;
        private String description;

        public Product() {
        }

        public Product(Long id, String name, Double price, String description) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.description = description;
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

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    '}';
        }
    }


}
