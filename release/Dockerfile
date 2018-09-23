FROM java:8

# JSTに変更
RUN echo "Asia/Tokyo" > /etc/timezone && dpkg-reconfigure -f noninteractive tzdata

ADD restaurant-backoffice-0.0.1-SNAPSHOT.jar /opt/
EXPOSE 8080
WORKDIR /opt/
CMD ["java", "-Xms256m", "-Xmx512m", "-jar", "restaurant-backoffice-0.0.1-SNAPSHOT.jar"]