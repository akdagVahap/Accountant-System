/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author akdag
 */
public class USER {
     public int id;
    public String name;
    public String surname;
    public String mail;
    public String position;
    public String username;
    public String password;
    
    //these are overload methods
    public void user_info(){
        System.out.println("The name is " + "  " + name +" The surname is " + "  "  + surname);
    }
    public void user_info(String username){
                System.out.println("The name is "  + "  " + name + " The surname is " + "  " + surname + " The username is " +"  " + username);

    }
    public void Role(){//this is override
        System.out.println("The role is " + "  " + position);
    }
}
