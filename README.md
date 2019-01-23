Proyecto_Scrum

Sprint 1

Dise�ar una Base de Datos para gestionar: Usuarios, grupos de usuarios, proyectos, especificaciones, sprints. (5 horas)
Hay que utilizar JPA/Hibernate para el acceso a la base de datos remota. (3 horas)
Los usuarios pueden ser de tipo: Developer (solo podr�n visualizar los datos), Product Owner (puede visualizar los datos y a�adir/modificar/eliminar especificaciones), Scrum Master (puede visualizar los datos, crear proyectos, crear sprints, temporizar especificaciones y sprints y asignar especificaciones a sprints) y Administrador de usuarios (puede crear usuarios). (1 hora)
Cuando se inicie la aplicaci�n ha de intentar de conectar a la Base de Datos remota e indicar� la conexi�n con (ONLINE). (1 hora)
Todas las ventanas internas (JInternalFrames) se podr�n cerrar y redimensionar. (2 horas)
Crear un JInternalFrames de Login. Aparece al ejecutar la aplicaci�n. (1 hora)
Al pulsar ENTER ha de intentar validar el usuario. (1 hora)
Ha de tener un JInternalFrame para inscribir usuarios al programa. (4 horas)

Indicaciones

-Importar en BD MySQL el archivo que se encuentra dentro de main/resources/scrumprojectmanager.sql para hacer un volcado de informaci�n.

-Ejecutar el programa en la clase "GestorDeProyectos".

IMPORTANTE para que funcione correctamente la conexi�n a la BD Remota, comprueba dentro del archivo persistence.xml, dentro de main/resources/META-INF, en la etiqueta < property name="javax.persistence.jdbc.url" value="..." >, la informaci�n sea correcta.

Lista de los usuarios:

Usuario: pachon
Contrase�a: 123123
Tipo de Usuario: null