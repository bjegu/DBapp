
package pl.polsl.lab1.database.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * class with a model of a ordercl table in a database
 * @author Dominika Bieg
 * @version 1.0
 */
@Entity
/**
 * named query to find all orders from a database
 */
@NamedQueries({
  @NamedQuery(name = Ordercl.FIND_ALL, query = "SELECT o FROM Ordercl o")})
@Table(name = "ordercl")
public class Ordercl implements Serializable {

    /**
     * string find_all - defining name of a query searching for all orders in a database
     */
public static final String FIND_ALL = "OrderCl.findAll";

    /**
     * id of a table order, generated automatically
     */
     @Id
     @Column(name="order_id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer order_id;
     /**
      * value of an order
      */
     @Column (name ="ord_value")
     private Double ord_value;
     /**
      * status of an order
      */
     @Column (name="status")
     private String status;
     
     /**
      * many to one realrion with a client table, one client id can be assigned to many orders
      */
    @ManyToOne // marks the other side of the 1-M relationship
    @JoinColumn(name = "client_id") // defines the foreign key column name
    private Client client;

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Double getOrd_value() {
        return ord_value;
    }

    public void setOrd_value(Double ord_value) {
        this.ord_value = ord_value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Integer clientId) {
        this.client = new Client();
        client.setClient_id(clientId);
    }

    @Override
    public String toString() {
        return "Ordercl{" + "order_id=" + order_id + ", ord_value=" + ord_value + ", status=" + status + ", client_id=" + client.getClient_id() + '}';
    }
    
     
     
}
