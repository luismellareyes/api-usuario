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
#### IDE
Ejecutar Run desde el IDE 

#### Ejecutar jar

Se debe ejecutar en la cola el siguiente comando:
Ejecutar Comando en el directorio de source
```
mvn clean install

java -jar ***/.m2/repository/cl/mella/api-usuarioToken/0.0.1-SNAPSHOT/api-usuarioToken-0.0.1-SNAPSHOT.jar
```


