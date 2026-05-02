**INSTITUTO DE EDUCACIÓN SUPERIOR TECNOLÓGICO PRIVADO CERTUS**  
**"Año De La Recuperación Y Consolidación De La Economía Peruana"**

**UNIDAD DIDÁCTICA:**  
**DISEÑO DE SOLUCIONES BASADA EN SERVICIOS**

**INTEGRANTES DEL GRUPO:** 

* JENIFER COLANA CHECCA  
* RENATO LUIS YANAMANGO CASANA  
* CARLOS WOLLEY LA TORRE MACHADO   
* ANDREA CALDASS QUISPITUPA  
* CARLOS FAUSTO HUAMAN RENGIFO  
* ANTHONY BRYAN AQUIJE CESPEDES

**AULA: 501M**

**CICLO: 5to**

**DOCENTE: FREDDY JAVIER SEGALES SANTOS**

**2026**

**ÍNDICE**

[1 INTRODUCCIÓN](#1-introducción)

[2 OBJETIVO](#2-objetivo)

[2.1 Objetivo general](#21-objetivo-general)

[2.2 Objetivos específicos](#22-objetivos-específicos)

[3 ALCANCE](#3-alcance)

[4 TECNOLOGÍAS UTILIZADAS](#4-tecnologías-utilizadas)

[5 ARQUITECTURA DEL PROYECTO](#5-arquitectura-del-proyecto)

[6 DIAGRAMA DE ARQUITECTURA](#6-diagrama-de-arquitectura)

[7 CAPA DE PERSISTENCIA CON SPRING DATA JPA](#7-capa-de-persistencia-con-spring-data-jpa)

[7.1 Configuración de Spring Data JPA](#71-configuración-de-spring-data-jpa)

[7.2 Anotaciones JPA en las Entidades](#72-anotaciones-jpa-en-las-entidades)

[7.3 Relaciones entre Entidades](#73-relaciones-entre-entidades)

[7.4 Repositorios con JpaRepository y CrudRepository](#74-repositorios-con-jparepository-y-crudrepository)

[8 MICROSERVICIOS IMPLEMENTADOS - AA3](#8-microservicios-implementados---aa3)

[8.1 Product Service (Puerto 8080)](#81-product-service-puerto-8080)

[8.2 User Service (Puerto 8082)](#82-user-service-puerto-8082)

[8.3 Order Service (Puerto 8081)](#83-order-service-puerto-8081)

[8.4 Frontend Service](#84-frontend-service)

[8.5 Comunicación Asíncrona con RabbitMQ](#85-comunicación-asíncrona-con-rabbitmq)

[9 BASE DE DATOS](#9-base-de-datos)

[10 ESTRUCTURA DEL CÓDIGO](#10-estructura-del-código)

[11 CONCLUSIONES](#11-conclusiones)

---

# 1. INTRODUCCIÓN

Desarrollar una plataforma de ventas enfocada en emprendedores es la base de este proyecto, pensado como un marketplace similar a la sección de compras de Facebook o la app de OLX. En esta tercera fase (AA3), el proyecto se centra en la implementación robusta de la **capa de persistencia** utilizando **Spring Data JPA** como tecnología de acceso a datos.

Si bien en la fase anterior (AA2) se definió la arquitectura de microservicios y se estableció la comunicación básica, en esta evidencia se profundiza en el mapeo objeto-relacional (ORM) mediante anotaciones JPA, la configuración de las fuentes de datos, la definición de repositorios y el establecimiento de relaciones entre entidades. Todo ello sobre una base de datos MySQL gestionada mediante contenedores Docker.

# 2. OBJETIVO

Implementar la capa de persistencia de datos en una arquitectura de microservicios utilizando **Spring Data JPA**, garantizando el correcto mapeo objeto-relacional de las entidades del sistema, la configuración adecuada del acceso a datos y la implementación de operaciones CRUD a través de repositorios especializados.

## 2.1 Objetivo general

Implementar la capa de persistencia utilizando Spring Data JPA en los microservicios del marketplace, asegurando la integridad y correcta gestión de los datos en MySQL.

## 2.2 Objetivos específicos

* Configurar Spring Data JPA en cada microservicio con las propiedades de conexión a base de datos.
* Definir las entidades JPA con anotaciones como `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`.
* Establecer relaciones entre entidades utilizando anotaciones de mapeo (`@OneToMany`, `@ManyToOne`, etc.).
* Implementar interfaces de repositorio extendiendo `JpaRepository` y `CrudRepository`.
* Desarrollar métodos de consulta personalizados en los repositorios (métodos derivados y consultas JPQL).

# 3. ALCANCE

Dentro de la presente evidencia se garantiza la correcta implementación de la capa de persistencia para el registro de usuarios, la creación de catálogos de productos y la gestión de pedidos, con el respaldo de Spring Data JPA y MySQL. Cada microservicio cuenta con su propia configuración de base de datos, entidades JPA y repositorios específicos.

**No incluye:**

* **Integración a pasarela de pago real:** No se contempla conectar servicios de pago en línea. En su lugar, se implementa una simulación lógica del pago.
* **Gestión avanzada de envíos y logística:** El sistema no incluye integración con servicios de transporte ni seguimiento de pedidos en tiempo real.
* **Autenticación con JWT avanzado:** La autenticación se maneja mediante lógica de validación de credenciales con BCrypt, sin implementar tokens JWT completos ni OAuth2.

# 4. TECNOLOGÍAS UTILIZADAS

El desarrollo se apoya en el ecosistema Java con **Spring Boot 3.x** y **Spring Data JPA** como tecnología central de persistencia. Para la base de datos se utiliza **MySQL 8**. La mensajería asíncrona se maneja con **RabbitMQ**, y toda la infraestructura se despliega sobre **Docker** y **Docker Compose**.

| Tecnología | Versión | Propósito |
|:---|:---:|:---|
| Java | 17 | Lenguaje de programación |
| Spring Boot | 3.x | Framework de desarrollo |
| Spring Data JPA | 3.x | Capa de persistencia ORM |
| Hibernate | 6.x | Implementación de JPA |
| MySQL | 8 | Base de datos relacional |
| RabbitMQ | 3.x | Broker de mensajería |
| Docker Compose | 3.8 | Orquestación de contenedores |
| Maven | 3.x | Gestión de dependencias |
| BCrypt | - | Encriptación de contraseñas |

# 5. ARQUITECTURA DEL PROYECTO

Se mantiene la arquitectura de microservicios definida en AA2, donde cada servicio es una aplicación Spring Boot independiente. En esta fase, el énfasis está en la **capa de persistencia**, implementada con Spring Data JPA en cada microservicio, siguiendo el patrón **Repository** como interfaz de acceso a datos.

La estructura por capas dentro de cada microservicio es:

* **Controller:** Maneja las solicitudes HTTP (REST API).
* **Service:** Contiene la lógica de negocio.
* **Repository:** Capa de acceso a datos mediante JPA (Spring Data).
* **Entity (Model):** Clases que representan las tablas de la base de datos.

# 6. DIAGRAMA DE ARQUITECTURA

El diagrama muestra la arquitectura basada en microservicios donde el cliente se comunica con el sistema a través del Frontend Service. Los servicios acceden a sus respectivas bases de datos MySQL mediante JPA/Hibernate, y se comunican entre sí de forma asíncrona usando RabbitMQ.

```
┌─────────────┐     ┌─────────────────────────────────────────────┐
│   Usuario   │────▶│            Frontend Service                  │
└─────────────┘     │         (Spring Boot + Thymeleaf)            │
                     └───────────┬──────────────────┬──────────────┘
                                 │                  │
                                 ▼                  ▼
                    ┌────────────────────┐  ┌────────────────────┐
                    │   API Gateway      │  │   Eureka Server    │
                    │  (Spring Gateway)  │  │ (Service Discovery)│
                    └────────┬───────────┘  └────────────────────┘
                             │
              ┌──────────────┼──────────────────┐
              ▼              ▼                  ▼
    ┌────────────────┐ ┌────────────┐ ┌────────────────┐
    │  Product       │ │  User      │ │  Order         │
    │  Service       │ │  Service   │ │  Service       │
    │  (Puerto 8080) │ │  (8082)    │ │  (8081)        │
    └───────┬────────┘ └─────┬──────┘ └───────┬────────┘
            │                │                │
            ▼                ▼                ▼
     ┌────────────┐  ┌────────────┐  ┌────────────┐
     │ MySQL      │  │ MySQL      │  │ MySQL      │
     │ products_db│  │ users_db   │  │ orders_db  │
     └────────────┘  └────────────┘  └────────────┘
            │                │                │
            └────────────────┼────────────────┘
                             ▼
                      ┌────────────┐
                      │  RabbitMQ  │
                      │   Broker   │
                      └────────────┘
```

# 7. CAPA DE PERSISTENCIA CON SPRING DATA JPA

## 7.1 Configuración de Spring Data JPA

Cada microservicio cuenta con su propia configuración de Spring Data JPA en el archivo `application.yml`, definiendo la conexión a su base de datos MySQL independiente.

**Configuración de datasource y JPA (ejemplo de order-service):**

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql-order:3306/order_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
```

**Parámetros clave de configuración:**

| Propiedad | Valor | Descripción |
|:---|---:|:---|
| `spring.datasource.url` | `jdbc:mysql://...` | URL de conexión a MySQL. `createDatabaseIfNotExist=true` crea la BD automáticamente. |
| `spring.datasource.driver-class-name` | `com.mysql.cj.jdbc.Driver` | Driver JDBC de MySQL 8. |
| `spring.jpa.hibernate.ddl-auto` | `update` | Hibernate sincroniza el esquema con las entidades sin borrar datos existentes. |
| `spring.jpa.show-sql` | `true` | Muestra las consultas SQL generadas en la consola (útil para depuración). |
| `spring.jpa.properties.hibernate.dialect` | `org.hibernate.dialect.MySQL8Dialect` | Dialecto específico de MySQL 8 para Hibernate. |

La estrategia `ddl-auto: update` permite que Hibernate genere y actualice automáticamente las tablas de la base de datos a partir de las entidades JPA, sin necesidad de scripts SQL manuales, facilitando el desarrollo iterativo.

## 7.2 Anotaciones JPA en las Entidades

Se utilizan anotaciones JPA estándar para el mapeo objeto-relacional. A continuación se detallan las anotaciones empleadas y su propósito:

### 7.2.1 Anotaciones de nivel de clase

| Anotación | Propósito |
|:---|---|
| `@Entity` | Marca la clase como una entidad JPA gestionada por Hibernate. |
| `@Table(name = "users")` | Especifica el nombre de la tabla en la base de datos. Si se omite, se usa el nombre de la clase. |

### 7.2.2 Anotaciones de nivel de campo

| Anotación | Propósito |
|:---|---|
| `@Id` | Define la clave primaria de la entidad. |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | Indica que el valor de la clave primaria se genera automáticamente por la base de datos (auto-increment). |
| `@Column(unique = true)` | Especifica que la columna debe tener valores únicos (restricción UNIQUE en la BD). |
| `@Column(name = "order_id")` | Personaliza el nombre de la columna en la tabla. |
| `@Enumerated(EnumType.STRING)` | Mapea un enum Java a una columna de tipo String en la BD. |
| `@Lob` | Indica que el campo debe almacenarse como un objeto grande (TEXT, BLOB). |
| `@CreationTimestamp` | Asigna automáticamente la fecha/hora de creación (no requiere código manual). |

### 7.2.3 Ejemplo de entidad: User (user-service)

```java
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    private String phone;
    private String status;       // ACTIVE / INACTIVE / BLOCKED
    private String role;         // ADMIN, SELLER, CUSTOMER
    private Boolean enabled;
    private LocalDateTime createdAt;

    // Getters y Setters
}
```

### 7.2.4 Ejemplo de entidad: Product (product-service)

```java
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Long userId;
    private Long categoryId;
    private String status;
    private LocalDateTime createdAt;

    // Getters y Setters
}
```

### 7.2.5 Ejemplo de entidad: Order (order-service)

```java
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long sellerId;
    private Long buyerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // PENDING, PAID, COMPLETED, CANCELLED

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderMessage> messages = new ArrayList<>();

    // Getters y Setters
}
```

### 7.2.6 Ejemplo de entidad: OrderMessage (order-service)

```java
@Entity
@Table(name = "orders_messages")
public class OrderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private Long authorId;
    private String content;
    private LocalDateTime timestamp;

    // Getters y Setters
}
```

### 7.2.7 Ejemplo de entidad: ProductSnapshot (order-service)

```java
@Entity
@Table(name = "product_snapshots")
public class ProductSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long sellerId;
    private String name;
    private Double price;
    private Boolean available;

    // Getters y Setters
}
```

## 7.3 Relaciones entre Entidades

En el microservicio de órdenes se implementan relaciones JPA entre las entidades `Order` y `OrderMessage`:

### Relación OneToMany / ManyToOne

**Order → OrderMessage**: Una orden puede tener múltiples mensajes.

```java
// En Order.java
@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
private List<OrderMessage> messages = new ArrayList<>();

// En OrderMessage.java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "order_id")
private Order order;
```

**Propósito de las anotaciones de relación:**

| Anotación | Significado |
|:---|---|
| `@OneToMany(mappedBy = "order")` | Indica que una orden puede tener muchos mensajes. El lado propietario de la relación es `OrderMessage`. |
| `@ManyToOne(fetch = FetchType.LAZY)` | Indica que muchos mensajes pertenecen a una orden. La carga perezosa (`LAZY`) evita traer la orden completa al consultar un mensaje. |
| `@JoinColumn(name = "order_id")` | Especifica la columna foránea en la tabla `orders_messages` que referencia a la tabla `orders`. |
| `cascade = CascadeType.ALL` | Propaga todas las operaciones (persist, merge, remove) desde Order a OrderMessage. |
| `orphanRemoval = true` | Elimina automáticamente los mensajes huérfanos cuando se remueven de la colección. |

## 7.4 Repositorios con JpaRepository y CrudRepository

Spring Data JPA proporciona interfaces que encapsulan las operaciones de acceso a datos, eliminando la necesidad de escribir código boilerplate. Cada microservicio implementa sus propios repositorios extendiendo `JpaRepository`.

### 7.4.1 JpaRepository vs CrudRepository

| Característica | `CrudRepository` | `JpaRepository` |
|:---|:---:|:---:|
| Operaciones CRUD básicas | ✓ | ✓ |
| Paginación y ordenación | - | ✓ |
| Métodos de lote | - | ✓ |
| Flush del contexto de persistencia | - | ✓ |
| Extiende de | `Repository` | `CrudRepository` y `PagingAndSortingRepository` |

En el proyecto se utiliza `JpaRepository` por su mayor flexibilidad y funcionalidades adicionales.

### 7.4.2 Repositorios implementados

**UserRepository (user-service):**

```java
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
```

- `findByEmail`: Método derivado que genera automáticamente la consulta `SELECT * FROM users WHERE email = ?`. Retorna un `Optional` para manejar el caso de usuario no encontrado.
- `existsByEmail`: Método derivado que genera `SELECT COUNT(*) FROM users WHERE email = ?`. Útil para validar unicidad antes de registrar un nuevo usuario.

**OrderRepository (order-service):**

```java
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByBuyerId(Long buyerId);

    List<Order> findBySellerId(Long sellerId);
}
```

- `findByBuyerId`: Consulta todas las órdenes donde el usuario es comprador.
- `findBySellerId`: Consulta todas las órdenes donde el usuario es vendedor.

**OrderMessageRepository (order-service):**

```java
public interface OrderMessageRepository extends JpaRepository<OrderMessage, Long> {

    List<OrderMessage> findByOrderIdOrderByTimestampAsc(Long orderId);
}
```

- `findByOrderIdOrderByTimestampAsc`: Consulta los mensajes de una orden, ordenados por fecha de manera ascendente. Combina un método derivado con ordenación.

**ProductSnapshotRepository (order-service):**

```java
public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot, Long> {
}
```

### 7.4.3 Métodos derivados vs consultas personalizadas

Spring Data JPA permite definir consultas sin escribir SQL mediante **métodos derivados** (query methods). El framework parsea el nombre del método y genera la consulta automáticamente.

**Sintaxis de métodos derivados:**

```
findBy + [Propiedad] + [Criterio opcional] + [Ordenación opcional]
```

| Método | Consulta SQL generada |
|:---|---|
| `findByEmail(String email)` | `SELECT * FROM users WHERE email = ?` |
| `existsByEmail(String email)` | `SELECT COUNT(*) FROM users WHERE email = ?` |
| `findByBuyerId(Long buyerId)` | `SELECT * FROM orders WHERE buyer_id = ?` |
| `findByOrderIdOrderByTimestampAsc(Long orderId)` | `SELECT * FROM orders_messages WHERE order_id = ? ORDER BY timestamp ASC` |

# 8. MICROSERVICIOS IMPLEMENTADOS - AA3

## 8.1 Product Service (Puerto 8080)

**Entidad JPA persistente:**

| Tabla | Campos | Anotaciones JPA |
|:---|:---|---|
| `products` | id, name, description, price, userId, categoryId, status, createdAt | `@Entity`, `@Table`, `@Id`, `@GeneratedValue` |

**Repositorio:**
- `ProductRepository extends JpaRepository<Product, Long>`

**Novedades en AA3:**
- Mejora en la entidad Product con campos adicionales y validaciones.
- Integración con RabbitMQ: publica eventos `ProductCreatedEvent` cuando se crea un producto.
- Los eventos se consumen de forma asíncrona por User Service y Order Service.

## 8.2 User Service (Puerto 8082)

**Entidad JPA persistente:**

| Tabla | Campos | Anotaciones JPA |
|:---|:---|---|
| `users` | id, name, email, password, phone, status, role, enabled, createdAt | `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column(unique = true)` |

**Repositorio:**
- `UserRepository extends JpaRepository<User, Long>`
- Métodos personalizados: `findByEmail`, `existsByEmail`

**Novedades en AA3:**
- Se agregó el campo `password` con encriptación BCrypt.
- Se agregó el campo `role` (CUSTOMER, SELLER, ADMIN).
- Se agregó el campo `enabled` para control de cuentas activas/inactivas.
- Se implementó el endpoint `POST /users/login` para autenticación.
- Se implementó el endpoint `POST /users` con DTO `RegisterRequest`.
- Se consume eventos de producto mediante RabbitMQ (almacena log en `ProductEventLog`).

## 8.3 Order Service (Puerto 8081)

**Entidades JPA persistentes:**

| Tabla | Campos | Anotaciones JPA |
|:---|:---|---|
| `orders` | id, productId, sellerId, buyerId, status, createdAt | `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Enumerated`, `@OneToMany` |
| `orders_messages` | id, orderId, authorId, content, timestamp | `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@JoinColumn` |
| `product_snapshots` | id, productId, sellerId, name, price, available | `@Entity`, `@Table`, `@Id`, `@GeneratedValue` |

**Repositorios:**
- `OrderRepository extends JpaRepository<Order, Long>`
- `OrderMessageRepository extends JpaRepository<OrderMessage, Long>`
- `ProductSnapshotRepository extends JpaRepository<ProductSnapshot, Long>`

**Novedades en AA3:**
- Se agregó la entidad `ProductSnapshot` para almacenar una copia de los datos del producto al momento de crear una orden (patrón Snapshot).
- Se implementó la relación `@OneToMany` / `@ManyToOne` entre Order y OrderMessage.
- Se consume eventos de producto desde Product Service a través de RabbitMQ, almacenando automáticamente snapshots.
- Se mejoró el manejo de estados de orden con `@Enumerated(EnumType.STRING)`.

## 8.4 Frontend Service

**Novedades en AA3:**
- Integración completa con el login: formulario de inicio de sesión que consume el endpoint `POST /users/login` de User Service.
- Registro de usuarios con encriptación de contraseña.
- Vista de detalle de producto con información del vendedor y ubicación.
- Flujo de solicitud de pedido ("Me interesa") que crea una orden.
- Diseño UI/UX renovado con estilo tipo OLX.

## 8.5 Comunicación Asíncrona con RabbitMQ

Los microservicios se comunican de forma asíncrona a través de RabbitMQ, lo que permite el desacoplamiento y la tolerancia a fallos.

**Configuración de colas y exchanges:**

| Microservicio | Evento | Exchange | Routing Key | Cola |
|:---|:---|---:|:---:|:---:|
| Product Service | Product Created | `product.exchange` | `product.created` | - |
| User Service (consume) | Product Created | `product.exchange` | `product.created` | `product.created.user.queue` |
| Order Service (consume) | Product Created | `product.exchange` | `product.created` | `product.created.order.queue` |
| Order Service | Order Created | `order.exchange` | `order.created` | `order.created.queue` |

**Ejemplo de flujo de eventos:**

1. Un vendedor crea un producto en Product Service.
2. Product Service guarda el producto en su base de datos MySQL.
3. Product Service publica un `ProductCreatedEvent` a través de RabbitMQ.
4. User Service consume el evento y registra un log del producto creado.
5. Order Service consume el evento y guarda un `ProductSnapshot` con los datos del producto, garantizando que los datos queden inmutables para futuras órdenes.

# 9. BASE DE DATOS

Cada microservicio gestiona su propia base de datos MySQL independiente, siguiendo el principio de aislamiento de datos en la arquitectura de microservicios.

**Tablas generadas automáticamente por JPA (ddl-auto: update):**

| Base de Datos | Tabla | Microservicio |
|:---|:---|:---:|
| `product_db` | `products` | Product Service |
| `user_db` | `users` | User Service |
| `order_db` | `orders` | Order Service |
| `order_db` | `orders_messages` | Order Service |
| `order_db` | `product_snapshots` | Order Service |

**Esquema de tablas:**

```
┌──────────────────────┐      ┌──────────────────────┐
│       products       │      │        users         │
├──────────────────────┤      ├──────────────────────┤
│ id (PK)              │      │ id (PK)              │
│ name                 │      │ name                 │
│ description          │      │ email (UNIQUE)       │
│ price                │      │ password             │
│ user_id              │      │ phone                │
│ category_id          │      │ status               │
│ status               │      │ role                 │
│ created_at           │      │ enabled              │
└──────────────────────┘      │ created_at           │
                              └──────────────────────┘

┌──────────────────────┐      ┌──────────────────────────┐
│       orders         │      │     orders_messages      │
├──────────────────────┤      ├──────────────────────────┤
│ id (PK)              │      │ id (PK)                  │
│ product_id           │◄─┐   │ order_id (FK) ──────────►│ orders.id
│ seller_id            │  │   │ author_id                 │
│ buyer_id             │  │   │ content                   │
│ status (ENUM)        │  │   │ timestamp                 │
│ created_at           │  │   └──────────────────────────┘
└──────────────────────┘  │
                          │   ┌──────────────────────────┐
                          │   │    product_snapshots     │
                          │   ├──────────────────────────┤
                          └───│ product_id               │
                              │ seller_id                │
                              │ name                     │
                              │ price                    │
                              │ available                │
                              └──────────────────────────┘
```

# 10. ESTRUCTURA DEL CÓDIGO

El proyecto está organizado siguiendo una arquitectura de microservicios, donde cada componente se desarrolla y despliega de forma independiente.

**Link Repositorio Github:** [https://github.com/fhuamanr/olx-certus-servicios](https://github.com/fhuamanr/olx-certus-servicios)

**Estructura de cada microservicio (patrón en capas):**

```
order-service/
├── src/main/java/pe/order/service/
│   ├── config/
│   │   └── RabbitMQConfig.java           # Configuración JMS/RabbitMQ
│   ├── consumer/
│   │   └── ProductEventConsumer.java     # Consume eventos de producto
│   ├── controller/
│   │   └── OrderController.java          # Controlador REST
│   ├── dto/
│   │   └── MessageRequest.java           # DTO para mensajes
│   ├── entity/
│   │   ├── Order.java                    # Entidad JPA
│   │   ├── OrderMessage.java             # Entidad JPA
│   │   └── ProductSnapshot.java          # Entidad JPA
│   ├── event/
│   │   ├── OrderCreatedEvent.java        # Evento de orden creada
│   │   └── ProductCreatedEvent.java      # Evento de producto creado
│   ├── producer/
│   │   └── OrderEventProducer.java       # Publica eventos en RabbitMQ
│   ├── repository/
│   │   ├── OrderRepository.java          # Repositorio JPA
│   │   ├── OrderMessageRepository.java   # Repositorio JPA
│   │   └── ProductSnapshotRepository.java # Repositorio JPA
│   └── service/
│       └── OrderService.java             # Lógica de negocio
├── src/main/resources/
│   └── application.yml                   # Configuración JPA, BD, RabbitMQ
└── Dockerfile
```

```
user-service/
├── src/main/java/pe/user/service/
│   ├── config/
│   │   ├── RabbitMQConfig.java           # Configuración RabbitMQ
│   │   └── OpenApiConfig.java            # Configuración Swagger
│   ├── consumer/
│   │   └── ProductEventConsumer.java     # Consume eventos de producto
│   ├── controller/
│   │   └── UserController.java           # Controlador REST
│   ├── dto/
│   │   └── RegisterRequest.java          # DTO para registro
│   ├── entity/
│   │   ├── User.java                     # Entidad JPA
│   │   ├── LoginRequest.java             # DTO para login
│   │   ├── LoginResponse.java            # DTO respuesta login
│   │   └── PasswordConfig.java           # Configuración BCrypt
│   ├── exception/
│   │   └── GlobalExceptionHandler.java   # Manejador global de errores
│   ├── repository/
│   │   └── UserRepository.java           # Repositorio JPA
│   └── service/
│       └── UserService.java              # Lógica de negocio
├── src/main/resources/
│   └── application.yml                   # Configuración JPA, BD, RabbitMQ
└── Dockerfile
```

```
product-service/
├── src/main/java/pe/product/service/
│   ├── config/
│   │   └── RabbitMQConfig.java           # Configuración RabbitMQ
│   ├── controller/
│   │   └── ProductController.java        # Controlador REST
│   ├── entity/
│   │   └── Product.java                  # Entidad JPA
│   ├── event/
│   │   └── ProductCreatedEvent.java      # Evento de producto creado
│   ├── producer/
│   │   └── ProductEventProducer.java     # Publica eventos en RabbitMQ
│   ├── repository/
│   │   └── ProductRepository.java        # Repositorio JPA
│   └── service/
│       └── ProductService.java           # Lógica de negocio
├── src/main/resources/
│   └── application.yml                   # Configuración JPA, BD, RabbitMQ
└── Dockerfile
```

# 11. CONCLUSIONES

Como equipo, concluimos que la implementación de la capa de persistencia con **Spring Data JPA** ha sido exitosa y cumple con los objetivos planteados para esta evidencia AA3.

**Sobre la implementación de JPA:**

1. **Configuración centralizada:** Se configuró Spring Data JPA en cada microservicio mediante archivos `application.yml`, utilizando `ddl-auto: update` para la generación automática de tablas a partir de las entidades, lo que agilizó el desarrollo y eliminó la necesidad de scripts SQL manuales.

2. **Mapeo objeto-relacional completo:** Se utilizaron anotaciones JPA estándar (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@Enumerated`, `@CreationTimestamp`) para mapear todas las entidades del sistema a sus correspondientes tablas en MySQL.

3. **Relaciones entre entidades:** Se implementaron relaciones `@OneToMany` y `@ManyToOne` entre las entidades Order y OrderMessage, utilizando estrategias de carga perezosa (`FetchType.LAZY`) y cascada de operaciones (`CascadeType.ALL`), garantizando la integridad referencial.

4. **Repositorios especializados:** Se implementaron interfaces de repositorio extendiendo `JpaRepository`, con métodos de consulta derivados como `findByEmail`, `existsByEmail`, `findByBuyerId` y `findByOrderIdOrderByTimestampAsc`, demostrando la potencia de los **query methods** de Spring Data JPA.

5. **Aislamiento de datos:** Cada microservicio cuenta con su propia base de datos MySQL independiente, cumpliendo con el principio de aislamiento de datos en la arquitectura de microservicios.

**Sobre la arquitectura general:**

- La integración con **RabbitMQ** permitió que los eventos de creación de productos sean consumidos de forma asíncrona por User Service y Order Service, demostrando comunicación entre microservicios sin acoplamiento directo.
- El **patrón Snapshot** implementado en Order Service (`ProductSnapshot`) garantiza que los datos del producto queden inmutables al momento de crear una orden, evitando inconsistencias si el producto se modifica posteriormente.
- La autenticación de usuarios se fortaleció con encriptación **BCrypt** para contraseñas y roles de usuario (CUSTOMER, SELLER, ADMIN).
- Las UI de frontend se integraron completamente con los microservicios, proporcionando una experiencia de usuario completa: registro, login, creación de productos, detalle de productos y solicitud de pedidos.

Finalmente, la adopción de Spring Data JPA como tecnología de persistencia ha simplificado significativamente el acceso a datos, eliminando la escritura de código JDBC repetitivo y permitiendo al equipo centrarse en la lógica de negocio del marketplace.