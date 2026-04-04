# 🚀 Product Service - OLX Microservice (MVP AA1)

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-4.x-green)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![Status](https://img.shields.io/badge/MVP-AA1-success)

---

## 📌 Descripción

Este proyecto corresponde al **Primer MVP (AA1)** del curso:

🎓 **DISEÑO DE SOLUCIONES BASADAS EN SERVICIOS - CERTUS**

Se ha desarrollado un microservicio tipo **Marketplace estilo OLX**, donde los usuarios pueden publicar productos (listings), sin manejo de inventario.

---

## 🧠 Enfoque Marketplace (OLX)

A diferencia de un eCommerce tradicional:

* ❌ No existe stock ni inventario
* 👤 Cada producto pertenece a un usuario ('userId')
* 📦 Se modela como un **listing (publicación)**
* 📍 Se incluye ubicación del producto
* 🧾 Se permite enriquecer con atributos dinámicos

---

## 🏗️ Modelo de Datos

### Entidades principales:

* Product
* Category
* ProductImage
* ProductAttribute
* ProductLocation

### Relaciones:

* Product → Category (**ManyToOne**)
* Product → Images (**OneToMany**)
* Product → Attributes (**OneToMany**)
* Product → Location (**OneToOne**)

---

## 🛠️ Stack Tecnológico

* ☕ Java 21
* 🌱 Spring Boot 4.x
* 🗄️ MySQL 8
* 🐳 Docker & Docker Compose
* 📦 Maven
* 🔗 REST API
* 🧪 Postman

---

## 📂 Estructura del Proyecto

```
src/main/java/pe/product/service

├── controller
├── entity
├── repository
├── services
└── ProductServiceApplication.java
```

---

## ⚙️ Funcionalidades

### 📦 PRODUCT (CORE)

* Crear producto completo (con imágenes, atributos y ubicación)
* Listar productos
* Obtener por ID
* Actualizar producto
* Eliminar producto

### 🏷️ CATEGORY

* CRUD completo

### 🖼️ PRODUCT IMAGE

* Crear
* Listar por producto
* Eliminar

### 🧾 PRODUCT ATTRIBUTE

* Crear
* Listar por producto
* Eliminar

### 📍 PRODUCT LOCATION

* Crear / actualizar
* Obtener
* Eliminar

---

## 🌐 Endpoints principales

### PRODUCT

| Método | Endpoint       |
| ------ | -------------- |
| GET    | /products      |
| GET    | /products/{id} |
| POST   | /products      |
| PUT    | /products/{id} |
| DELETE | /products/{id} |

---

## 🧪 Colección Postman

Se incluye colección completa para pruebas:

📁 **Certus_OLX_Postman_FULL.json**

Incluye:

* CRUD Product
* CRUD Category
* Gestión de imágenes
* Gestión de atributos
* Gestión de ubicación

---

## 🚀 Ejecución del Proyecto

### 1. Build del proyecto

```bash
mvn clean package
```

---

### 2. Levantar contenedores

```bash
docker-compose down -v
docker-compose up --build
```

---

### 3. Acceso API

```
http://localhost:8080/products
```

---

## 🐳 Contenedores

| Servicio | Puerto |
| -------- | ------ |
| MySQL    | 3307   |
| API      | 8080   |

---

## ⚠️ Requisitos

* Docker instalado
* Java 21
* Maven
* RAM recomendada: **8GB mínimo (ideal 16GB+)**

---

## 🧠 Roadmap (Siguientes MVPs)

* 🔐 Seguridad JWT
* 📘 Swagger / OpenAPI
* 👤 Microservicio de usuarios
* 🌐 API Gateway
* 🔔 Notificaciones
* 📊 Auditoría / logs

---

## 👨‍💻 Autoría

Proyecto desarrollado por alumnos Grupo 1 compuesto por alumnos de Certus del Curso:

**Diseño de Soluciones Basadas en Servicios**

---

## 🏆 Estado del Proyecto

✔ MVP AA1 completo
✔ Arquitectura base definida
✔ CRUD funcional
✔ Docker operativo
✔ Integración con Postman
✔ Modelo OLX implementado

---

## 🚀 Nota Técnica

Este proyecto ya está listo para evolucionar hacia:

* Arquitectura de microservicios real
* Integración con API Gateway
* Separación por dominios (User, Auth, Orders)

---
