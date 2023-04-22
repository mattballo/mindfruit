FROM maven:3.8.3-openjdk-17

WORKDIR /mindfruit
COPY . .
RUN mvn clean install spring-boot:repackage -e -DskipTests

CMD mvn spring-boot:run -e -DskipTests