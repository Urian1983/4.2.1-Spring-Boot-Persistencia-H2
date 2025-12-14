package cat.itacademy.s04.s02.n01.fruit.repository;

import cat.itacademy.s04.s02.n01.fruit.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//Extendemos JPARepository, Fruit corresponde obviamente al objeto, Long es el tipo de la clave primaria
public interface FruitRepository extends JpaRepository<Fruit, Long>{


}