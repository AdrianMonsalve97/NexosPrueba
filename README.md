Nexos prueba
Este es un proyecto de prueba desarrollado con Spring Boot que realiza transacciones y administración de tarjetas.

Requisitos previos
Antes de ejecutar este proyecto, asegúrate de tener instalado lo siguiente:

Java 17
MySQL
Configuración de la base de datos
Crea una base de datos MySQL con el nombre nexosprueba.
Actualiza las propiedades de conexión a la base de datos en el archivo application.properties:
properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/nexosprueba
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create
Asegúrate de establecer el nombre de usuario y la contraseña adecuados para tu entorno.

Ejecución del proyecto
Clona este repositorio en tu máquina local.
Abre el proyecto en tu IDE preferido.
Ejecuta la clase principal Application.java para iniciar la aplicación.
El proyecto se ejecutará en http://localhost:8080.

Documentación de la API
Este proyecto utiliza Swagger para generar la documentación de la API. Puedes acceder a la documentación en http://localhost:8080/swagger-ui.html una vez que el proyecto esté en ejecución.

Contacto
Si tienes alguna pregunta o comentario sobre este proyecto, no dudes en contactarme a través de mi correo electrónico: 97.amonsalve@gmail.com

¡Gracias por revisar este proyecto de prueba!

Recuerda personalizar la información según tu proyecto y agregar cualquier otra sección relevante que consideres necesario. Espero que esto te ayude a crear una documentación clara y útil para tu proyecto.

Si tienes alguna otra pregunta, no dudes en hacerla. ¡Buena suerte!
