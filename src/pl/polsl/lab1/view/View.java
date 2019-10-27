
package pl.polsl.lab1.view;

import java.util.List;
import java.util.Scanner;
import pl.polsl.lab1.database.model.Client;
import pl.polsl.lab1.database.model.Ordercl;

/**
 * View class responsible for displaying and downloafing data from a console
 * @author Dominika Bieg
 * @version 1.0
 */
public class View {
    
    /**
     * possibility to download data from a console
     */
    private Scanner in = new Scanner(System.in);
    private Scanner inN = new Scanner(System.in);
    /**
     * creating a client type object
     */
    private Client client = new Client();
    /**
     * creating an ordercl object
     */
    private Ordercl newOrder = new Ordercl();
    
    /**
     * method asking for an id of an element
     * @return id of an element
     */
    public int askId(){
        int id = askInt("Id of an element:");
      return id;
    }
    
    /**
     * Method that sets new parameters to the client object
     * @param toUpdate - client to be updated
     * @return client object with new parameters
     */
    public Client askUpdate(Client toUpdate){
        System.out.println("Set a name of a client: ");
        String name = in.nextLine();
        System.out.println("Set a surname of a client: ");
        String surname = in.nextLine();
        System.out.println("Set a city of a client: ");
        String city = in.nextLine();
       
       toUpdate.setName(name);
       toUpdate.setSurname(surname);
       toUpdate.setCity(city);
       
       return toUpdate;
    }
    
    /**
     * Method that sets new parameters to the ordercl object
     * @param toUpdate - order to be updated
     * @return ordercl object with new parameters
     */
    public Ordercl askUpdate (Ordercl toUpdate){
        Double value = askDouble("Set a new order value: ");
        System.out.println("Set a new order status: ");
        String status = in.nextLine();
        Integer client_id = askInt("Set a new order receiver (client_id) ");
        
        toUpdate.setOrd_value(value);
        toUpdate.setStatus(status);
        toUpdate.setClient(client_id);
        
        return toUpdate;
    }
    
    /**
     * Method that asks for a double value and in case of missing the format asks again
     * @param label - message shown to the user
     * @return value of a double downloading from a console
     */
    private Double askDouble(String label){
        try{
            System.out.println(label);
            String input = in.nextLine();
            if (input.isEmpty()){
                return null;
            }
            else{
            return Double.parseDouble(in.nextLine());}
        }
        catch(NumberFormatException e){
            return askDouble(label);
        }
    }
    
    /**
     * Method that asks for a int value and in case of missing the format asks again
     * @param label - message shown to the user
     * @return value of a int downloading from a console
     */
  private Integer askInt(String label){
      try{
            System.out.println(label);
            String input = in.nextLine();
            if (input.isEmpty()){
                return null;
            }
            else {
                return Integer.parseInt(input);
            }
        }
        catch(NumberFormatException e){
            return askInt(label);
        }
   }
  
  /**
   * Method displaying list of possible functionalities of a program
   * @return a value of functionality chosen by the user
   */
    public int initialAsk(){
       System.out.println("Hi, what would you like to do? ");
       System.out.println("Please type in the number: ");
       System.out.println("1. Add a client to the database ");
       System.out.println("2. Add an order to the database ");
       System.out.println("3. Display all clients from the database ");
       System.out.println("4. Display all orders from the database ");
       System.out.println("5. Update the client data ");
       System.out.println("6. Update the order data ");
       System.out.println("7. Delete the client from a database ");
       System.out.println("8. Delete the order from a database ");
       System.out.println("9. Find the client");
       System.out.println("10. Find the order");
       System.out.println("11. Finish work/ Close app");
       return inN.nextInt();
}
    
    /**
     * Method printing any list on a console
     * @param <T> generic parameter giving a possibility of using the method in different cases
     * @param theList list to be printed
     */
    public <T extends Object> void showAll(List<T> theList){
        theList.forEach(System.out::println);
    }
   
}
