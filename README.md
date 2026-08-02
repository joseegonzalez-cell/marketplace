# Marketplace 

Sistema de gestión para un supermercado tipo marketplace, construido con **Spring Boot 3**, **Java 17**, **PostgreSQL** y arquitectura en capas. Preparado para integración futura con frontend **Angular**.

---

## Tecnologías

| Tecnología | Versión |
|------------|---------|
| Java | 17 |
| Spring Boot | 3.2.x |
| Spring Data JPA | 3.2.x |
| PostgreSQL | 15+ |
| MapStruct | 1.5.5 |
| Lombok | Última |
| OpenAPI (Swagger) | 2.3.0 |

---

## Arquitectura
```
com.marketplace
├── config/         # Configuraciones globales
├── controller/     # Endpoints REST
├── dto/            # Objetos de transferencia (Records)
├── entity/         # Entidades JPA
├── exception/      # Manejo global de excepciones
├── mapper/         # MapStruct mappers
├── repository/     # Repositorios Spring Data
├── service/        # Interfaces de negocio
└── service/impl/   # Implementaciones de servicio
```

---

##  Esquemas de Base de Datos

| Schema | Propósito |
|--------|-----------|
| `core` | Clientes, usuarios, configuraciones |
| `inventory` | Productos, stock, categorías |
| `billing` | Facturas, detalles de factura, pagos |

---

## Configuración Local

### 1. Crear base de datos PostgreSQL

```sql
CREATE DATABASE marketplace_db;
CREATE SCHEMA core;
CREATE SCHEMA billing;
CREATE SCHEMA inventory;


2. Configurar credenciales
Edita: src/main/resources/application.properties:

spring.datasource.username=postgres
spring.datasource.password=password123

3. Ejecutar

./mvnw spring-boot:run
# o
mvn spring-boot:run
```
---

## Documentación API
### Una vez ejecutado el proyecto:
http://localhost:8080/api/v1/swagger-ui.html

### OpenAPI JSON
http://localhost:8080/api/v1/api-docs

---

## Entidades Principales

| Entidad          | Schema      | Descripción             |
| ---------------- | ----------- | ----------------------- |
| `Cliente`        | `core`      | Información de clientes |
| `Producto`       | `inventory` | Catálogo de productos   |
| `Factura`        | `billing`   | Encabezado de facturas  |
| `FacturaDetalle` | `billing`   | Líneas de cada factura  |

---

## Contribución
1. Fork del proyecto
2. **Crear rama:** git checkout -b feature/nueva-funcionalidad
3. **Commit:** git commit -m "feat: nueva funcionalidad"
4. **Push:** git push origin feature/nueva-funcionalidad
5. Crear Pull Request

---

## Autor
José Elver González D.
Desarrollador **Full Stack** en formación

---

## Contacto
- **Email:** josee0103gonzalez@gmail.com
- **LinkedIn:** https://www.linkedin.com/in/jose-elver-gonzalez-diaz-139991366

---

## Repositorio
https://github.com/joseegonzalez-cell/marketplace.git

---
