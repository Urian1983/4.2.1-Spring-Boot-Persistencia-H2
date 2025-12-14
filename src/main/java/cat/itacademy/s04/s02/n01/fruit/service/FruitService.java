package cat.itacademy.s04.s02.n01.fruit.service;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;

public interface FruitService {
    FruitResponseDTO addFruit(FruitRequestDTO requestDTO);
}
