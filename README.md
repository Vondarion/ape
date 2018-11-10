This project is a little ape that controls my Smart Home.
Switched to be stored at bitbucket.

## Table of Contents

- [Updating to New Releases](updating-to-new-releases)
-
## Updating to New Releases

## Know issues

Spring Boot and Java 11:
https://winterbe.com/posts/2018/08/29/migrate-maven-projects-to-java-11-jigsaw/
https://stackoverflow.com/questions/52185626/illegal-reflective-access-when-i-stop-springboot-web-application-with-tomcat-9-a?noredirect=1&lq=1

Add following arguments when launch the java application:
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED



