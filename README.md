## Api-usuario
Api Rest de creación de usuarios, Luis Mella

## Descripción de aplicación Api Rest para la creacion de usuarios.

Esta aplicación expone un Api Rest con Endpoint de Autenticacion, registro y obtencion de listado de usuarios. Todos los Endpoint acepta Json y retornan todos lus respuestas en el mismo formato.

## Requisitos previos

El dispositivos debe contar:
- Gestor de Base de datos Postgresql, en el caso de no constar con esta seguir los pasos de instalación. [Instalacion Postgresql](https://www.postgresql.org/download/ "Descargar Postgresql")
- Java 8 o superior
Si es necesario instalar java en el equipos, ejecutar este comando en la consola:

```
sudo apt-get install openjdk-8-jdk
```

## Descargar aplicación del repositorio

Para descargar la aplicacion de debe realizar una clonacion del repositorio, abrir una consola de comandos, ubicarse en el directorio que se desee dejar la aplicacion y ejecutar el comando:

```
git clone https://github.com/luismellareyes/api-usuario.git
```
e ingresar al directorio :

```
cd ./api-usuario/
```
## Actualizacion de dependencias

Es necesario que previamente se actualicen y/o descarguen las dependecias utilizadas por el API Rest. Favor, ejecutar el siguiente comando en el directorio:

```
mvn compile -U
```

## Configurar Banco de datos Postgresql

La configuración de postgres se puede realizar de dos maneras, Modificando el resources application.properties o modificando credeciales de postgres.
### Configuracion de application.properties

Se debe configurar la Base de datos(BD) de postgres y sus credenciales, se puede utilizar la Base de datos predeterminado o crear una nueva. En el documento de propiedades(application.properties) de la aplicacion se debe ingresar la ip o dns del servidor de BD, usuarios y contraseña.

```java
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=password
```
### Configuracion de postgresql
En el caso de mantener la misma configuracion predeterminada, es necesario configurar la Base de datos, para eso es necesario ejecutar los siguientes comandos.

```
sudo -u postgres psql 
```
Dentro de la consola de postgres, se debe cambiar la contraseña, colocando la contraseña "password"
```
postgres=# alter user postgres with password 'password';
```

## Ejecutar 

Para ejecutar la aplicacion se puede utilizar un IDE, ejecutar desde el mismo directorio o generar el .jar

#### Ejecucion desde directorio

Ejecutar Comando en el directorio de source
```
mvn spring-boot:run
```
####  IDE
Ejecutar Run desde el IDE 

#### Ejecutar jar

Se debe ejecutar en la cola el siguiente comando:
Ejecutar Comando en el directorio de source

```
mvn clean install

java -jar ***/.m2/repository/cl/mella/api-usuarioToken/0.0.1-SNAPSHOT/api-usuarioToken-0.0.1-SNAPSHOT.jar
```

# Pruebas de Endpoint

A continuacion se entregaran ejemplos del manejos de los Endpoint:

## Autenticacion

Los endpoint de autenticación, cuenta con dos metodos: 
- Para registrar usuario de administracion y usuarios, ingresar a los servicios.
- Login y generacion de token Bearer.

### Registrar nuevo usuario
Este endpoint registra a una usuario como administrador o usuario, con el cual se realizara la peticion de login posteriormente.
```
Endpoint: localhost:8080/auth/nuevo
Method: POST
Headers: Content-Type:application/json
Body:
{
"nombre":"LuisMella",
"nombreUsuario":"LuisAdmin",
"email": "luismellareyes@gmail.com",
"password": "Ha123",
"rol":"admin"
}

Response:
{
"mensaje": "usuario guardado"
}
```
### Login

Este endpoint realiza login en el servicio,ingresando nombreUsuario y contraseña, devolviendo una peticion de token de autenticacion que sera utilizado en los siguientes Endpoint.
```
Endpoint: localhost:8080/auth/login
Method: POST
Headers: Content-Type:application/json
Body:
{   "nombreUsuario":"LuisAdmin",
    "password": "Ha123"}

Response:
{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjEiLCJpYXQiOjE2MTM5NzQ0MjAsImV4cCI6MTYxNDAxMDQyMH0.zlxx7RwgEYeLFPdCKAn-zLPOsKKvAwnu4p3sNgJRS-o",
"bearer": "Bearer",
"nombreUsuario": "LuisAdmin",
"authorities": [
{   "authority": "ROLE_USER"
     },
     {
     "authority": "ROLE_ADMIN"
     }
]
}
```

### Registro

Este endpoint permite registrar usuarios con el rol de user, este servicio solo puede ser ejecutado por los usuarios con rol Admin.
```
Endpoint: localhost:8080/usuario/registro
Method: POST
Headers: Content-Type:application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkx1aXMxIiwiaWF0IjoxNjEzOTc2NDM3LCJleHAiOjE2MTQwMTI0Mzd9.X0YLY7ci-JvB_jdJqmCXz-OLtM2h3nr88ufli57aGaM
Body:
{
    "email": "admin11@gmail.com",
    "password": "1Ha3",
    "phones": [
        {
            "number": "12345678",
            "cityCode": "1",
            "contryCode": "2"
        },
        {
            "number": "87654321",
            "cityCode": "3",
            "contryCode": "4"
        }
    ]
}
    
Response:
{
    "id": "454414ab-72ee-40ae-a8b6-4aa56f32d639",
    "created": "2021-02-22T06:47:46.148+00:00",
    "modified": "2021-02-22T06:47:46.148+00:00",
    "last_login": "2021-02-22T06:47:46.148+00:00",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbjExIiwiaWF0IjoxNjEzOTc2NDY2LCJleHAiOjE2MTQwMTI0NjZ9.d0d3qguz-xXiIkPEldMQd9-B3gyh5BKRZWkyZZFqknY",
    "isActive": true
}


```




### Lista de usuarios

Este endpoint retorna el listado de usuarios que estan registrados, este servicio es posible ejecutar por usuarios Admin y User.

```
Endpoint: localhost:8080/usuario/lista
Method: GET
Headers: Content-Type:application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkx1aXMxIiwiaWF0IjoxNjEzOTc2NDM3LCJleHAiOjE2MTQwMTI0Mzd9.X0YLY7ci-JvB_jdJqmCXz-OLtM2h3nr88ufli57aGaM
    
Response:
{
    "Usuarios": [
        "admin1@gmail.com",
        "admin11@gmail.com"
    ]
}
```
