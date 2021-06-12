FROM openjdk:8-alpine
COPY out/artifacts/stockquotemanager/*.jar /usr/local/bin
WORKDIR /usr/local/bin
EXPOSE 8081
CMD ["sh", "-c", "ls -l && echo 1 && echo 2 && java -jar stockquotemanager.jar"]
