                                        Proyecto 4
                                           (JPA)
1. Inicio del proyecto **spring-boot-jpa**
   - Link:https://www.youtube.com/watch?v=QZM8vpXAIZs&list=PLefvs4KMhNlvBNglolyALOQh1lNWR49eW&index=13
   - Dependencias:
     - Spring Web
     - Thymeleaf
     - Validation
     - Spring Boot Dev Tools
     - H2 Database
     - MySQL Driver
     - Spring Data JPA
2. Creación de modelos
   - Creamos el package **models.entity**
     - Creamos la clase **Cliente** con sus respectivas anotaciones(@Entity, @Table, @Id)
     - En **application.properties** deshabilitamos la vista JPA y habilitamos la consola de H2
     - En esta ruta: http://localhost:8080/h2-console/ agregamos el código de H2 que aparece al reiniciar la aplicación
     - Esta nos permitirá ver la tabla![img.png](src%2Fmain%2Fresources%2Fstatic%2Fimg.png)
3. DAO
   - Creamos un nuevo package **models.dao**
     - Creamos la interface **ICliente**
       - Agregamos los métodos a implementar
     - Creamos la clase **ClienteDAO** que implementa los métodos de la interface **ICliente**
     - **EntityManager** trae todos los métodos para listar, guardar, etc.
     - Desarrollamos **listarTodo** @Transactional(readOnly=true:) nos dice que nos vamos a conectar a la DB para hacer algo de solo lectura
4. Controlador
   - Creamos un controlador para controlar las acciones
   - Creamos el package controllers
     - Creamos ClienteController
     - Agregamos la anotación @Controller
     - Inyectamos la Interface **ICliente**
     - Convertimos en un @Repository **ClienteDAO**
   - Creamos una Vista
     - cliente/listar.html
   - Datos de prueba, creamos un archivo **import.sql**
   - Los datos de prueba se crearon porque no habíamos agregado la anotación **@PersistenceContext** por lo cual no habíamos logrado la conexión a la base de datos
   - !Deshabilitar nombre aleatorio BD en **application.properties**
     - spring.datasource.generate-unique-name=false
   - !Habilitar visualización de sentencias SQL en **application.properties**
     - spring.jpa.show-sql=true
   