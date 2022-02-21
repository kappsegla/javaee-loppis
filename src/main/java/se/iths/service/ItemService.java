package se.iths.service;

import se.iths.entity.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class ItemService {

    @PersistenceContext
    EntityManager entityManager;

    public void createItem(Item item) {
            entityManager.persist(item);
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
