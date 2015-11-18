# Proyecto Demo para TIW

Proyecto de demostracion para la asignatura Tecnologias de Internet para la Web.
El proyecto esta basado en el arquetipo maven *es.uc3m.tiw:TIW-Archetype*
 
## Pasos necesarios

1. Crear el proyecto con el arquetipo
2. En el primer sprint1 comentar los proyectos *ejb* y *util* del fichero **pom.xml** del proyecto <proyecto>-ear 
3. Tras tocar el fichero _pom.xml_ eclipse mostrara un error en el proyecto que se soluciona de la siguiente manera:

    Boton derecho -> maven -> update project

4. Trabajar normalmente sobre el *<proyecto>-web*
5. Ejecutar Run as -> maven build ... 
6. introducir en **Goals** clean package 

## ACTUALIZAR LA VERSION DEL PROYECTO ##

Para cambiar la version del proyecto y actualizar por ejemplo de la 1.0 a la 2.0 hay que ejecutar el siguiente comando desde el proyecto padre

		mvn versions:set -DnewVersion=2.0
		mvn versions:commit
		
Si hubiera algun error, se puede revertir a la version anterior con el siguiente comando:

		mvn versions:revert



