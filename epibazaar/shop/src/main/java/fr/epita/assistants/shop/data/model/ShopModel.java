package fr.epita.assistants.shop.data.model;
        
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shop")
public class ShopModel {
    @Id
    @GeneratedValue
    @Getter @Setter
    public int id;

    @Getter @Setter
    public float priceMultiplier;
    @Getter @Setter
    public float upgradePrice;
}