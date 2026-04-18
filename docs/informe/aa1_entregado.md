**INSTITUTO DE EDUCACIÓN SUPERIOR TECNOLÓGICO PRIVADO CERTUS**  
**“Año De La Recuperación Y Consolidación De La Economía Peruana”**

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

[1 INTRODUCCIÓN:	3](#1-introducción:)

[2 OBJETIVO:	3](#2-objetivo:)

[2.1 Objetivo general	3](#2.1-objetivo-general)

[2.2 Objetivos específicos	3](#2.2-objetivos-específicos)

[3 ALCANCE:	3](#3-alcance:)

[4 TECNOLOGÍAS UTILIZADAS:	4](#4-tecnologías-utilizadas:)

[5 ARQUITECTURA DEL PROYECTO:	4](#5-arquitectura-del-proyecto:)

[6 DIAGRAMA DE ARQUITECTURA:	4](#6-diagrama-de-arquitectura:)

[7 DESCRIPCIÓN DE API:	5](#7-descripción-de-api:)

[8 ESTRUCTURA DEL CÓDIGO:	5](#8-estructura-del-código:)

[9 BASE DE DATOS:	7](#9-base-de-datos:)

[10 CONCLUSIONES:	7](#10-conclusiones:)

# 

# **1 INTRODUCCIÓN:** {#1-introducción:}

Desarrollar una plataforma de ventas enfocada en emprendedores es la base de este proyecto, piensa en algo similar a la sección de compras de Facebook o la app de OLX. Solo que aquí todo el respaldo técnico se construirá desde cero utilizando microservicios. Usaremos Java con Spring Boot, gestionaremos las dependencias mediante Maven y guardaremos la información en MySQL. Básicamente queremos que los usuarios publiquen y compren sin que un fallo en un área específica tumbe todo el sistema. Por esto cada función operará de forma aislada comunicándose a través de un API Gateway y un servidor Eureka que mantendrá el registro de toda la red.

# **2 OBJETIVO:** {#2-objetivo:}

Lograr que el sistema soporte un crecimiento real a futuro es nuestra meta principal, diseñar esta arquitectura no trata solo de separar código sino implica responsabilidades muy precisas como: independizar cada servicio, lograr que Eureka se encargue de descubrirlos automáticamente y obligar a que cualquier petición externa pase de forma obligatoria por el API Gateway. A la par, nos apoyaremos en contenedores de Docker para que el despliegue del proyecto sea manejable y replicable.

### **2.1 Objetivo general** {#2.1-objetivo-general}

Diseñar e implementar una arquitectura de microservicios escalable para un marketplace de productos.

### **2.2 Objetivos específicos** {#2.2-objetivos-específicos}

* Separar responsabilidades en servicios independientes.  
* Implementar un sistema de descubrimiento de servicios con Eureka.  
* Centralizar el acceso mediante un API Gateway.  
* Gestionar datos con MySQL.  
* Desplegar la solución usando Docker y Docker Compose.

# **3 ALCANCE:** {#3-alcance:}

Dentro de los límites del proyecto se garantiza el funcionamiento del registro de usuarios, la creación de catálogos por parte de los vendedores y la gestión de pedidos. Hay elementos que por cuestiones de tiempo y enfoque del curso quedarán fuera. Por ejemplo, los pagos se simularán lógicamente, sin conectar una pasarela real, y herramientas adicionales como un sistema de chat interno no forman parte de los entregables de esta fase.

**No incluye:**

Con el presente proyecto, dadas las características académicas del proyecto final y las limitaciones de tiempo y recursos, se delimitaron los principales aspectos que no se ejecutarán en esta parte del desarrollo, que quedarán definidos para ser mejoras futuras o extensiones del software:

* Integración a la pasarela de pago real: No se contempla conectar servicios de pago en línea (tarjetas de crédito/débito, mediante billeteras electrónicas, mediante APIs externas). En su lugar, se implementará una simulación lógica del pago mediante coordinación con el cliente que permitirá validar el flujo de compra sin efectuar transacciones reales, ni cumplir con criterios de seguridad, como PCI-DSS.  
*  Gestión avanzada de envíos y logística: El sistema no incluirá la integración con servicios de transporte , el cálculo automático de los costos de envío ni el seguimiento de pedidos en tiempo real . La gestión de entregas será teórica o manual.                     


# **4 TECNOLOGÍAS UTILIZADAS:** {#4-tecnologías-utilizadas:}

El desarrollo del lado del servidor se apoya fuertemente en el ecosistema Java, utilizando Spring Boot y Maven. Del lado de la persistencia de datos, la elección es MySQL. Para gestionar el despliegue sin problemas de compatibilidad entre entornos, toda la infraestructura correrá sobre Docker y Docker Compose. En cuanto a la nube, evaluamos subir los contenedores a plataformas ágiles como Railway o alternativamente utilizar recursos directos en Oracle Cloud.

# **5 ARQUITECTURA DEL PROYECTO:** {#5-arquitectura-del-proyecto:}

Descartamos el modelo monolítico tradicional para adoptar directamente una arquitectura orientada a microservicios. Trabajar bajo este esquema facilita mucho las cosas si a futuro una parte del sistema falla o necesita actualizarse sin apagar el resto de la aplicación. A nivel de patrones, la integración del *Service Discovery* junto con el patrón *API Gateway* resulta fundamental para orquestar correctamente la red interna.

# **6 DIAGRAMA DE ARQUITECTURA:** {#6-diagrama-de-arquitectura:}

El diagrama muestra la arquitectura basada en microservicios donde el cliente se comunica con el sistema a través del API Gateway. Los servicios se registran en Eureka Server y acceden a una base de datos MySQL compartida.

![][image1]

# **7 DESCRIPCIÓN DE API:** {#7-descripción-de-api:}

Toda petición externa impacta primero en el API Gateway, a partir de ahí el tráfico se divide. El módulo *User Service* se hace cargo de las credenciales y rutas de inicio de sesión emitiendo tokens de seguridad JWT, por otro lado, el *Product Service* recibe el grueso de las consultas de búsqueda y la gestión del catálogo. El *Order Service* de forma separada procesa exclusivamente el flujo de las compras y el historial.

# **8 ESTRUCTURA DEL CÓDIGO:** {#8-estructura-del-código:}

El proyecto está organizado siguiendo una arquitectura de microservicios, donde cada componente del sistema se desarrolla y despliega de forma independiente.

**Link Repositorio Github:**

[https://github.com/fhuamanr/olx-certus-servicios](https://github.com/fhuamanr/olx-certus-servicios)

Cada microservicio cuenta con su propia estructura interna basada en Spring Boot, separando responsabilidades en capas como:

* **Controller:** El responsable de gestionar las solicitudes HTTP que llegan (REST API), de comprobar datos de entrada y de obtener la respuesta con el cliente.  
* **Service:** Lógica de negocio , contiene la lógica de negocio del sistema precisamente la que procesa las reglas, las validaciones, las operaciones principales (dentro de procesos de implicación con la capa de datos)  
* **Repository:** El responsable del acceso a la base de datos (curiosamente presentado como una capa de acceso a los datos mediante abstracciones como JPA, etc. para realizar CRUD operaciones).  
* **Model:** Entidades del sistema y la representación de ellas en la base de datos.

Los principales módulos del sistema son:

* **User Service:** Gestiona usuarios, autenticación y roles.  
  **Responsabilidades**:  
  \- Registro de los nuevos usuarios.  
  \- Autenticación (login).  
  \- Gestión de los perfiles.  
  \- Gestión de los roles base (comprador / vendedor).  
  **Funciones clave:**  
  \- Validación de credenciales.  
  \- Persistencia de los datos del usuario.  
  \- Control de acceso por rol.  
* **Product Service:** Administra productos y catálogo.  
  **Responsabilidades:**  
- Creación, edición o eliminación de productos (CRUD).  
- Consulta y filtrado de productos.  
  **Funciones:**  
- Búsqueda por categoría, precio o palabras clave.  
- Asociación de productos a vendedores.  
- Gestión opcional de imágenes mediante servicios externos como almacenamiento en la nube.  
* **Order Service:** Maneja pedidos y transacciones.  
  **Responsabilidades:**  
- Realización de pedidos.  
- Consultar el historial de compra.  
- Relación entre los usuarios y los productos adquiridos.  
  **Funciones:**  
- Anotar la transacción (simulando la transacción).  
- Consulta de pedidos según usuario.  
- Control de pedidos (estado del pedido, por ejemplo, pendiente, completado).  
* **API Gateway:** Punto de entrada único para todas las solicitudes.  
  **Funciones esenciales:**   
- Enrutamiento o ruteo dinámico basándose en los servicios que se conocen y se han registrado en Eureka  
- Centralización de endpoints para que el cliente no tenga que llamar el endpoint de una dirección IP concreta, es decir, para que el cliente no tenga que hacer con un host y un puerto fijos las llamadas a los servicios.  
  **Posible implementación de:**  
  \- Seguridad mediante JWT (Authentication and Authorization),   
  \- Control de tráfico (Frequency Limiting),   
  \- Filtros logging y monitoring.  
* **Eureka Server:** Registro y descubrimiento de servicios.  
  **Responsabilidad principal:**   
  • Permitir la comunicación entre servicios y permitir que la comunicación entre servicios no haga uso de una dirección IP fija.  
  **Funciones esenciales:**   
-  Registro de servicios activos y registro dinámico de los servicios activos;   
- Descubrimiento de instancias disponibles de forma automática;   
- Balanceo básico mediante la elección de una instancia registrada.  
  Esto hace posible una arquitectura más flexible y más tolerante a fallos.

# **9 BASE DE DATOS:** {#9-base-de-datos:}

Optamos por MySQL debido a su comprobada agilidad al trabajar con contenedores. Sabemos que la teoría pura de microservicios exige una base de datos independiente por cada módulo sin embargo para mantener el enfoque académico del curso sin complicar en exceso la infraestructura local usaremos una instancia compartida. La separación lógica por tablas será estricta, se cuidará la integridad referencial y se crearán índices en las columnas de mayor búsqueda del catálogo para evitar problemas de rendimiento.

# **10 CONCLUSIONES:** {#10-conclusiones:}

* Diseñar el marketplace fragmentando sus funciones nos entrega un sistema robusto y preparado para crecer. Aunque la curva de aprendizaje y configuración inicial es mayor por la necesidad de implementar enrutadores y registros de servicio el resultado final es un entorno de desarrollo ordenado que minimiza los puntos únicos de fallo.
