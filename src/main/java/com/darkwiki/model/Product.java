package com.darkwiki.model;

import jakarta.persistence.*;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.*;

@Entity
public class Product implements Persistable<Long>, Serializable, Comparable<Product> {

    @Id
    @GeneratedValue
    Long id;


    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @Column
    private String description;

    @Column
    private double pricePerUnit;

    @ElementCollection
    @CollectionTable(name = "product_amounts", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "available_amount")
    private SortedSet<Integer> availableAmountSet = new TreeSet<>();

    @Column
    private double shippingCost;

    @OneToMany(mappedBy = "product")
    private Set<Order> orderSet = new HashSet<Order>();

    public void addToAvailableAmountSet(Integer amount) {
        if (amount != null) {
            availableAmountSet.add(amount);
        }
    }

    public void removeFromAvailableAmountSet(Integer amount) {
        if (amount != null) {
            availableAmountSet.remove(amount);
        }
    }

    // TODO: This seems to be dangerous, but possibly needed for the ProductAddView to function
    public void setAvailableAmountSet(SortedSet<Integer> availableAmountSet) {
        this.availableAmountSet = availableAmountSet;
    }

    public void addToOrderSet(Order order) {
       if (order != null) {
           orderSet.add(order);
           order.setProduct(this);
       }
    }

    public void removeFromOrderSet(Order order) {
       if (order != null) {
           orderSet.remove(order);
           order.setProduct(null);
       }
    }

    public void setVendor(Vendor vendor) {
        if (vendor != null && this.vendor != null) {
            if (this.vendor.equals(vendor)) {
                return;
            }
        }

        if (vendor != null) {
            vendor.getProductSet().add(this);
        }

        if (this.vendor != null) {
            this.vendor.getProductSet().remove(this);
        }

        this.vendor = vendor;
    }

    public void setProductType(ProductType productType) {
        if (productType != null) {
            productType.getProductSet().add(this);
        }

        if (this.productType != null) {
            this.productType.getProductSet().remove(this);
        }

        this.productType = productType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(id);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int compareTo(Product o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "(%s) %s".formatted(id, name);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Set<Integer> getAvailableAmountSet() {
        return availableAmountSet;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public Vendor getVendor() {
        return vendor;
    }
}
