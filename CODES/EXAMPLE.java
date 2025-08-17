/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author akdag
 */
public class EXAMPLE {

    public static void main(String[] args) {
        CUSTOMER customer =new CUSTOMER();
                ACCOUNTANT accountant =new ACCOUNTANT();
USER user=new USER();
        customer.name="JOHN";
        customer.surname="MİLLER";
        customer.position="CUSTOMER";
        customer.username="JOHN44";
        customer.user_info();
                customer.user_info(customer.username);
customer.Role();
        accountant.name="JANE";
        accountant.surname="TAYLOR";
                accountant.position="ACCOUNTANT";
        accountant.username="JANE36";
        accountant.user_info();
                accountant.user_info(accountant.username);
             accountant.Role();

                user.position="NORMAL PERSON";
                user.Role();
                user.name="JACK";
                user.surname="BROWN";
                user.username="JACK20";
    }
}
