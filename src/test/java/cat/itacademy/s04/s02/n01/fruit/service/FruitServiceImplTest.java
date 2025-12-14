package cat.itacademy.s04.s02.n01.fruit.service;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FruitServiceImplTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private FruitServiceImpl fruitServiceImpl;
    private Fruit mockFruit;

    @BeforeEach
    void setup() {
        mockFruit = new Fruit(1L, "Apple", 1);
    }

    @Test
    void add_savesFruit(){
        FruitRequestDTO request = new FruitRequestDTO("Apple",1);

        when(fruitRepository.save(any(Fruit.class))).thenReturn(mockFruit);

        FruitResponseDTO result = fruitServiceImpl.addFruit(request);

        verify(fruitRepository).save(any(Fruit.class));
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Apple");
        assertThat(result.getWeightInKilos()).isEqualTo(1);

    }
}
