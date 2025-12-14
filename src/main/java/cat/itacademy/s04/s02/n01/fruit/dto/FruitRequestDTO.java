package cat.itacademy.s04.s02.n01.fruit.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FruitRequestDTO {

    //No incluyo el ID, ya que supuestamente se genera en la base de datos ;
    @NotBlank(message = "Fruit name can't be empty")
    private String name;

    @Min(value =1, message ="Weight can't be negative.")
    private int weightInKilos;
}