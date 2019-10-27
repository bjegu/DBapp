package pl.polsl.lab1.database.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * class with a model of a client table in a database
 * @author Dominika Bieg
 * @version 1.0
 */


@Entity
@Table(name = "client")
/**
 * named query to find all clients from a database
 */
  @NamedQueries({
  @NamedQuery(name = Client.FIND_ALL, query = "SELECT c FROM Client c")})
public class Client implements Serializable {
    
    /**
     * string find_all - defining name of a query searching for all clients in a database
     */
    public static final String FIND_ALL = "Client.findAll";

    /**
     * id of a table client, generated automatically
     */
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer client_id;
    /**
     * name of a client
     */
    @Column(name="name")
    private String name;
    /**
     * surname of a client
     */
    @Column (name="surname")
    private String surname;
    /**
     * city of a client
     */
    @Column (name="city")
    private String city;
    
    /**
     * mapping one to many to an order table by client id 
     */
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ordercl> orders = new ArrayList<>();
  

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Ordercl> getOrders() {
        return orders;
    }

    public void setOrders(List<Ordercl> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Client{" + "client_id=" + client_id + ", name=" + name + ", surname=" + surname + ", city=" + city + ", orders=" + orders + '}';
        
    }
    
    
    
    
}
