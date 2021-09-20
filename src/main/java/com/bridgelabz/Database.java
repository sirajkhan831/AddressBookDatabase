package com.bridgelabz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.sqlite.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.*;
import java.util.LinkedList;

public class Database {
    static int errorCode = 0;
    static String tableName = "contactList";
    static String ID;
    static String ID2;
    static String pass;
    static String pass2;
    static String link;
    static String link2;

    static {
        try {
            File file = new File("credentials.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("cred");
            Node node = nodeList.item(0);
            Element eElement = (Element) node;
            ID = eElement.getElementsByTagName("id").item(0).getTextContent();
            ID2 = eElement.getElementsByTagName("id2").item(0).getTextContent();
            link = eElement.getElementsByTagName("link").item(0).getTextContent();
            link2 = eElement.getElementsByTagName("link2").item(0).getTextContent();
            pass = eElement.getElementsByTagName("pass").item(0).getTextContent();
            pass2 = eElement.getElementsByTagName("pass2").item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // addToDatabase method to add a new contact to database
    public static void addContact(Contact contact) {
        try {
            Connection conn = DriverManager.getConnection(link, ID, pass);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + tableName + " (FirstName, LastName, Number, Email, Address, City, State, ZipCode) " +
                    "VALUES ('" + contact.getFirstName() + "', '" + contact.getLastName() + "', '" + contact.getNumber() + "', '" + contact.getEmail() + "', '" + contact.getAddress() + "', '" + contact.getCity() + "', '" + contact.getState() + "', '" + contact.getZipCode() + "');");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database while adding contact.");
        }
    }

    // removing contact from database
    public static void removeContact(String contactName) {
        try {
            Connection conn = DriverManager.getConnection(link, ID, pass);
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM " + tableName + " WHERE FirstName = '" + contactName + "'");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database while removing contact.");
        }
    }

    // importing contacts from database
    public static void importContact(LinkedList<Contact> contact) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(link, ID, pass);
            Statement statement = conn.createStatement();
            String Query = "SELECT * FROM " + tableName;
            ResultSet rs = statement.executeQuery(Query);
            while (rs.next()) {
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String number = rs.getString("Number");
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String city = rs.getString("City");
                String state = rs.getString("State");
                int zipcode = rs.getInt("ZipCode");
                contact.add(new Contact(firstName, lastName, number, address, city, state, email, zipcode));
            }
        } catch (SQLException e) {
            errorCode++;
            if (errorCode == 1) {
                System.out.println("Error while connecting to the Google Cloud");
                try {
                    Thread.sleep(1000);
                    System.out.print("Trying to connect to CloudCluster");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.println(".");
                } catch (InterruptedException f) {
                    f.printStackTrace();
                }
                link = link2;
                ID = ID2;
                pass = pass2;
                importContact(contact);
            } else {
                System.out.println("Connection failed");
                System.out.print("Switching to local database..");
                try {
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".");
                    Thread.sleep(500);
                    System.out.print(".\n");
                    sqlite(contact);
                } catch (SQLException g) {
                    g.printStackTrace();
                } catch (InterruptedException h) {
                    h.printStackTrace();
                }
            }
        }
    }

    public static void sqlite(LinkedList<Contact> contact) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:local.db");
        Statement statement = connection.createStatement();
        String Query = "SELECT * FROM " + tableName;
        ResultSet rs = statement.executeQuery(Query);
        while (rs.next()) {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
            String number = rs.getString("Number");
            String email = rs.getString("Email");
            String address = rs.getString("Address");
            String city = rs.getString("City");
            String state = rs.getString("State");
            int zipcode = rs.getInt("ZipCode");
            contact.add(new Contact(firstName, lastName, number, address, city, state, email, zipcode));
        }
    }
}
