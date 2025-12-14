package cat.itacademy.s04.s02.n01.fruit.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremento, necesario para H2
    private Long id;

    @NotBlank(message = "Fruit name can't be empty.")
    private String name;
    @Min(value = 0, message = "Weight can't be negative.")
    private int weightInKilos;

}