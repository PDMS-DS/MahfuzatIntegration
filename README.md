before build project do the following
1- go to project path 
2- open cmd and set path to project path 
Note : project path name must have spacs
3- run the following
mvn install:install-file -Dfile=E:\ECM_MCN\SpringBootForArchiveSch\lib\jace.jar -DgroupId=com.ibm.filenet -DartifactId=jace -Dversion=1.0 -Dpackaging=jar -DgeneratePom=true
