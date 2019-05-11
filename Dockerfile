FROM java:8
MAINTAINER bupt.coder
ADD build/libs/course-microservice-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# 使用 --spring.profiles.active=create 删除数据库数据并重新注入数据
# 使用 --spring.profiles.active=update 数据库使用更新模式