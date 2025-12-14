package cat.itacademy.s04.s02.n01.fruit.service;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitServiceImpl implements FruitService{

    @Autowired
    private FruitRepository fruitRepository;

    public FruitResponseDTO addFruit(FruitRequestDTO requestDTO){
        Fruit fruit = new Fruit();
        fruit.setName(requestDTO.getName());
        fruit.setWeightInKilos(requestDTO.getWeightInKilos());

        Fruit savedFruit = fruitRepository.save(fruit);

        FruitResponseDTO response = new FruitResponseDTO();
        response.setId(savedFruit.getId());
        response.setName(savedFruit.getName());
        response.setWeightInKilos(savedFruit.getWeightInKilos());
        return response;
    }
}
