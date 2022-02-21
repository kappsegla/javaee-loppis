package se.iths.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import se.iths.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Test
    @DisplayName("GetAll should return all items")
    void getAll() {
        Item item1 = new Item();
        List<Item> students = List.of(item1);
        TypedQuery<Item> tq = mock(TypedQuery.class);
        when(tq.getResultList()).thenReturn(students);
        EntityManager entityManager = mock(EntityManager.class);
        when(entityManager.createQuery("SELECT i from Item i", Item.class)).thenReturn(tq);
        ItemService itemService = new ItemService();
        itemService.entityManager = entityManager;

        var result = itemService.getAllItems();

        assertEquals(1 , result.size());
    }

}