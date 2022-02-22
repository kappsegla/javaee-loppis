package se.iths.entity;

import se.iths.constraints.Category;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "Item.findByCategory", query = "SELECT i from Item i where i.category =:category"),
        @NamedQuery(name = "Item.findByName", query = "SELECT i from Item i where i.name =:name")
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Constraint, förhindrar att ett namn med mindre än 2 bokstäver anges
    @NotEmpty
    @Size(min = 2)
    private String name;
    @Category
    private String category;
    private int quantity;
    private double price;
    private LocalDate createdAt;

    // Denna metod körs innan objektet skrivs till DB
    @PrePersist
    public void getCurrentDate() {
        setCreatedAt(LocalDate.now());
    }


    // For demo purpose
    @PostPersist
    public void itemWasPersisted() {
        System.out.println("Item was stored in DB");
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
