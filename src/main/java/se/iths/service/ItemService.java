package se.iths.service;

import se.iths.entity.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Transactional
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    Validator validator;

    Logger logger = Logger.getLogger(ItemService.class.getName());

    public void createItem(Item item) {
//            Set<ConstraintViolation<Item>> violations = validator.validate(item);
//
//            if( violations.size() > 0)
//                throw new WebApplicationException(418);

        try {
            entityManager.persist(item);
        } catch (javax.validation.ValidationException e) {
            logger.log(Level.WARNING, e.getMessage());
            logger.log(Level.WARNING, e.getClass().getName());
        }
    }

    // For demo purpose
    @PostConstruct
    public void itemClassCreate() {
        System.out.println("Item service class created!");
    }

    // For demo purpose
    @PreDestroy
    public void itemClassDestroy() {
        System.out.println("Item service class says goodbye for now!");
    }

    public void updateItem(Item item) {
        entityManager.merge(item);
    }

    public Optional<Item> findItemById(Long id) {
        return Optional.ofNullable(entityManager.find(Item.class, id));
    }

    public List<Item> getAllItems() {
        return entityManager.createQuery("SELECT i from Item i", Item.class).getResultList();
    }

    public List<Item> getItems(String category) {
        //TypedQuery<Item> query = entityManager.createQuery("SELECT i from Item i where i.category =:category", Item.class);
        TypedQuery<Item> query = entityManager.createNamedQuery("Item.findByCategory", Item.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    public void deleteItem(Long id) {
        Item foundItem = entityManager.find(Item.class, id);
        entityManager.remove(foundItem);
    }

    public Item updateName(Long id, String name) {
        Item foundItem = entityManager.find(Item.class, id);
        foundItem.setName(name);
        return entityManager.merge(foundItem);
    }

}
