
package pl.polsl.lab1;

import pl.polsl.lab1.controller.Controller;

/**
 *
 * @author Dominika Bieg
 * @version 1.0
 * main class
 */
public class Main {
    /**
     * main function
     * @param args
     * @throws Exception 
     */
     public static void main(String[] args) throws Exception {
//        
         /**
          * creating an object of controller class
          * calling the main loop
          */
            Controller controller = new Controller();
            controller.mainLoop();
         
        // 1 stworz, 2 wyswietl, 3 wyswietl wszystkich, 4  edytuj, 5 usun, 6 edycja zamowien
        // 6'1 dadaj zamowienie, 6'2 wyswietl zamowienia, 6'3 usuń zamówienie
       
     }
}
