FROM openjdk:11
VOLUME /tmp
COPY build/libs/*.jar news_feed.jar
ENTRYPOINT ["java","-jar","/news_feed.jar"]