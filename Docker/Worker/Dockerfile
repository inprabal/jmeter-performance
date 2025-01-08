# Start from the specified base image
FROM maven:3.9.9-eclipse-temurin-17-focal

# Set build arguments and environment variables for Maven credentials
ARG MAVEN_USER
ARG MAVEN_PASSWORD
ENV MAVEN_USER=${MAVEN_USER}
ENV MAVEN_PASSWORD=${MAVEN_PASSWORD}

# Set environment variables for JMeter installation
ENV JMETER_VERSION=5.6.3 \
    JMETER_HOME=/opt/apache-jmeter-${JMETER_VERSION} \
    PATH=$JMETER_HOME/bin:$PATH

# Install necessary tools, Maven, and download JMeter
RUN apt-get update && apt-get install -y \
    bash \
    curl \
    tar \
    && curl -L https://dlcdn.apache.org//jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz \
    | tar -xz -C /opt

# Set working directory in the container
WORKDIR /app

# Copy the Maven settings.xml with repository credentials
COPY settings.xml /root/.m2/settings.xml

# Copy Maven project into the container
COPY . /app

# Build the project with the specified profile
RUN mvn clean test-compile -s /root/.m2/settings.xml -Pjmeter-ext

# Copy the generated JARs to the JMeter lib/ext directory
RUN cp -r /app/target/jmeter-ext/*.jar ${JMETER_HOME}/lib/ext/

# Copy RMI keystore file to JMeter bin directory
RUN cp /app/src/test/conf/rmi_keystore.jks ${JMETER_HOME}/bin/

# Ports to be exposed from the container for JMeter Slaves/Server
EXPOSE 1099 50000

# Application to run on starting the container
ENTRYPOINT ["jmeter-server", \
            "-Dserver.rmi.localport=50000", \
            "-Dserver_port=1099"]
