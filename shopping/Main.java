import shopping.Person;
import shopping.Product;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Set up
        Scanner scanner = new Scanner(System.in);
        Map<String, Person> clients = new LinkedHashMap<>();

        // Getting people data
        readPeople(scanner, clients);

        Map<String, Product> products = new HashMap<>();

        // Getting products data
        readProducts(scanner, products);


        String command = scanner.nextLine();
        while (!command.equals("END")) {
            // Getting individual person and product data
            String[] buyerAndProduct = command.split("\\s+");
            String buyerName = buyerAndProduct[0];
            String productName = buyerAndProduct[1];

            try {
                buyProducts(clients, products, buyerName, productName);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }

        // Printing all information after end 
        printAllInformation(clients);
    }

    private static void readProducts(Scanner scanner, Map<String, Product> products) {
        String[] input = scanner.nextLine().split(";");

        for (String s : input) {
            String[] tokens = s.split("=");
            try {
                Product product = new Product(tokens[0], Double.parseDouble(tokens[1]));
                products.put(tokens[0], product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void readPeople(Scanner scanner, Map<String, Person> clients) {
        String[] input = scanner.nextLine().split(";");

        for (String s : input) {
            String[] tokens = s.split("=");
            try {
                Person person = new Person(tokens[0], Double.parseDouble(tokens[1]));
                clients.put(tokens[0], person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printAllInformation(Map<String, Person> clients) {
        for (Map.Entry<String, Person> entry : clients.entrySet()) {
            int index = 0;
            System.out.printf("%s - ", entry.getValue().getName());
            for (Product product : entry.getValue().getProducts()) {
                System.out.printf("%s", product.getName());
                index++;
                if (index < entry.getValue().getProducts().size()) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    private static void buyProducts(Map<String, Person> clients, Map<String, Product> products, String buyerName, String productName) {
        clients.get(buyerName).buyProduct(products.get(productName));
        System.out.printf("%s bought %s%n", buyerName, productName);
    }
}