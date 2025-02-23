# Price Service API

## Descripción

Este proyecto es una API REST desarrollada con **Spring Boot**, que permite obtener el precio aplicable de un
producto en función de la fecha de aplicación, el identificador del producto y la cadena de tiendas.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.4.2**
- **Maven**
- **H2 Database** (Base de datos en memoria)
- **JPA (Spring Data)**
- **Lombok**
- **MapStruct**
- **OpenAPI** (Swagger UI)
- **JUnit & Mockito** (Para pruebas)

## Arquitectura

El proyecto sigue la arquitectura hexagonal (DDD) con las siguientes capas:

- **Domain**: Contiene la lógica de negocio.
- **Infrastructure**: Implementaciones de persistencia y adaptadores externos.
- **Application**: Casos de uso y servicios de aplicación.

## Decisión sobre la entidad `Brand`

Actualmente, la entidad `Brand` no ha sido agregada al dominio porque su complejidad y uso en el sistema no
justifican su inclusión en esta capa. Sin embargo, si la entidad `Brand` crece en funcionalidad o se convierte
en un elemento clave del negocio, sería recomendable trasladarla al dominio para garantizar un diseño más
robusto y flexible a futuro.

## Instalación y Ejecución

### Requisitos previos

- Tener instalado **Java 17** o superior.
- Tener instalado **Maven**.

### Clonar el repositorio

```bash
 git clone https://github.com/dfragar/pruebaInditex.git
 cd price-service
```

### Construir el proyecto

```bash
mvn clean install
```

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación se ejecutará en el puerto **8080**.

## Base de Datos

Se utiliza **H2 Database** en memoria. Los scripts **schema.sql** y **data.sql** inicializan la base de datos
al arrancar la aplicación.

Para acceder a la consola de H2, visitar:

```
http://localhost:8080/h2-console
```

Usar los siguientes datos:

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: *(vacío)*

## Endpoints

### Obtener el precio aplicable

**GET** `/api/prices/apply-price`

#### Parámetros de consulta:

- `productId` (Integer) - Identificador del producto.
- `brandId` (Integer) - Identificador de la cadena (1 = ZARA).
- `applicationDate` (LocalDateTime) - Fecha y hora de aplicación en formato `YYYY-MM-DDTHH:MM:SS`.

#### Ejemplo de petición:

```bash
curl -X GET "http://localhost:8080/api/prices/apply-price?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00"
```

#### Ejemplo de respuesta:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

## Testing

El proyecto incluye diferentes tipos de pruebas para garantizar la calidad y el correcto funcionamiento del
sistema:

- **Pruebas unitarias**: Se validan los casos de uso y la lógica de negocio mediante mocks para aislar las
  dependencias externas. Ejemplo:
    - `PriceServiceUserCaseTest` prueba la obtención de precios con y sin resultados en la base de datos.

- **Pruebas de integración**: Se testea la API REST con `MockMvc` para verificar el comportamiento del
  controlador sin necesidad de desplegar la aplicación. Ejemplo:
    - `PriceControllerAdapterTest` evalúa distintas combinaciones de `productId`, `brandId` y
      `applicationDate`.

- **Pruebas end-to-end (E2E)**: Se ejecutan pruebas contra un servidor real en un puerto aleatorio, asegurando
  la correcta integración de todos los componentes. Ejemplo:
    - `PriceControllerTest` verifica los precios devueltos en diferentes momentos del tiempo mediante
      `TestRestTemplate`.

Estos tests garantizan que la aplicación funcione correctamente en distintos niveles de abstracción y en
múltiples escenarios.

Para ejecutar lost test:

```bash
mvn test
```

### Casos de prueba

Se validan los siguientes escenarios:

1. Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA).
2. Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA).
3. Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA).
4. Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA).
5. Petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA).

## Documentación API (Swagger UI)

Para visualizar la documentación en **Swagger UI**, acceder a:

```
http://localhost:8080/swagger-ui/index.html
```


