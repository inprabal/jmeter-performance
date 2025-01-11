FROM maven:3.9.9-eclipse-temurin-17-focal

# Set build arguments and environment variables
ARG MAVEN_USER
ARG MAVEN_PASSWORD
ARG GIT_API_TOKEN
# Define a build argument for the repository URL
ARG REPO_URL

ENV MAVEN_USER=${MAVEN_USER}
ENV MAVEN_PASSWORD=${MAVEN_PASSWORD}
ENV JMETER_VERSION=5.6.3
ENV JMETER_HOME=/opt/apache-jmeter-${JMETER_VERSION}
ENV PATH=$JMETER_HOME/bin:$PATH

# Set the JMeter heap size
ENV HEAP="-Xms2g -Xmx6g"

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

# Clone the repository using API token
#RUN git clone https://${GIT_API_TOKEN}@${REPO_URL} .

# Copy project files
#COPY settings.xml /root/.m2/settings.xml
COPY . /app

# Build project and prepare JMeter
RUN mvn clean test-compile -s /app/settings.xml -Pjmeter-ext && \
    cp -r /app/target/jmeter-ext/*.jar ${JMETER_HOME}/lib/ext/ && \
    cp /app/src/test/conf/rmi_keystore.jks ${JMETER_HOME}/bin/ && \
    cp /app/src/test/resources/user.properties ${JMETER_HOME}/bin/

# Set working directory for JMeter
WORKDIR $JMETER_HOME/bin

# Ports to be exposed
EXPOSE 1099 50000

# Entry point to start JMeter server
ENTRYPOINT ["jmeter-server", "-Dserver.rmi.localport=50000", "-Dserver_port=1099"]
