Este projecto consite en desarrollar, utilizando Spring Boot 2 y Java 11, una API que permita hacer un mantenimiento de súper héroes.

1.-Para ejecutar el programa ejecute el comando.
mvnw spring-boot:run

2.-Uso de la api.
La api es accesible desde la pagina de login
http://localhost:8080/login

Hay dos usuarios disponibles
Username: user Password: password -> Tiene accessible todos los metodos menos /heroes/all
Username: auditor Password: password -> Tiene accesible toda la api.

Para cambiar de usuario es necesario cerrar session desde esta url.
http://localhost:8080/logout

3.- Despliegue con Docker.
Crear el fichero jar necesario
mvnw package

El Dockerfile se encuantra en la raiz del proyecto.

Creación de la imagen
docker build -t assignmentimage .

Ejecucion de la imagen
docker run -p 8080:8080 assignmentimage
 