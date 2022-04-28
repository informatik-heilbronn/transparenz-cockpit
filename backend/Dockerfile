FROM amazoncorretto:11

WORKDIR /labsw/

COPY ./build/libs/LabSW.Backend.jar /labsw/LabSW.Backend.jar

VOLUME /labsw/transparentcockpit.config

EXPOSE 80

ENTRYPOINT ["java", "-jar", "LabSW.Backend.jar"]