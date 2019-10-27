package pl.polsl.lab1.database;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pl.polsl.lab1.database.model.Client;
import pl.polsl.lab1.database.model.Ordercl;

/**
 * class responsible for connection with a database and all entity menager-related methods
 * @author Dominika Bieg
 * @version 1.0
 */
public class Connection {

    /**
     * establishing the connection with the database
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabPU");
    EntityManager em = emf.createEntityManager();

    /**
     * Method providing a connection by an entity menager and adding a new entity to a client table
     * @param client - client type object which should be added to database
     */
    public void createEntity(Client client) {
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.clear();
        System.out.println(em.find(Client.class, client.getClient_id()));
    }

    /**
     * Method providing a connection by an entity menager and adding a new entity to an order table
     * @param order - ordercl type object which should be added to database
     */
    public void createEntity(Ordercl order) {
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Method which finds and reads entity from a client table. In case of not having a given entity in database, throws an exception;
     * @param client_id - id of client to be returned
     * @return client object, which was found in a database
     * @throws Exception in case of not having the client in a database
     */
    public Client readClient(int client_id) throws Exception {
        Client client = em.find(Client.class, client_id);
        if (client != null) {
            System.out.println(client);
            return client;
        } else {
            throw new Exception("This client does not exist in the database.");
        }
    }

    /**
     * Method which finds and reads entity from an order table. In case of not having a given entity in database, throws an exception;
     * @param order_id - id of client to be returned
     * @return ordercl object, which was found in a database
     * @throws Exception in case of not having the ordrer in a database
     */
    public Ordercl readOrder(int order_id) throws Exception {
        Ordercl order = em.find(Ordercl.class, order_id);
        if (order != null) {
            System.out.println(order);
            return order;
        } else {
            throw new Exception("This order does not exist in the database.");
        }
    }

    /**
     * Method providing a connection to database and updating an entity in a client table
     * @param updated - client object to be updated
     */
    public void updateClient(Client updated) {
        em.getTransaction().begin();
        em.merge(updated); // update operation
        em.getTransaction().commit();
    }

    /**
     * Method providing a connection to database and updating an entity in an order table
     * @param toUpdate - order object to be updated
     */
    public void updateOrder(Ordercl toUpdate) {
        em.getTransaction().begin();
        em.merge(toUpdate); // update operation
        em.getTransaction().commit();
    }

    /**
     * Method providing a connection to database and delating an entity in a client table
     * @param toDelete - client object to be deleted
     */
    public void deleteClient(Client toDelete) {
        em.getTransaction().begin();
        em.remove(toDelete); // delete operation
        em.getTransaction().commit();
    }

    /**
     * Method providing a connection to database and delating an entity in an order table
     * @param toDelete - ordercl object to be deleted
     */
    public void deleteOrder(Ordercl toDelete) {
        em.getTransaction().begin();
        em.remove(toDelete); // delete operation
        em.getTransaction().commit();
    }

    /**
     * method creating a query finding all entities in client table
     * @return clList - list of all entities in a client table
     */
    public List<Client> showall() {
        List<Client> clList = em.createNamedQuery(Client.FIND_ALL).getResultList();
        return clList;
    }

    /**
     * method creating a query finding all entities in order table
     * @return ordList - list of all entities in an order table
     */
    public List<Ordercl> showallO() {
        List<Ordercl> ordList = em.createNamedQuery(Ordercl.FIND_ALL).getResultList();
        return ordList;
    }

    /**
     * Method creating a various queries to a client table 
     * @param em - entity manager
     * @param name - name of the client to be searched - CAN BE NULL
     * @param surname - surname of the client to be searched - CAN BE NULL
     * @param city - city of the client to be searched - CAN BE NULL
     * @return queryDefinition - definition of a query to the databasse
     */
    public static CriteriaQuery<Client> prepareClientCriteriaQuery(EntityManager em,
            String name, String surname, String city) {
        Expression expr; // refers to the attributes of entity class
        Root<Client> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Client> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause
        CriteriaBuilder builder = em.getCriteriaBuilder(); // creates predicates

        // the argument of createQuery defines the return type of the query - here, whole CLient object
        queryDefinition = builder.createQuery(Client.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Client.class);
        // defines the select part of the query
        // at this point we have a query select s from Client s (select * from client in SQL)
        queryDefinition.select(queryRoot);
        if (name != null&& !name.isEmpty()) {
            // gets access to the field called name in the client class
            expr = queryRoot.get("name");
            // creates condition of the form s.name LIKE name
            predicates.add(builder.like(expr, name));
        }
        if (surname != null && !surname.isEmpty() ) {
            // gets access to the field called name in the client class
            expr = queryRoot.get("surname");
            // creates condition of the form s.average >= average
            predicates.add(builder.like(expr, surname));
        }
        if (city != null && !city.isEmpty()) {
            // gets access to the field called name in the client class
            expr = queryRoot.get("city");
            // creates condition of the form s.average >= average
            predicates.add(builder.like(expr, city));
        }
        // if there are any conditions defined
        if (!predicates.isEmpty()) {
            // build the where part in which we combine the conditions using AND operator
            queryDefinition.where(
                    builder.and(predicates.toArray(
                                    new Predicate[predicates.size()])));
        }
        return queryDefinition;
    }
    
    /**
     * Method that gains all entities meeting the requirements on a lst of client type objects
     * @param name - name of the searching client
     * @param surname - surname of searching client
     * @param city - city of searching client
     * @return query created basing on required parameters
     */
    public List<Client> getClientByCriteries(String name, String surname, String city) {
        return em.createQuery(prepareClientCriteriaQuery(em, name, surname, city))
                .getResultList();
    }
    
    /**
     * Method that gains all entities meeting the requirements on a lst of an order type objects     
     * @param em - entity manager
     * @param ord_value - value of searching order
     * @param status - status of a seraching order
     * @param client_id - id of a client assigned to a serching order
     * @return queryDefinition - definition of a query to the databasse
     */
    public static CriteriaQuery<Ordercl> prepareOrderCriteriaQuery(EntityManager em,
            Double ord_value, String status, Integer client_id) {
        Expression expr; // refers to the attributes of entity class
        Root<Ordercl> queryRoot; // entity/table from which the selection is performed
        CriteriaQuery<Ordercl> queryDefinition; // query being built
        List<Predicate> predicates = new ArrayList<>(); // list of conditions in the where clause
        CriteriaBuilder builder = em.getCriteriaBuilder(); // creates predicates

        // the argument of createQuery defines the return type of the query 
        queryDefinition = builder.createQuery(Ordercl.class);
        // defines the from part of the query
        queryRoot = queryDefinition.from(Ordercl.class);
        // defines the select part of the query
        // at this point we have a query select s from Client s (select * from client in SQL)
        queryDefinition.select(queryRoot);
        if (ord_value != null) {
            // gets access to the field called name in the client class
            expr = queryRoot.get("ord_value");
            // creates condition of the form s.average >= average
            predicates.add(builder.greaterThanOrEqualTo(
                    expr, ord_value));
        }
        if (status != null && !status.isEmpty() ) {
            // gets access to the field called name in the Student class
            expr = queryRoot.get("status");
            // creates condition of the form s.average >= average
            predicates.add(builder.like(expr, status));
        }
        if (client_id != null) {
            // gets access to the field called name in the client class
            expr = queryRoot.get("client_id");
            // creates condition of the form s.average >= average
            predicates.add(builder.equal(
                    expr, client_id));
        }
        // if there are any conditions defined
        if (!predicates.isEmpty()) {
            // build the where part in which we combine the conditions using AND operator
            queryDefinition.where(
                    builder.and(predicates.toArray(
                                    new Predicate[predicates.size()])));
        }
        return queryDefinition;
}
    /**
     * Method that gains all entities meeting the requirements on a lst of client type objects
     * @param ord_value - value of the searched order
     * @param status - statue of a searched order
     * @param client_id = id of a client assigned to a serached order
     * @return query created basing on required parameters
     */
     public List<Ordercl> getOrderByCriteries(Double ord_value, String status, Integer client_id) {
        return em.createQuery(prepareOrderCriteriaQuery(em, ord_value, status, client_id))
                .getResultList();
    }
    
}
