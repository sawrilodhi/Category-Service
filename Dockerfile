FROM openjdk:8
EXPOSE 9096
ADD target/category-service-0.0.1.jar category-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/category-service-0.0.1.jar"] 