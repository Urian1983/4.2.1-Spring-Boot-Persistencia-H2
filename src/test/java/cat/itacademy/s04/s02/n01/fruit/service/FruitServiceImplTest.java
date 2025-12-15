package cat.itacademy.s04.s02.n01.fruit.service;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitRequestDTO;
import cat.itacademy.s04.s02.n01.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import cat.itacademy.s04.s02.n01.fruit.repository.FruitRepository;

import cat.itacademy.s04.s02.n01.fruit.exceptions.FruitNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void add_savesFruit() {
        FruitRequestDTO request = new FruitRequestDTO("Apple", 1);

        when(fruitRepository.save(any(Fruit.class))).thenReturn(mockFruit);

        FruitResponseDTO result = fruitServiceImpl.addFruit(request);

        verify(fruitRepository).save(any(Fruit.class));
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Apple");
        assertThat(result.getWeightInKilos()).isEqualTo(1);

    }

    @Test
    void getAllFruits_returnsAllFruit() {
        Fruit fruit1 = new Fruit(1L, "Apple", 1);
        Fruit fruit2 = new Fruit(2L, "Banana", 2);
        List<Fruit> mockFruits = Arrays.asList(fruit1, fruit2);

        when(fruitRepository.findAll()).thenReturn(mockFruits);

        List<FruitResponseDTO> result = fruitServiceImpl.getAllFruits();

        verify(fruitRepository).findAll();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Apple");
        assertThat(result.get(1).getName()).isEqualTo("Banana");
    }

    @Test
    void getAllFruits_returnsEmptyList() {
        when(fruitRepository.findAll()).thenReturn(Arrays.asList());

        List<FruitResponseDTO> result = fruitServiceImpl.getAllFruits();

        verify(fruitRepository).findAll();
        assertThat(result).isEmpty();
    }


    @Test
    void getFruitById_returnsFruit() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(mockFruit));

        FruitResponseDTO result = fruitServiceImpl.getFruitById(1L);

        verify(fruitRepository).findById(1L);
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Apple");
        assertThat(result.getWeightInKilos()).isEqualTo(1);

    }

    @Test
    void getFruitById_throwsExceptionWhenNotFound() {
        when(fruitRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fruitServiceImpl.getFruitById(999L))
                .isInstanceOf(FruitNotFoundException.class)
                .hasMessage("Fruit with id 999 not found");

        verify(fruitRepository).findById(999L);
    }

    @Test
    void deleteFruit_deletesSuccessfully() {
        when(fruitRepository.findById(1L)).thenReturn(Optional.of(mockFruit));

        fruitServiceImpl.deleteFruit(1L);

        verify(fruitRepository).findById(1L);
        verify(fruitRepository).deleteById(1L);
    }

    @Test
    void deleteFruit_throwsExceptionWhenNotFound() {
        when(fruitRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> fruitServiceImpl.deleteFruit(999L))
                .isInstanceOf(FruitNotFoundException.class)
                .hasMessage("Fruit with id 999 not found");

        verify(fruitRepository).findById(999L);

    }
}
