# Use a slimmer base image
FROM maven:3.9.9-eclipse-temurin-17-focal-slim

# Set build arguments and environment variables
ARG MAVEN_USER
ARG MAVEN_PASSWORD
ENV MAVEN_USER=${MAVEN_USER}
ENV MAVEN_PASSWORD=${MAVEN_PASSWORD}
ENV JMETER_VERSION=5.6.3
ENV JMETER_HOME=/opt/apache-jmeter-${JMETER_VERSION}
ENV PATH=$JMETER_HOME/bin:$PATH

# Install tools and JMeter
RUN apt-get update && apt-get install -y --no-install-recommends \
    bash \
    curl \
    tar \
    && curl -L https://dlcdn.apache.org/jmeter/binaries/apache-jmeter-${JMETER_VERSION}.tgz \
    | tar -xz -C /opt \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

# Copy project files
COPY settings.xml /root/.m2/settings.xml
COPY . /app

# Build project and prepare JMeter
RUN mvn clean test-compile -s /root/.m2/settings.xml -Pjmeter-ext && \
    cp -r /app/target/jmeter-ext/*.jar ${JMETER_HOME}/lib/ext/ && \
    cp /app/src/test/conf/rmi_keystore.jks ${JMETER_HOME}/bin/

# Set working directory for JMeter
WORKDIR $JMETER_HOME/bin

# Ports to be exposed
EXPOSE 1099 50000

# Entry point to start JMeter server
ENTRYPOINT ["jmeter-server", "-Dserver.rmi.localport=50000", "-Dserver_port=1099"]
