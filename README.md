# Detector de Mutantes – Examen MercadoLibre

Proyecto desarrollado para la materia **Desarrollo de Software**.
La API detecta mutantes a partir de secuencias de ADN utilizando un algoritmo que analiza matrices NxN.

---

## Deploy en Render (API Online)

**Base URL:**
 [https://examen-mercadolibre-9qtw.onrender.com](https://examen-mercadolibre-9qtw.onrender.com)

**Swagger UI:**
 [https://examen-mercadolibre-9qtw.onrender.com/swagger-ui/index.html](https://examen-mercadolibre-9qtw.onrender.com/swagger-ui/index.html)

 **Captura 1 – Swagger funcionando en Render**

<img width="1349" height="677" alt="image" src="https://github.com/user-attachments/assets/3f68c7a9-f642-4f24-abc2-1ac0179e1071" />

---

## Tecnologías utilizadas

* Java **21**
* Spring Boot **4**
* Maven
* Spring Web
* Spring Data JPA
* Base de datos **H2** en memoria
* Docker
* Swagger / OpenAPI 3
* Render (deploy)

---

## Endpoints

---

### **POST /mutant**

Determina si un ADN pertenece a un mutante.

#### **Request**

```json
{
  "dna": ["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
```

#### **Responses**

* **200 OK** → Mutante
* **403 Forbidden** → Humano

**Captura 2 – Thunder Client con POST mutante (200 OK)**

<img width="714" height="473" alt="image" src="https://github.com/user-attachments/assets/fbf9c9f1-f2ab-4fad-802d-dd4146e483f4" />

**Captura 3 – Thunder Client con POST humano (403 Forbidden)**

<img width="721" height="465" alt="image" src="https://github.com/user-attachments/assets/0ae58303-f520-4d0e-b538-c14e111747d9" />

---

### **GET /stats**

Devuelve estadísticas del ADN analizado.

#### **Ejemplo de respuesta**

```json
{
  "count_mutant_dna": 1,
  "count_human_dna": 4,
  "ratio": 0.25
}
```

**Captura 4 – Thunder Client mostrando GET /stats**

<img width="709" height="538" alt="image" src="https://github.com/user-attachments/assets/5a8c46e5-26f0-4b63-a998-fa0a13c7f612" />

---

## ¿Cómo funciona el algoritmo?

El método:

```
isMutant(String[] dna)
```

Analiza la matriz NxN buscando **secuencias de 4 letras iguales consecutivas** en estos sentidos:

* Horizontal
* Vertical
* Diagonal

Si encuentra **más de una secuencia válida**, el ADN se clasifica como mutante.
Incluye validaciones de tamaño y simetría de la matriz.

---

## Arquitectura del Proyecto

```
src/main/java/com.example.mutant
 ├── controller
 │   ├── MutantController
 │   └── StatsController
 ├── service
 │   ├── MutantDetector
 │   ├── MutantService
 │   └── StatsService
 ├── repository
 │   └── DnaRecordRepository
 ├── model
 │   └── DnaRecord
 └── dto
     ├── DnaRequest
     └── StatsResponse
```
**Captura 5 – Estructura del proyecto en IntelliJ**

<img width="348" height="554" alt="image" src="https://github.com/user-attachments/assets/fa4b8279-f2ae-4ad5-a511-814a9921a210" />

---

## Base de Datos H2 (solo local)

**Consola H2:**
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)


---

## Dockerfile utilizado para Render

```dockerfile
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Captura 7 – Render “service live” con despliegue exitoso**

<img width="1341" height="664" alt="image" src="https://github.com/user-attachments/assets/f2bc5f00-c1f2-448d-bc3a-adc9ff516bdb" />



---

## Tests

Este proyecto incluye tests automáticos para verificar el correcto funcionamiento del algoritmo de detección de mutantes.
Los tests están ubicados en:

src/test/java/com/example/mutant

Para ejecutar los tests:

```
./mvnw test
```

---

## Nombre y legajo 
## Santiago Ariel Tejada - 50181

---
