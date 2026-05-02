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

[7.4 Repositorios con JpaRepository](#74-repositorios-con-jparepository)

[8 MICROSERVICIOS IMPLEMENTADOS - AA3](#8-microservicios-implementados---aa3)

[8.1 Product Service](#81-product-service)

[8.2 User Service](#82-user-service)

[8.3 Order Service](#83-order-service)

[8.4 Frontend Service](#84-frontend-service)

[8.5 Comunicación Asíncrona con RabbitMQ](#85-comunicación-asíncrona-con-rabbitmq)

[9 BASE DE DATOS](#9-base-de-datos)

[10 ORQUESTACIÓN CON DOCKER COMPOSE](#10-orquestación-con-docker-compose)

[11 ESTRUCTURA DEL CÓDIGO](#11-estructura-del-código)

[12 CONCLUSIONES](#12-conclusiones)

---

# 1. INTRODUCCIÓN

Desarrollar una plataforma de ventas enfocada en emprendedores es la base de este proyecto, pensado como un marketplace similar a la sección de compras de Facebook o la app de OLX. En esta tercera fase (AA3), el proyecto se centra en la implementación robusta de la **capa de persistencia** utilizando **Spring Data JPA** como tecnología de acceso a datos.

Si bien en la fase anterior (AA2) se definió la arquitectura de microservicios, en esta evidencia se profundiza en el mapeo objeto-relacional (ORM) mediante anotaciones JPA, la configuración de las fuentes de datos, la definición de repositorios y el establecimiento de relaciones entre entidades. Todo ello sobre bases de datos MySQL independientes para cada microservicio, gestionadas mediante contenedores Docker.

# 2. OBJETIVO

Implementar la capa de persistencia de datos en una arquitectura de microservicios utilizando **Spring Data JPA**, garantizando el correcto mapeo objeto-relacional de las entidades del sistema, la configuración adecuada del acceso a datos y la implementación de operaciones CRUD a través de repositorios especializados.

## 2.1 Objetivo general

Implementar la capa de persistencia utilizando Spring Data JPA en los microservicios del marketplace, asegurando la integridad y correcta gestión de los datos en MySQL.

## 2.2 Objetivos específicos

* Configurar Spring Data JPA en cada microservicio con las propiedades de conexión a base de datos.
* Definir las entidades JPA con anotaciones como `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`.
* Establecer relaciones entre entidades utilizando anotaciones de mapeo (`@OneToMany`, `@ManyToOne`, `@OneToOne`).
* Implementar interfaces de repositorio extendiendo `JpaRepository`.
* Desarrollar métodos de consulta personalizados en los repositorios (métodos derivados).

# 3. ALCANCE

Dentro de la presente evidencia se garantiza la correcta implementación de la capa de persistencia para el registro de usuarios, la creación de catálogos de productos y la gestión de pedidos, con el respaldo de Spring Data JPA y MySQL. Cada microservicio cuenta con su propia base de datos MySQL independiente, entidades JPA y repositorios específicos.

**No incluye:**

* **Integración a pasarela de pago real:** No se contempla conectar servicios de pago en línea. En su lugar, se implementa una simulación lógica del pago.
* **Gestión avanzada de envíos y logística:** El sistema no incluye integración con servicios de transporte ni seguimiento de pedidos en tiempo real.
* **Autenticación con JWT avanzado:** La autenticación se maneja mediante validación de credenciales con BCrypt, sin implementar tokens JWT completos ni OAuth2.

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
| Lombok | - | Reducción de código boilerplate |
| BCrypt | - | Encriptación de contraseñas |

# 5. ARQUITECTURA DEL PROYECTO

Se mantiene la arquitectura de microservicios definida en AA2, donde cada servicio es una aplicación Spring Boot independiente. En esta fase, el énfasis está en la **capa de persistencia**, implementada con Spring Data JPA en cada microservicio, siguiendo el patrón **Repository** como interfaz de acceso a datos.

La estructura por capas dentro de cada microservicio es:

* **Controller:** Maneja las solicitudes HTTP (REST API).
* **Service:** Contiene la lógica de negocio.
* **Repository:** Capa de acceso a datos mediante JPA (Spring Data).
* **Entity (Model):** Clases que representan las tablas de la base de datos, con anotaciones JPA.

# 6. DIAGRAMA DE ARQUITECTURA

El diagrama muestra la arquitectura basada en microservicios donde el cliente se comunica con el sistema a través del Frontend Service. Los servicios acceden a sus respectivas bases de datos MySQL mediante JPA/Hibernate, y se comunican entre sí de forma asíncrona usando RabbitMQ.

```
┌─────────────┐     ┌─────────────────────────────────────────────┐
│   Usuario   │────▶│            Frontend Service                  │
└─────────────┘     │         (Spring Boot + Thymeleaf)            │
                     │                (Puerto 8082)                │
                     └───────────┬──────────────────┬──────────────┘
                                 │                  │
                                 ▼                  ▼
                    ┌──────────────────────┐  ┌─────────────────────┐
                    │   Product Service    │  │    User Service     │
                    │   (Puerto 8080)      │  │   (Puerto 8081)     │
                    └──────────┬───────────┘  └──────────┬──────────┘
                               │                         │
                               ▼                         ▼
                    ┌──────────────────────┐  ┌─────────────────────┐
                    │    order Service     │  │      RabbitMQ       │
                    │   (Puerto 8083)      │  │   (Mensajería)      │
                    └──────────┬───────────┘  └─────────────────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      MySQL 8         │
                    │  productdb / userdb  │
                    │     / orderdb        │
                    └──────────────────────┘
```

**Nota:** Cada microservicio tiene su propia base de datos MySQL:
- Product Service → `productdb` (contendor `mysql-product`, puerto 3307)
- User Service → `userdb` (contenedor `mysql-user`, puerto 3308)
- Order Service → `orderdb` (contenedor `mysql-order`, puerto 3309)

# 7. CAPA DE PERSISTENCIA CON SPRING DATA JPA

## 7.1 Configuración de Spring Data JPA

Cada microservicio cuenta con su propia configuración de Spring Data JPA en el archivo `application.yml`, definiendo la conexión a su base de datos MySQL independiente.

**Ejemplo de configuración (product-service):**

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/productdb
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  rabbitmq:
    host: rabbitmq
    port: 5672
    username: guest
    password: guest
```

**Configuración de cada microservicio:**

| Microservicio | URL JDBC | Puerto expuesto | BD |
|:---|---|:---:|:---|
| Product Service | `jdbc:mysql://mysql:3306/productdb` | 8080 | `productdb` |
| User Service | `jdbc:mysql://mysql-user:3306/userdb` | 8081 | `userdb` |
| Order Service | `jdbc:mysql://mysql-order:3306/orderdb` | 8083 | `orderdb` |

**Parámetros clave de configuración JPA:**

| Propiedad | Valor | Descripción |
|:---|---:|:---|
| `spring.jpa.hibernate.ddl-auto` | `update` | Hibernate sincroniza el esquema con las entidades sin borrar datos existentes. |
| `spring.jpa.show-sql` | `true` | Muestra las consultas SQL generadas en la consola (útil para depuración). |

La estrategia `ddl-auto: update` permite que Hibernate genere y actualice automáticamente las tablas de la base de datos a partir de las entidades JPA, sin necesidad de scripts SQL manuales, facilitando el desarrollo iterativo.

## 7.2 Anotaciones JPA en las Entidades

Se utilizan anotaciones JPA estándar para el mapeo objeto-relacional. A continuación se detallan las anotaciones empleadas y su propósito:

### 7.2.1 Anotaciones de nivel de clase

| Anotación | Propósito |
|:---|---|
| `@Entity` | Marca la clase como una entidad JPA gestionada por Hibernate. |
| `@Table(name = "users")` | Especifica el nombre de la tabla en la base de datos. |
| `@Data` (Lombok) | Genera automáticamente getters, setters, toString, equals y hashCode. |
| `@NoArgsConstructor` (Lombok) | Genera constructor sin argumentos (requerido por JPA). |
| `@AllArgsConstructor` (Lombok) | Genera constructor con todos los argumentos. |

### 7.2.2 Anotaciones de nivel de campo

| Anotación | Propósito |
|:---|---|
| `@Id` | Define la clave primaria de la entidad. |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | Indica que el valor de la clave primaria se genera automáticamente por la base de datos (auto-increment). |
| `@Column(unique = true)` | Especifica que la columna debe tener valores únicos (restricción UNIQUE en la BD). |
| `@Enumerated(EnumType.STRING)` | Mapea un enum Java a una columna de tipo String en la BD. |
| `@ManyToOne` | Define una relación muchos-a-uno. |
| `@OneToMany` | Define una relación uno-a-muchos. |
| `@OneToOne` | Define una relación uno-a-uno. |
| `@JoinColumn(name = "product_id")` | Especifica la columna foránea en la tabla. |
| `@PrePersist` | Método callback ejecutado antes de persistir la entidad. |
| `@PreUpdate` | Método callback ejecutado antes de actualizar la entidad. |

### 7.2.3 Entidad Product (product-service)

```java
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private String status;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductAttribute> attributes;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private ProductLocation location;

    private String brand;
    private String conditionType;   // NEW, USED, REFURBISHED
    private String slug;
    private Integer views;
    private Boolean featured;
    private LocalDateTime updatedAt;
}
```

**Entidades relacionadas (product-service):**

| Entidad | Tabla | Relación con Product |
|:---|---|:---:|
| `Category` | `categories` | `@ManyToOne` (varios productos → una categoría) |
| `ProductImage` | `product_images` | `@OneToMany` (un producto → varias imágenes) |
| `ProductAttribute` | `product_attributes` | `@OneToMany` (un producto → varios atributos) |
| `ProductLocation` | `product_locations` | `@OneToOne` (un producto → una ubicación) |

### 7.2.4 Entidad User (user-service)

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

    private String phone;
    private String status;       // ACTIVE / INACTIVE / BLOCKED
    private LocalDateTime createdAt;

    @NotBlank
    private String password;
    private String role;         // ADMIN, SELLER, CUSTOMER
    private Boolean enabled;
}
```

### 7.2.5 Entidad Order (order-service)

```java
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long sellerId;
    private Long buyerId;

    // snapshot embebido del producto
    private String productName;
    private Double productPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;   // PENDING, ACCEPTED, REJECTED, IN_COORDINATION, COMPLETED, CANCELLED

    // coordinación OLX
    private String paymentMethod;
    private String deliveryMethod;
    private String meetingPoint;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.status = (this.status == null) ? OrderStatus.PENDING : this.status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
```

### 7.2.6 Entidad OrderMessage (order-service)

```java
@Entity
@Table(name = "order_messages")
@Data
@AllArgsConstructor
public class OrderMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long senderId;
    private String senderRole;    // BUYER / SELLER
    private String message;
    private Boolean readMessage;
    private LocalDateTime createdAt;

    public OrderMessage() {
        this.readMessage = false;
        this.createdAt = LocalDateTime.now();
    }
}
```

### 7.2.7 Entidad ProductSnapshot (order-service)

```java
@Entity
@Table(name = "product_snapshot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSnapshot {

    @Id
    private Long productId;      // SIN @GeneratedValue, se asigna manualmente

    private String name;
    private Double price;
    private Long sellerId;
    private Boolean available;
}
```

### 7.2.8 Entidad UserSnapshot (order-service)

```java
@Entity
@Table(name = "user_snapshot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSnapshot {

    @Id
    private Long userId;         // SIN @GeneratedValue, se asigna manualmente

    private String name;
    private String email;
    private String phone;
    private Boolean enabled;
}
```

## 7.3 Relaciones entre Entidades

### 7.3.1 Relaciones en Product Service

El microservicio de productos implementa las relaciones JPA más complejas del sistema:

**Product → Category (ManyToOne):**
```java
// En Product.java
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "category_id")
private Category category;

// En Category.java
@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
private List<Product> products;
```

**Product → ProductImage (OneToMany):**
```java
// En Product.java
@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<ProductImage> images;

// En ProductImage.java
@ManyToOne
@JoinColumn(name = "product_id")
private Product product;
```

**Product → ProductAttribute (OneToMany):**
```java
// En Product.java
@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private List<ProductAttribute> attributes;

// En ProductAttribute.java
@ManyToOne
@JoinColumn(name = "product_id")
private Product product;
```

**Product → ProductLocation (OneToOne):**
```java
// En Product.java
@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
private ProductLocation location;

// En ProductLocation.java
@OneToOne
@JoinColumn(name = "product_id")
private Product product;
```

### 7.3.2 Relaciones en Order Service

**Order → OrderMessage (OneToMany / ManyToOne):**
```java
// OrderMessage.java (lado propietario)
@ManyToOne
@JoinColumn(name = "order_id")
private Order order;
```

Nota: La entidad `Order` no tiene una relación `@OneToMany` explícita hacia `OrderMessage`. La relación es solo desde el lado de `OrderMessage` mediante `@ManyToOne`.

### 7.3.3 Resumen de relaciones JPA

| Microservicio | Entidad A | Entidad B | Tipo Relación | FK |
|:---|:---|---|:---:|:---:|
| Product | Product | Category | `@ManyToOne` / `@OneToMany` | `category_id` |
| Product | Product | ProductImage | `@OneToMany` / `@ManyToOne` | `product_id` |
| Product | Product | ProductAttribute | `@OneToMany` / `@ManyToOne` | `product_id` |
| Product | Product | ProductLocation | `@OneToOne` / `@OneToOne` | `product_id` |
| Order | OrderMessage | Order | `@ManyToOne` | `order_id` |

## 7.4 Repositorios con JpaRepository

Spring Data JPA proporciona interfaces que encapsulan las operaciones de acceso a datos. Cada microservicio implementa sus propios repositorios extendiendo `JpaRepository`.

### 7.4.1 Repositorios de Product Service

```java
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);           // Productos por vendedor

    List<Product> findByCategoryId(Long categoryId);   // Productos por categoría

    List<Product> findByStatus(String status);         // Productos por estado
}
```

### 7.4.2 Repositorios de User Service

```java
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);          // Búsqueda por email (login)

    boolean existsByEmail(String email);               // Validación de unicidad
}
```

```java
public interface ProductEventLogRepository extends JpaRepository<ProductEventLog, Long> {
}
```

### 7.4.3 Repositorios de Order Service

```java
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByBuyerId(Long buyerId);           // Órdenes como comprador
    List<Order> findBySellerId(Long sellerId);         // Órdenes como vendedor
}
```

```java
public interface OrderMessageRepository extends JpaRepository<OrderMessage, Long> {
    List<OrderMessage> findByOrderId(Long orderId);    // Mensajes de una orden
}
```

```java
public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot, Long> {
}
```

```java
public interface UserSnapshotRepository extends JpaRepository<UserSnapshot, Long> {
}
```

### 7.4.4 Métodos derivados (Query Methods)

Spring Data JPA permite definir consultas sin escribir SQL mediante **métodos derivados**. El framework parsea el nombre del método y genera la consulta automáticamente.

**Sintaxis:** `findBy` + `[Propiedad]` + `[Criterio]`

| Método | Consulta SQL generada |
|:---|---|
| `findByEmail(String email)` | `SELECT * FROM users WHERE email = ?` |
| `existsByEmail(String email)` | `SELECT COUNT(*) FROM users WHERE email = ?` |
| `findByBuyerId(Long buyerId)` | `SELECT * FROM orders WHERE buyer_id = ?` |
| `findBySellerId(Long sellerId)` | `SELECT * FROM orders WHERE seller_id = ?` |
| `findByOrderId(Long orderId)` | `SELECT * FROM order_messages WHERE order_id = ?` |
| `findByUserId(Long userId)` | `SELECT * FROM products WHERE user_id = ?` |
| `findByCategoryId(Long categoryId)` | `SELECT * FROM products WHERE category_id = ?` |
| `findByStatus(String status)` | `SELECT * FROM products WHERE status = ?` |

# 8. MICROSERVICIOS IMPLEMENTADOS - AA3

## 8.1 Product Service

- **Puerto:** 8080 (contenedor: `product-service`)
- **Base de datos:** `productdb` (contenedor: `mysql-product`, puerto: 3307)
- **Repositorio:** `ProductRepository extends JpaRepository<Product, Long>`
- **Endpoints REST:** CRUD de productos, búsqueda por usuario/categoría/estado

**Entidades JPA:**

| Tabla | Anotaciones | Relaciones |
|:---|---|:---:|
| `products` | `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne`, `@OneToMany`, `@OneToOne` | Category, ProductImage, ProductAttribute, ProductLocation |
| `categories` | `@Entity`, `@Id`, `@GeneratedValue`, `@OneToMany` | Product |
| `product_images` | `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne` | Product |
| `product_attributes` | `@Entity`, `@Id`, `@GeneratedValue`, `@ManyToOne` | Product |
| `product_locations` | `@Entity`, `@Id`, `@GeneratedValue`, `@OneToOne` | Product |

**Novedades en AA3:**
- Implementación completa de relaciones JPA (OneToMany, ManyToOne, OneToOne) entre entidades.
- Publicación de eventos `ProductCreatedEvent` en RabbitMQ al crear un producto.
- Envío del evento con eliminación del header `__TypeId__` para compatibilidad entre servicios.

## 8.2 User Service

- **Puerto:** 8081 (contenedor: `user-service`)
- **Base de datos:** `userdb` (contenedor: `mysql-user`, puerto: 3308)
- **Repositorios:** `UserRepository`, `ProductEventLogRepository` (ambos `extends JpaRepository`)

**Entidades JPA:**

| Tabla | Campos | Anotaciones |
|:---|---|:---:|
| `users` | id, name, email, password, phone, status, role, enabled, createdAt | `@Entity`, `@Id`, `@GeneratedValue`, `@Column(unique = true)` |
| `product_event_log` | id, productId, userId, name, price, receivedAt | `@Entity`, `@Id`, `@GeneratedValue` |

**Novedades en AA3:**
- Se agregaron los campos `password` (encriptado con BCrypt), `role` (CUSTOMER, SELLER, ADMIN), `enabled`.
- Se implementó el endpoint `POST /users/login` para autenticación (valida email + password contra BCrypt).
- Se implementó el endpoint `POST /users` con validación de email duplicado mediante `existsByEmail`.
- Se agregó el DTO `RegisterRequest` para el registro de usuarios.
- Se publica un evento `UserCreatedEvent` en RabbitMQ cuando se registra un nuevo usuario.
- Se consume eventos de producto desde Product Service mediante `ProductEventConsumer`, almacenando logs en la tabla `product_event_log`.

## 8.3 Order Service

- **Puerto:** 8083 (contenedor: `order-service`)
- **Base de datos:** `orderdb` (contenedor: `mysql-order`, puerto: 3309)
- **Repositorios:** `OrderRepository`, `OrderMessageRepository`, `ProductSnapshotRepository`, `UserSnapshotRepository`

**Entidades JPA:**

| Tabla | Campos | Anotaciones clave |
|:---|---|:---|
| `orders` | id, productId, sellerId, buyerId, productName, productPrice, status (ENUM), paymentMethod, deliveryMethod, meetingPoint, createdAt, updatedAt | `@Entity`, `@Enumerated(EnumType.STRING)`, `@PrePersist`, `@PreUpdate` |
| `order_messages` | id, orderId (FK), senderId, senderRole, message, readMessage, createdAt | `@Entity`, `@ManyToOne`, `@JoinColumn` |
| `product_snapshot` | productId (PK manual), name, price, sellerId, available | `@Entity`, `@Id` (sin `@GeneratedValue`) |
| `user_snapshot` | userId (PK manual), name, email, phone, enabled | `@Entity`, `@Id` (sin `@GeneratedValue`) |

**Novedades en AA3:**
- Se implementó el **patrón Snapshot** con las entidades `ProductSnapshot` y `UserSnapshot`, que almacenan copias inmutables de datos de producto y usuario al momento de crear una orden.
- Se implementó la relación `@ManyToOne` entre `OrderMessage` y `Order`.
- Se consume eventos de producto desde Product Service, almacenando snapshots automáticamente.
- Se consume eventos de usuario desde User Service (cuando se registra un usuario, se crea un `UserSnapshot`).
- Manejo de estados de orden mediante `enum OrderStatus`: PENDING, ACCEPTED, REJECTED, IN_COORDINATION, COMPLETED, CANCELLED.
- Callbacks `@PrePersist` y `@PreUpdate` para manejo automático de fechas `createdAt` y `updatedAt`.

## 8.4 Frontend Service

- **Puerto:** 8082 (contenedor: `frontend-service`)
- **Tecnología:** Spring Boot + Thymeleaf + HTML5 + CSS3 + JavaScript

**Novedades en AA3:**
- Integración completa con login: formulario que consume `POST /users/login` de User Service.
- Registro de usuarios con encriptación de contraseña.
- Vista de detalle de producto con información del vendedor y ubicación.
- Flujo de solicitud de pedido ("Me interesa") que crea una orden.
- Diseño UI/UX renovado con estilo tipo OLX (border-radius, sombras, colores personalizados).

## 8.5 Comunicación Asíncrona con RabbitMQ

Los microservicios se comunican de forma asíncrona a través de RabbitMQ, lo que permite el desacoplamiento y la tolerancia a fallos.

**Configuración de exchanges, colas y routing keys:**

| Microservicio origen | Evento | Exchange | Routing Key | Cola destino | Microservicio destino |
|:---|---|:---:|:---:|:---|:---:|
| Product Service | ProductCreated | `product.exchange` | `product.created` | `product.created.queue` | Product Service (propia) |
| Product Service | ProductCreated | `product.exchange` | `product.created` | `product.created.user.queue` | User Service |
| Product Service | ProductCreated | `product.exchange` | `product.created` | `product.created.order.queue` | Order Service |
| User Service | UserCreated | `user.exchange` | `user.created` | `user.created.queue` | User Service (propia) |
| User Service | UserCreated | `user.exchange` | `user.created` | `user.created.queue` | Order Service |
| Order Service | OrderCreated | `order.exchange` | `order.created` | `order.created.queue` | Order Service (propia) |

**Flujo de ejemplo: creación de un producto**

1. Un vendedor crea un producto en Product Service (POST `/products`).
2. Product Service guarda el producto en su BD `productdb`.
3. Product Service publica un `ProductCreatedEvent` en `product.exchange` con routing key `product.created`.
4. **User Service** consume el evento: crea un registro en `product_event_log` con los datos del producto.
5. **Order Service** consume el evento: crea un `ProductSnapshot` en `orderdb` con los datos del producto.

**Flujo de ejemplo: registro de un usuario**

1. Un usuario se registra en User Service (POST `/users`).
2. User Service guarda el usuario en su BD `userdb`.
3. User Service publica un `UserCreatedEvent` en `user.exchange` con routing key `user.created`.
4. **Order Service** consume el evento: crea un `UserSnapshot` en `orderdb` con los datos del usuario.

# 9. BASE DE DATOS

Cada microservicio gestiona su propia base de datos MySQL independiente, siguiendo el principio de aislamiento de datos en la arquitectura de microservicios.

**Tablas generadas automáticamente por JPA (ddl-auto: update):**

| Base de Datos | Tabla | Microservicio |
|:---|:---|:---:|
| `productdb` | `products` | Product Service |
| `productdb` | `categories` | Product Service |
| `productdb` | `product_images` | Product Service |
| `productdb` | `product_attributes` | Product Service |
| `productdb` | `product_locations` | Product Service |
| `userdb` | `users` | User Service |
| `userdb` | `product_event_log` | User Service |
| `orderdb` | `orders` | Order Service |
| `orderdb` | `order_messages` | Order Service |
| `orderdb` | `product_snapshot` | Order Service |
| `orderdb` | `user_snapshot` | Order Service |

**Esquema de tablas:**

```
┌──────────────────────────┐
│  productdb: products     │
├──────────────────────────┤
│ id (PK)                  │
│ name                     │
│ description              │
│ price                    │
│ user_id                  │
│ category_id (FK) ────────┼──┐
│ status                   │  │
│ brand                    │  │
│ condition_type           │  │
│ slug                     │  │
│ views                    │  │
│ featured                 │  │
│ created_at               │  │
│ updated_at               │  │
└──────────────────────────┘  │
                              │
┌──────────────────────────┐  │
│  categories              │  │
├──────────────────────────┤  │
│ id (PK)                  │  │
│ name                     │  │
│ description              │  │
└──────────────────────────┘  │
                              │
┌──────────────────────────┐  │
│  product_images           │  │
├──────────────────────────┤  │
│ id (PK)                  │  │
│ url                      │  │
│ is_primary               │  │
│ product_id (FK) ─────────┼──┘
└──────────────────────────┘

┌──────────────────────────┐  ┌──────────────────────────┐
│  product_attributes       │  │  product_locations        │
├──────────────────────────┤  ├──────────────────────────┤
│ id (PK)                  │  │ id (PK)                  │
│ attribute_name           │  │ country                  │
│ attribute_value          │  │ city                     │
│ product_id (FK) ─────────┼──┤ address                  │
└──────────────────────────┘  │ latitude                 │
                              │ longitude                │
                              │ product_id (FK) ─────────┤
                              └──────────────────────────┘

┌──────────────────────┐      ┌────────────────────────────┐
│  userdb: users       │      │  product_event_log          │
├──────────────────────┤      ├────────────────────────────┤
│ id (PK)              │      │ id (PK)                    │
│ name                 │      │ product_id                 │
│ email (UNIQUE)       │      │ user_id                    │
│ password             │      │ name                       │
│ phone                │      │ price                      │
│ status               │      │ received_at                │
│ role                 │      └────────────────────────────┘
│ enabled              │
│ created_at           │
└──────────────────────┘

┌──────────────────────┐      ┌────────────────────────────┐
│  orderdb: orders     │      │  order_messages             │
├──────────────────────┤      ├────────────────────────────┤
│ id (PK)              │      │ id (PK)                    │
│ product_id           │      │ order_id (FK) ────────────►│
│ seller_id            │      │ sender_id                  │
│ buyer_id             │      │ sender_role                │
│ product_name         │      │ message                    │
│ product_price        │      │ read_message               │
│ status (ENUM)        │      │ created_at                 │
│ payment_method       │      └────────────────────────────┘
│ delivery_method      │
│ meeting_point        │      ┌────────────────────────────┐
│ created_at           │      │  product_snapshot           │
│ updated_at           │      ├────────────────────────────┤
└──────────────────────┘      │ product_id (PK)            │
                              │ name                       │
┌──────────────────────────┐  │ price                      │
│  user_snapshot            │  │ seller_id                  │
├──────────────────────────┤  │ available                  │
│ user_id (PK)             │  └────────────────────────────┘
│ name                     │
│ email                    │
│ phone                    │
│ enabled                  │
└──────────────────────────┘
```

# 10. ORQUESTACIÓN CON DOCKER COMPOSE

Se utilizó Docker Compose para orquestar todos los componentes del sistema, permitiendo un despliegue consistente y replicable.

**Servicios definidos en docker-compose.yml:**

| Servicio | Contenedor | Puerto | Depende de |
|:---|---|:---:|:---|
| `mysql` | `mysql-product` | 3307:3306 | - |
| `mysql-user` | `mysql-user` | 3308:3306 | - |
| `mysql-order` | `mysql-order` | 3309:3306 | - |
| `rabbitmq` | `rabbitmq` | 5672, 15672 | - |
| `product-service` | `product-service` | 8080:8080 | mysql, rabbitmq |
| `user-service` | `user-service` | 8081:8080 | mysql-user, rabbitmq |
| `order-service` | `order-service` | 8083:8080 | mysql-order, rabbitmq |
| `frontend-service` | `frontend-service` | 8082:8080 | product, user, order |

# 11. ESTRUCTURA DEL CÓDIGO

El proyecto está organizado siguiendo una arquitectura de microservicios, donde cada componente se desarrolla y despliega de forma independiente.

**Link Repositorio Github:** [https://github.com/fhuamanr/olx-certus-servicios](https://github.com/fhuamanr/olx-certus-servicios)

**Estructura de carpetas de cada microservicio:**

```
order-service/
├── src/main/java/pe/order/service/
│   ├── config/
│   │   └── RabbitMQConfig.java           # Configuración RabbitMQ (exchanges, colas, bindings)
│   ├── consumer/
│   │   └── ProductEventConsumer.java     # Consume eventos de Product Service
│   ├── controller/
│   │   └── OrderController.java          # Controlador REST
│   ├── dto/
│   │   └── MessageRequest.java           # DTO para envío de mensajes
│   ├── entity/
│   │   ├── Order.java                    # Entidad JPA (tabla: orders)
│   │   ├── OrderMessage.java             # Entidad JPA (tabla: order_messages)
│   │   ├── ProductSnapshot.java          # Entidad JPA (tabla: product_snapshot)
│   │   └── UserSnapshot.java             # Entidad JPA (tabla: user_snapshot)
│   ├── enums/
│   │   └── OrderStatus.java              # Enum: PENDING, ACCEPTED, REJECTED, etc.
│   ├── event/
│   │   ├── OrderCreatedEvent.java        # Evento de orden creada
│   │   └── ProductCreatedEvent.java      # Evento de producto creado (para deserializar)
│   ├── producer/
│   │   └── OrderEventProducer.java       # Publica eventos OrderCreated en RabbitMQ
│   ├── repository/
│   │   ├── OrderRepository.java          # JpaRepository<Order, Long>
│   │   ├── OrderMessageRepository.java   # JpaRepository<OrderMessage, Long>
│   │   ├── ProductSnapshotRepository.java # JpaRepository<ProductSnapshot, Long>
│   │   └── UserSnapshotRepository.java   # JpaRepository<UserSnapshot, Long>
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
│   │   └── RabbitMQConfig.java           # Configuración RabbitMQ (colas, exchanges)
│   ├── consumer/
│   │   └── ProductEventConsumer.java     # Consume eventos de producto
│   ├── controller/
│   │   └── UserController.java           # Controlador REST
│   ├── dto/
│   │   └── RegisterRequest.java          # DTO para registro
│   ├── entity/
│   │   ├── LoginRequest.java             # DTO para login
│   │   ├── LoginResponse.java            # DTO respuesta login
│   │   ├── PasswordConfig.java           # Configuración BCrypt
│   │   ├── ProductEventLog.java          # Entidad JPA (tabla: product_event_log)
│   │   └── User.java                     # Entidad JPA (tabla: users)
│   ├── event/
│   │   ├── ProductCreatedEvent.java      # Evento de producto creado (deserialización)
│   │   └── UserCreatedEvent.java         # Evento de usuario creado
│   ├── exception/
│   │   └── GlobalExceptionHandler.java   # Manejador global de errores
│   ├── producer/
│   │   └── UserEventProducer.java        # Publica eventos UserCreated en RabbitMQ
│   ├── repository/
│   │   ├── ProductEventLogRepository.java # JpaRepository<ProductEventLog, Long>
│   │   └── UserRepository.java           # JpaRepository<User, Long>
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
│   │   └── RabbitMQConfig.java            # Configuración RabbitMQ
│   ├── controller/
│   │   └── ProductController.java         # Controlador REST
│   ├── entity/
│   │   ├── Category.java                  # Entidad JPA (tabla: categories)
│   │   ├── Product.java                   # Entidad JPA (tabla: products)
│   │   ├── ProductAttribute.java          # Entidad JPA (tabla: product_attributes)
│   │   ├── ProductImage.java              # Entidad JPA (tabla: product_images)
│   │   └── ProductLocation.java           # Entidad JPA (tabla: product_locations)
│   ├── event/
│   │   └── ProductCreatedEvent.java       # Evento de producto creado
│   ├── producer/
│   │   └── ProductEventProducer.java      # Publica eventos ProductCreated
│   ├── repository/
│   │   └── ProductRepository.java         # JpaRepository<Product, Long>
│   └── service/
│       └── ProductService.java            # Lógica de negocio
├── src/main/resources/
│   └── application.yml                    # Configuración JPA, BD, RabbitMQ
└── Dockerfile
```

# 12. CONCLUSIONES

Como equipo, concluimos que la implementación de la capa de persistencia con **Spring Data JPA** ha sido exitosa y cumple con los objetivos planteados para esta evidencia AA3.

**Sobre la implementación de JPA:**

1. **Configuración centralizada:** Se configuró Spring Data JPA en cada microservicio mediante archivos `application.yml`, utilizando `ddl-auto: update` para la generación automática de tablas a partir de las entidades, lo que agilizó el desarrollo y eliminó la necesidad de scripts SQL manuales.

2. **Mapeo objeto-relacional completo:** Se utilizaron anotaciones JPA estándar (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, `@Enumerated`, `@PrePersist`, `@PreUpdate`) para mapear todas las entidades del sistema a sus correspondientes tablas en MySQL.

3. **Relaciones entre entidades:** Se implementaron relaciones `@OneToMany`, `@ManyToOne` y `@OneToOne` entre las entidades en Product Service (Product → Category, Product → ProductImage, Product → ProductAttribute, Product → ProductLocation) y Order Service (OrderMessage → Order), utilizando estrategias de cascada (`CascadeType.ALL`) y eliminación de huérfanos (`orphanRemoval = true`).

4. **Repositorios especializados:** Se implementaron 8 interfaces de repositorio extendiendo `JpaRepository`, con métodos de consulta derivados como `findByEmail`, `existsByEmail`, `findByBuyerId`, `findBySellerId`, `findByOrderId`, `findByUserId`, `findByCategoryId` y `findByStatus`, demostrando la potencia de los **query methods** de Spring Data JPA.

5. **Patrón Snapshot:** Se implementaron las entidades `ProductSnapshot` y `UserSnapshot` con claves primarias manuales (sin `@GeneratedValue`), que almacenan copias inmutables de datos al momento de crear una orden, evitando inconsistencias si los datos originales se modifican posteriormente.

6. **Aislamiento de datos:** Cada microservicio cuenta con su propia base de datos MySQL independiente (`productdb`, `userdb`, `orderdb`), cumpliendo con el principio de aislamiento de datos en la arquitectura de microservicios.

**Sobre la arquitectura general:**

- La integración con **RabbitMQ** permite la comunicación asíncrona entre servicios, con 3 exchanges (`product.exchange`, `user.exchange`, `order.exchange`) que distribuyen eventos a colas específicas.
- Se implementó el envío de eventos desde Product Service (`ProductCreatedEvent`), User Service (`UserCreatedEvent`) y Order Service (`OrderCreatedEvent`).
- La autenticación de usuarios se fortaleció con encriptación **BCrypt** para contraseñas y roles de usuario (CUSTOMER, SELLER, ADMIN).
- Las UI de frontend se integraron completamente con los microservicios, proporcionando una experiencia de usuario completa: registro, login, creación de productos, detalle de productos y solicitud de pedidos.

Finalmente, la adopción de Spring Data JPA como tecnología de persistencia ha simplificado significativamente el acceso a datos, eliminando la escritura de código JDBC repetitivo y permitiendo al equipo centrarse en la lógica de negocio del marketplace.