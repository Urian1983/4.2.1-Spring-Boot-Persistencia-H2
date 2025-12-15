package cat.itacademy.s04.s02.n01.fruit.service;

import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.exceptions.FruitNotFoundException;
import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<FruitResponseDTO> getAllFruits() {
        List<Fruit> fruits = fruitRepository.findAll();
        return fruits.stream()
                .map(fruit -> new FruitResponseDTO(
                        fruit.getId(),
                        fruit.getName(),
                        fruit.getWeightInKilos()
                ))
                .collect(Collectors.toList());
    }

    public FruitResponseDTO getFruitById(Long id) throws FruitNotFoundException {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException("Fruit with id " + id + " not found"));

        return new FruitResponseDTO(
                fruit.getId(),
                fruit.getName(),
                fruit.getWeightInKilos()
        );
    }

    public void deleteFruit(Long id){
        fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException("Fruit with id " + id + " not found"));

        fruitRepository.deleteById(id);
    }

}
