
import com.mycompany.example.COMPANY;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;

import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * jdbc:mysql://127.0.0.1:3306/?user=root
 *
 * @author akdag
 */
public class MANAGE {

    private static final String connectionString2 = "your connection";

    public static ResultSet SearchByUsernamee(String usernamee, DefaultTableModel modell) {//we are sarching all users
        ResultSet rs = null;

        Connection conn ;

        modell.setRowCount(0);
        try {

            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmtt = conn.createStatement();//we used null as and one rs to connect two infos

            String query = "SELECT username, name, surname, mail, invoince, NULL AS company_name, NULL AS type, NULL AS total_money "//NULL AS WONT SELECT AND UNION WİLL CONNECT 2 TABLES AND 
                    + "FROM user_info WHERE username LIKE '%" + usernamee + "%' "
                    + "UNION "
                    + "SELECT username, NULL AS name, NULL AS surname, NULL AS mail, NULL AS invoince, company_name, type, total_money "
                    + "FROM company_info WHERE username LIKE  '%" + usernamee + "%' ";

            rs = stmtt.executeQuery(query);
           
            
            while (rs.next()) {//THİS WİLL VİSİT ALL RESULTSET 

                String username = rs.getString("username");
                boolean alreadyExists = false;
        for (int i = 0; i < modell.getRowCount(); i++) {//this will vsit all table if there is sama username will pass  to block adding one rows because of company table
            if (modell.getValueAt(i, 0).equals(username)) {//İF THİS VALUE THAT İS USERNAME EQUAL NEW USERNAME
                alreadyExists = true;
                break;//FOR LOOP WİLL STOP
            }
           
        }
 if (alreadyExists) {//İF ALREADY EXİST İS TRUE WHİLE WİLL PASS İN RESULTSET BY CONTİNUE THİS USERNAME
            continue;
        }
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String mail = rs.getString("mail");
                String invoince = rs.getString("invoince");
               

                modell.addRow(new Object[]{username, name, surname, mail, invoince});
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
       
        }
        return rs;
    }

   

    public static boolean AddCUSTOMER(String username, String password, String name, String surname, String mail, String position, String invoince, String company_name, String type, Double total_money) {
        boolean rvalue = true;
        Connection conn ;
        Connection conn1;
//will create new customer
        try {
            conn = DriverManager.getConnection(connectionString2);
            conn1 = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
            String query = "INSERT INTO user_info (username,password,name,surname,mail,position,invoince) "
                    + "VALUES ('" + username + "','" + password + "','" + name + "','" + surname + "','" + mail + "','" + position + "','" + invoince + "')";

            stmt.executeUpdate(query);
            java.sql.Statement stmt1 = conn1.createStatement();

            String query1 = "INSERT INTO company_info (username,company_name,type,total_money) "
                    + "VALUES ('" + username + "','" + company_name + "','" + type + "','" + total_money + "')";
            stmt1.executeUpdate(query1);

            conn.close();
            conn1.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } 
        return rvalue;
    }

    public static boolean AddACCOUNTANT(String username, String password, String name, String surname, String mail, String position) {
        boolean rvalue = true;
        Connection conn ;
//will add new accountant
        try {
            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
            String query = "INSERT INTO user_info (username,password,name,surname,mail,position) "
                    + "VALUES ('" + username + "','" + password + "','" + name + "','" + surname + "','" + mail + "','" + position + "')";

            stmt.executeUpdate(query);

            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } 
        return rvalue;
    }

    public static CUSTOMER GetCustomerByUsername(String username, COMPANY şirket) {
        ResultSet rs ;
        ResultSet rs1 ;

        Connection con ;
        Connection conn1 ;

        CUSTOMER insan = new CUSTOMER();
ACCOUNTANT ac;
        try {

            con = DriverManager.getConnection(connectionString2);
            conn1 = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = con.createStatement();
            java.sql.Statement stmt1 = conn1.createStatement();

            String query = "SELECT * FROM user_info WHERE username='" + username + "'";
            String query1 = "SELECT * FROM company_info WHERE username='" + username + "'";

            rs = stmt.executeQuery(query);
            rs1 = stmt1.executeQuery(query1);

            if (rs.next() && rs1.next()) {
ac=new ACCOUNTANT();
                ac.position=rs.getString("POSİTİON");
                insan = new CUSTOMER();
                insan.username = username;
                şirket.username = username;
                insan.name = rs.getString("NAME");
                insan.surname = rs.getString("SURNAME");
                insan.mail = rs.getString("MAİL");
                insan.invoince = rs.getString("İNVOİNCE");
                insan.password = rs.getString("PASSWORD");
                insan.position = rs.getString("POSİTİON");
                şirket.company_name = rs1.getString("COMPANY_NAME");
                şirket.type = rs1.getString("TYPE");
                şirket.total_money = rs1.getDouble("TOTAL_MONEY");
                şirket.last_revenue = rs1.getDouble("LAST_REVENUE");
                şirket.paid_debt = rs1.getDouble("PAİD_DEBT");

            }
            

            con.close();
            conn1.close();

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        } 
        return insan;
    }
 public static ACCOUNTANT GetAccountant(String username) {//will bring accountant 
        ResultSet rs ;
       

        Connection con ;
       

        
ACCOUNTANT ac=new ACCOUNTANT();
        try {

            con = DriverManager.getConnection(connectionString2);
           

            java.sql.Statement stmt = con.createStatement();
            

            String query = "SELECT * FROM user_info WHERE username='" + username + "'";
           

            rs = stmt.executeQuery(query);
        

            if (rs.next()) {
ac=new ACCOUNTANT();
                ac.username=rs.getString("USERNAME");
                                ac.password=rs.getString("PASSWORD");
                ac.name=rs.getString("NAME");
                ac.surname=rs.getString("SURNAME");
                ac.mail=rs.getString("MAİL");
                ac.position=rs.getString("POSİTİON");

               
            }
            

            con.close();
          

        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        } 
        return ac;
    }

    public static boolean GetUserByUsernameControl(String username, String password, String box) {//will detect correct user in login
        ResultSet rs ;

        Connection con ;
       
        try {

            con = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = con.createStatement();
            String query = "SELECT * FROM user_info WHERE username='" + username + "'";
            rs = stmt.executeQuery(query);
            if (rs.next()) {
                String userName1 = rs.getString("USERNAME");
                String password1 = rs.getString("PASSWORD");
                String position = rs.getString("POSİTİON");

                if (username.equals(userName1) && password.equals(password1) && box.equals(position)) {
                    return true;
                } else {
                    System.out.println("wrong info");
                    return false;
                }

            }

            con.close();
        } catch (SQLException ex) {

            System.out.println("Error" + ex.getMessage());
            return false;
        }

        return false;
    }

   

    

    public static boolean UpdateCustomer(String name, String surname, String password, String mail, String position, String invoince,String username) {//updating customer
        boolean rvalue = true;
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
            String query = "UPDATE user_info SET name = '" + name + "', "
                    + "surname = '" + surname + "', "
                    + "password = '" + password + "', "
                    + "mail = '" + mail + "', "
                    + "position = '" + position + "', "
                    + "invoince = '" + invoince + "'WHERE username = '" + username + "'";

            stmt.executeUpdate(query);
            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        }
        return rvalue;
    }
    
    public static boolean UpdateAccountant(String name, String surname, String password, String mail,String username) {//will update accountant
        boolean rvalue = true;
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionString2);
            java.sql.Statement stmt = conn.createStatement();
            String query = "UPDATE user_info SET name = '" + name + "', "
                    + "surname = '" + surname + "', "
                    + "password = '" + password + "', "
                    + "mail = '" + mail + "' WHERE username = '" + username + "'";


            stmt.executeUpdate(query);
            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        }
        return rvalue;
    }

    
    
    public static boolean UpdateMoneyInCompany(String username, double Rest_money, double debt) {//will load rest money and debt TO COMPANY TABLE
        boolean rvalue = true;
        Connection conn23;
        try {
            conn23 = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn23.createStatement();
            String query = "UPDATE company_info SET total_money = " + Rest_money + ", paid_debt = " + debt + ",username='" + username + "' WHERE username = '" + username + "'";

            stmt.executeUpdate(query);
            conn23.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } catch (Exception ex1) {
            rvalue = false;
            System.out.println("error" + ex1.getMessage());
        }
        return rvalue;
    }

    
    
    

    public static boolean DeleteByUsername(String username) {

        boolean rvalue = true;

        Connection con ;
        Connection con1 ;

        try {

            con = DriverManager.getConnection(connectionString2);
            con1 = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = con.createStatement();
            java.sql.Statement stmt1 = con.createStatement();

            String query = "DELETE FROM user_info WHERE username='" + username + "'";
            String query1 = "DELETE FROM company_info WHERE username='" + username + "'";

            stmt.executeUpdate(query);
            stmt1.executeUpdate(query1);

            con.close();
            con1.close();
            rvalue = true;
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
            rvalue = false;
        }
        return rvalue;
    }

    
    
    public static boolean UpdateCompany(String company_name,String username) {//will update company
        boolean rvalue = true;
        Connection conn ;
        try {
            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
            String query = "UPDATE company_info SET company_name = '" + company_name + "' WHERE username = '" + username + "'";

            stmt.executeUpdate(query);
            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        }
        return rvalue;
    }

    
    
    public static boolean UpdateRevenueInCompany(String username, double total_money, double revenue) {//will load total money and revenue
        boolean rvalue = true;
        Connection conn23;
        try {
            conn23 = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn23.createStatement();
            String query = "UPDATE company_info SET total_money = " + total_money + ", last_revenue = " + revenue + " WHERE username = '" + username + "'";

            stmt.executeUpdate(query);
            conn23.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } catch (Exception ex1) {
            rvalue = false;
            System.out.println("error" + ex1.getMessage());
        }
        return rvalue;
    }

    
    
    public static boolean AddExpense(String username, double salaries, double rent, double marketing, double taxes, double services, double transportation, double total_debt) {
        boolean rvalue = true;
        Connection conn ;
//will add expenses
        try {
            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
           
String query = "INSERT INTO expense (username, salaries, rent, marketing, taxes, services, transportation, total_debt) "
        + "VALUES ('" + username + "', " + salaries + ", " + rent + ", " + marketing + ", " + taxes + ", " + services + ", " + transportation + ", " + total_debt + ")";

            stmt.executeUpdate(query);

            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } 
        return rvalue;
    }

    
    
    

   

    
    
    
    public static EXPENSE GetExpenseForCustomer(String username, COMPANY şirket) {//will bring all expenses and return expense
        ResultSet rs ;

        Connection con ;

        EXPENSE ex = new EXPENSE();

        try {

            con = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = con.createStatement();

            String query = "SELECT * FROM expense WHERE username='" + username + "'";

            rs = stmt.executeQuery(query);

            if (rs.next()) {

                ex.username = rs.getString("USERNAME");
                ex.salaries = rs.getDouble("SALARİES");
                ex.rent = rs.getDouble("RENT");
                ex.marketing = rs.getDouble("MARKETİNG");
                ex.taxes = rs.getDouble("TAXES");
                ex.services = rs.getDouble("SERVİCES");
                ex.transportation = rs.getDouble("TRANSPORTATİON");
                ex.total_debt = rs.getDouble("TOTAL_DEBT");

            }

            con.close();

        } catch (SQLException er) {
            System.out.println("Error" + er.getMessage());
        } 
        return ex;
    }
    
    
    
    
    public static ResultSet GetExpenseList(String username, DefaultTableModel modell) {//we used this for expenses table
        ResultSet rs = null;//to block errors

        Connection conn ;

        modell.setRowCount(0);
        try {

            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmtt = conn.createStatement();
//SELECT * FROM expenses WHERE username LIKE '%zengin%';

            String query = "SELECT * FROM expense WHERE username LIKE '%" + username + "%' ";
                    
            rs = stmtt.executeQuery(query);
            while (rs.next()) {

                String username1 = rs.getString("username");
                String salaries = rs.getString("salaries");
                String rent = rs.getString("rent");
                String marketing = rs.getString("marketing");
                String taxes = rs.getString("taxes");
                String services = rs.getString("services");
                String transportation = rs.getString("transportation");
                String total_debt = rs.getString("total_debt");

                modell.addRow(new Object[]{username1, salaries, rent, marketing,taxes,services,transportation,total_debt});
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return rs;
    }
    
    
    
    
    public static ResultSet GetRevenueList(String username, DefaultListModel model) {//we used this for revenue list
        ResultSet rs = null;//to block errors

        Connection conn ;

        model.clear();
        try {

            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmtt = conn.createStatement();


            String query = "SELECT * FROM revenue WHERE username LIKE '%" + username + "%' ";
                    
            rs = stmtt.executeQuery(query);
            while (rs.next()) {

                //String username1 = rs.getString("username");
                String new_revenue = rs.getString("new_revenue");
                

                model.addElement(new_revenue);
            }

            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error" + ex.getMessage());
        }
        return rs;
    }
    
    
    
    
    public static boolean AddRevenue(String username, String new_revenue) {//will add new revenue 
        boolean rvalue = true;
        Connection conn ;

        try {
            conn = DriverManager.getConnection(connectionString2);

            java.sql.Statement stmt = conn.createStatement();
            String query = "INSERT INTO revenue (username,new_revenue) "
                    + "VALUES ('" + username + "','" + new_revenue + "')";

            stmt.executeUpdate(query);

            conn.close();

        } catch (SQLException ex) {
            rvalue = false;
            System.out.println("error" + ex.getMessage());
        } 
        return rvalue;
    }

    
    
    
    
}
