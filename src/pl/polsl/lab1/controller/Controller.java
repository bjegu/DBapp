package pl.polsl.lab1.controller;

import java.util.List;
import pl.polsl.lab1.database.Connection;
import pl.polsl.lab1.database.model.Client;
import pl.polsl.lab1.database.model.Ordercl;
import pl.polsl.lab1.view.View;

/**
 *
 * @author Dominika Bieg
 * @version 1.0
 * controller class in which view and connection to database are met, controlling the 
 */
public class Controller {
    /**
     * creating an object of type connection
     */
    private final Connection newConn = new Connection();
    /**
     * crearing an object of type view
     */
    private final View newView = new View();
    
    
    /**
     * method, which asks for a parameters, buids a client and updates it in a database
     * if the client does not exist in a database it informs the user about it and try to get another id number from a user
     * @throws Exception 
     */
    public void passClient() throws Exception{
       int client_id = newView.askId();
       try{
       Client toUpdateCl = newConn.readClient(client_id);
       Client updated = newView.askUpdate(toUpdateCl);
       newConn.updateClient(updated);
       System.out.println("Update ok");}
       catch(Exception e){
           System.out.println(e.getMessage());
           passClient();
       }
    }
    /**
     *method, which asks for a parameters, buids a order and updates it in a database
     * if the client does not exist in a database it informs the user about it and try to get another id number from a user
     * @throws Exception 
     */
   public void passOrder() throws Exception{
       int order_id = newView.askId();
       try{
       Ordercl toUpdateOrd = newConn.readOrder(order_id);
       Ordercl toUpdate = newView.askUpdate(toUpdateOrd);
       newConn.updateOrder(toUpdate);
       System.out.println("Update ok");}
       catch(Exception e){
           System.out.println(e.getMessage());
           passOrder();
       }
   }
   /**
    * method, whichh asks for parameters, builds a client and delete it from a database
    * if the client does not exist in a database it informs the user about it and try to get another id number from a user
    * @throws Exception 
    */
   public void deleteClient() throws Exception{
       int client_id = newView.askId();
       try{
           Client toDelete = newConn.readClient(client_id);
            newConn.deleteClient(toDelete);
            System.out.println("Delete ok");
       }
       catch(Exception e){
           System.out.println(e.getMessage());
           deleteClient();
       }
   }
   
   /**
    * method, whichh asks for parameters, builds an order and delete it from a database
    * if the order does not exist in a database it informs the user about it and try to get another id number from a user
    * @throws Exception 
    */
   
   public void deleteOrder() throws Exception{
       int order_id = newView.askId();
       try{
       Ordercl toDelete = newConn.readOrder(order_id);
       newConn.deleteOrder(toDelete);
       System.out.println("Delete ok");}
       catch(Exception e){
           System.out.println(e.getMessage());
           deleteOrder();
       }
   }
   
   /**
    * method which gets and returns a list of clients
    * @return list of cients to be shown
    */
   public List<Client> showAll(){
      return newConn.showall();
   }
   
   /**
    * method which gets and returns a list of orders
    * @return list of orders to be shown
    */
   public List<Ordercl> showAllO(){
       return newConn.showallO();
   }
   
   /**
    * method which initialiezes a query of a client class and gets all og the parameters from the object 
    * moreover call the method to showing the result of the query
    * @param client - object of type client
    */
   public void createClientQuery(Client client){
      List <Client> queryResult = newConn.getClientByCriteries(client.getName(), client.getSurname(), client.getCity());
      newView.showAll(queryResult);
   }
   
   /**
    * method which initialiezes a query of a ordercl class and gets all og the parameters from the object 
    * moreover call the method to showing the result of the query
    * @param order - object of ordercl class 
    */
   public void createOrderQuery(Ordercl order){
       List<Ordercl> queryResult = newConn.getOrderByCriteries(order.getOrd_value(), order.getStatus(), order.getClient().getClient_id());
       newView.showAll(queryResult);
   }
   
   /**
    * main loop, which calls displaying all functionalities, allows user to choose which functionality he wants to use and allows the program to work continously
    * @throws Exception 
    */
   public void mainLoop() throws Exception{
       int option;
       do { option = newView.initialAsk();
       switch(option){
           case 1:
               Client client = new Client();
               Client newClient = newView.askUpdate(client);
               newConn.createEntity(newClient);
               break;
            case 2:
                Ordercl order = new Ordercl();
                Ordercl newOrder = newView.askUpdate(order);
                newConn.createEntity(newOrder);
                break;
            case 3:
                List <Client> clList = newConn.showall();
                newView.showAll(clList);
                break;
            case 4:
                List <Ordercl> ordList = newConn.showallO();
                newView.showAll(ordList);
                break;
            case 5:
                passClient();
                break;
            case 6:
                passOrder();
                break;
            case 7:
                deleteClient();
                break;
            case 8:
                deleteOrder();
                break;
            case 9:
               Client clientsearch = new Client();
               Client client1 = newView.askUpdate(clientsearch);
               createClientQuery(client1);
               break;
            case 10:
                Ordercl ordersearch = new Ordercl();
                Ordercl order1 = newView.askUpdate(ordersearch);
                createOrderQuery(order1);
                break;
       }
       }
       while(option != 11);
       System.out.println("See you soon!");
       
   }
}
