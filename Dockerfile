FROM openjdk:8
ADD build/libs/*.jar /
ADD version.txt /version.txt
ADD build.gradle /build.gradle
EXPOSE 8090
ENTRYPOINT [ "java", "-jar", "/springbootcrudexample*.jar"]