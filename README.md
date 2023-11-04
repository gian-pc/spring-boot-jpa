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