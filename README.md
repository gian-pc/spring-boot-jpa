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
5. Bootstrap y Vistas
   - Esta vez descargamos Boostrap y lo agregamos en listar.html
   - Agregamos una class de Boostrap
   - Agregamos formulario.html
   - Resultados![img_1.png](src%2Fmain%2Fresources%2Fstatic%2Fimg_1.png)![img_2.png](src%2Fmain%2Fresources%2Fstatic%2Fimg_2.png)
6. Mapear el Formulario
   - Mapeamos los inputs del formulario **th:field** y en **ClienteController** le enviamos un objeto de tipo Cliente
   - Implementamos el método guardar y le agregamos @Transactional
   - @PrePersist; esta anotación nos sirve para agregar una fecha de manera automática
   - Para evitar que se envíen campos vacíos agregamos **nullable = false** en Cliente y **@NotBlank**
   - Seguidamente validamos en ClienteController
   - Después de validar en ClienteController al querer pasar campos vacíos no nos debe permitir enviar y retorna a "cliente/formulario"
7. Mapeo de errores
   - En formulario.html mapearemos los errores campo por campo
   - Agregamos **messages.properties** para los mensajes personalizados
   - Adicional en el formulario agregamos unas clases de bootstrap
8. Editar un Cliente
   - Antes agregamos **bootstrap.bundle.js** de bootstrap para que se genere la animación del menú
   - Para editar un cliente, primero debemos obtener el cliente
9. Optimizando el punto 8
   - Reduciremos de 2 a 1 sólo método
   - Aplicaremos una condición para cuando se tenga o no un **id**
10. Guardar
    - Implementamos el método guardar en ClienteDAO
    - @SessionAttributes: Mapea una sessión a nivel global donde se almacena el objeto Cliente
    - Luego cierro la session después de guardar
11. Eliminar
    - Agregamos métodos para eliminar un cliente
12. Mejoras 
13. Conexión MySQL
14. Imágenes
    - Habilitamos el formulario.html con un atributo para que acepte datos de tipos archivos(imágenes) **enctype="multipart/form-data"**
    - Habilitamos un control(input) para poder subir los archivos
    - Configuramos el Controlador y le agregamos el atributo **@RequestParam MultipartFile file**
    - Agregamos un nuevo atributo a Cliente **foto**
    - Adicionamos **foto** en **import.sql**
15. Mostrar imágenes
    - Agregamos un método en el controller
    - Agregamos detalle.html
    - Agregamos un boton en listar para ver la imagen
    - Agregamos un valor por defecto a la imagen en **import.sql**
16. Desacoplar la carpeta uploads
    - Eliminamos la carpeta uploads
    - En el escritorio creamos una carpeta uploads
    - Eliminamos el path del ClienteController y agregamos la ruta de la carpeta uploads
    - Creamos **MvcConfig** para desacoplar la carpeta uploads
17. Otra Forma de desacople de uploads
    - Generamos la carpeta uploads a nivel del proyecto
    - Modificamos ClienteController
    - Modificamos MvcConfig