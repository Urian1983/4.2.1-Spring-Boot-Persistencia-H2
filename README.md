# Fruit API H2

API REST para la gestión de stock de frutas desarrollada con Spring Boot, JPA y base de datos H2. Implementa operaciones CRUD básicas utilizando JPARepository.

## 🚀 Características

- API RESTful completamente funcional
- Operaciones CRUD básicas (Create, Read, Delete)
- Validación de datos con Bean Validation
- Manejo de excepciones personalizado
- Base de datos H2 persistente en archivo
- DTOs separados para request/response
- Tests unitarios con JUnit 5 y Mockito
- Soporte para Docker

## 🛠️ Tecnologías Utilizadas

- **Java 21**
- **Spring Boot 3.5.8**
- **Spring Data JPA** - Capa de persistencia
- **H2 Database** - Base de datos embebida
- **Lombok** - Reducción de código boilerplate
- **Bean Validation** - Validación de datos
- **Maven** - Gestión de dependencias
- **JUnit 5 & Mockito** - Testing
- **Docker** - Containerización

## 📋 Requisitos Previos

- JDK 21
- Maven 3.9 o superior
- Docker (opcional, para ejecución en contenedor)

## ⚙️ Instalación y Configuración

### Ejecución Local

1. **Clonar el repositorio**
```bash
git clone https://github.com/tu-usuario/fruit-api-h2.git
cd fruit-api-h2
```

2. **Compilar el proyecto**
```bash
./mvnw clean install
```

3. **Ejecutar la aplicación**
```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`

### Ejecución con Docker

1. **Construir y ejecutar con Docker Compose**
```bash
docker-compose up --build
```

2. **Detener los contenedores**
```bash
docker-compose down
```

Para eliminar también los volúmenes de datos:
```bash
docker-compose down -v
```

## 🗄️ Base de Datos H2

### Configuración Local
La base de datos se persiste en archivo en `./data/fruitdb`

### Consola H2
Puedes acceder a la consola de H2 para visualizar y gestionar los datos:

```
http://localhost:8080/h2-console
```

**Credenciales de acceso:**
- JDBC URL: `jdbc:h2:file:./data/fruitdb`
- Usuario: `sa`
- Contraseña: *(dejar en blanco)*

## 📚 Endpoints de la API

| Método | Endpoint | Descripción | Request Body |
|--------|----------|-------------|--------------|
| POST | `/fruits` | Crear una nueva fruta | `FruitRequestDTO` |
| GET | `/fruits` | Obtener todas las frutas | - |
| GET | `/fruits/{id}` | Obtener una fruta por ID | - |
| DELETE | `/fruits/{id}` | Eliminar una fruta | - |

### Modelo de Datos

**FruitRequestDTO:**
```json
{
  "name": "Apple",
  "weightInKilos": 5
}
```

**FruitResponseDTO:**
```json
{
  "id": 1,
  "name": "Apple",
  "weightInKilos": 5
}
```

### Validaciones

- `name`: No puede estar vacío
- `weightInKilos`: Debe ser mayor o igual a 1

### Ejemplos de Uso

**Crear una nueva fruta:**
```bash
curl -X POST http://localhost:8080/fruits \
  -H "Content-Type: application/json" \
  -d '{"name": "Apple", "weightInKilos": 5}'
```

**Obtener todas las frutas:**
```bash
curl -X GET http://localhost:8080/fruits
```

**Obtener una fruta por ID:**
```bash
curl -X GET http://localhost:8080/fruits/1
```

**Eliminar una fruta:**
```bash
curl -X DELETE http://localhost:8080/fruits/1
```

### Manejo de Errores

La API devuelve respuestas de error estructuradas:

**Fruta no encontrada (404):**
```json
{
  "timestamp": "2024-12-14T21:30:00",
  "message": "Fruit with id 999 not found",
  "status": 404
}
```

**Validación fallida (400):**
```json
{
  "timestamp": "2024-12-14T21:30:00",
  "message": "Fruit name can't be empty",
  "status": 400
}
```

## 📁 Estructura del Proyecto

```
src/
├── main/
│   ├── java/cat/itacademy/s04/s02/n01/fruit/
│   │   ├── controller/
│   │   │   └── FruitController.java
│   │   ├── dto/
│   │   │   ├── FruitRequestDTO.java
│   │   │   └── FruitResponseDTO.java
│   │   ├── exceptions/
│   │   │   ├── FruitNotFoundException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   ├── model/
│   │   │   └── Fruit.java
│   │   ├── repository/
│   │   │   └── FruitRepository.java
│   │   ├── service/
│   │   │   ├── FruitService.java
│   │   │   └── FruitServiceImpl.java
│   │   └── FruitApiH2Application.java
│   └── resources/
│       ├── application.properties
│       ├── application-docker.properties
│       └── application-docker.yml
└── test/
    └── java/cat/itacademy/s04/s02/n01/fruit/
        ├── controller/
        │   └── FruitControllerTest.java
        └── service/
            └── FruitServiceImplTest.java
```

## 🔧 Configuración

### application.properties

```properties
# Nombre de la aplicación
spring.application.name=fruit-api-h2

# Configuración H2
spring.datasource.url=jdbc:h2:file:./data/fruitdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Consola H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
```

### Perfiles de Configuración

- **Local**: `application.properties` (base de datos en archivo)
- **Docker**: `application-docker.properties` + `application-docker.yml` (configuración para contenedor)

## 🧪 Tests

Ejecutar los tests:

```bash
./mvnw test
```

El proyecto incluye:
- **Tests unitarios del controlador** con MockMvc
- **Tests unitarios del servicio** con Mockito
- Cobertura de casos exitosos y de error

## 🐳 Docker

### Dockerfile
Utiliza multi-stage build para optimizar el tamaño de la imagen:
1. Stage builder: Compila el proyecto con Maven
2. Stage final: Imagen ligera con JRE 21 Alpine

### Docker Compose
Configura la aplicación con:
- Puerto 8080 expuesto
- Volumen persistente para la base de datos H2
- Variables de entorno para configuración
- Perfil Spring `docker` activado

## 🎯 Funcionalidades Destacadas

- **JPARepository**: Uso de métodos CRUD heredados sin necesidad de implementación
- **DTOs**: Separación entre objetos de dominio y transferencia de datos
- **Validación**: Bean Validation en DTOs y entidades
- **Excepciones**: Manejo centralizado con `@RestControllerAdvice`
- **Lombok**: Reducción de código con anotaciones (@Data, @NoArgsConstructor, @AllArgsConstructor)
- **Testing**: Cobertura con JUnit 5 y Mockito

## 📝 Notas

- La base de datos H2 se persiste en el directorio `./data/`
- Los datos persisten entre reinicios de la aplicación
- En Docker, los datos se almacenan en un volumen Docker nombrado

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Haz fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Añade nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 👤 Autor

Josep J. Roca Blanco - IT Academy Sprint 4 - Tarea 2 - Nivel 1

---

**Task 4.2** - API REST con Spring Boot, JPA y H2
