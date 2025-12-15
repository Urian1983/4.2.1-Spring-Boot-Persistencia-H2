package cat.itacademy.s04.s02.n01.fruit.service;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.exceptions.FruitNotFoundException;

import java.util.List;

public interface FruitService {
    FruitResponseDTO addFruit(FruitRequestDTO requestDTO);
    List<FruitResponseDTO> getAllFruits();
    FruitResponseDTO getFruitById(Long id) throws FruitNotFoundException;
    void deleteFruit(Long id);

}
