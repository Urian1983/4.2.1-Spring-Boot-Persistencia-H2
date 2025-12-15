package cat.itacademy.s04.s02.n01.fruit.controller;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.service.FruitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruits")
public class FruitController {

    @Autowired
    private FruitService fruitService;

    @PostMapping
    public ResponseEntity<FruitResponseDTO> addFruit(@Valid @RequestBody FruitRequestDTO requestDTO) {
        FruitResponseDTO response = fruitService.addFruit(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<FruitResponseDTO>> getAllFruits() {
        List<FruitResponseDTO> fruits = fruitService.getAllFruits();
        return ResponseEntity.ok(fruits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FruitResponseDTO> getFruitById(@PathVariable Long id) {
        FruitResponseDTO fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok(fruit);
    }

}
