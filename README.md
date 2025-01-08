
#### Running the GUI
-----

You will be able to invoke the JMeter GUI using the following command:

```
mvn jmeter:configure jmeter:gui -Pperformance
```

If you want to preload a test, you can specify it on the command line:  

```
mvn jmeter:configure jmeter:gui -DguiTestFile=src/test/jmeter/Test.jmx -Pperformance
```

#### Run the tests

```
mvn clean verify -Pperformance
```

For remote execution    

```
mvn clean verify -Pperformance -Dremote=true   
(start the remote server using jmeter/bin>./jmeter-server -Djava.rmi.server.hostname=xxx.xxx.xxx.xxx
```

#### Docker Setup   

```
sudo apt-get update  
sudo apt-get install ca-certificates curl   
sudo install -m 0755 -d /etc/apt/keyrings   
sudo curl -fsSL https://download.docker.com/linux/ubuntu/gpg -o /etc/apt/keyrings/docker.asc   
sudo chmod a+r /etc/apt/keyrings/docker.asc   
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.asc] https://download.docker.com/linux/ubuntu $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null   
sudo apt-get update   
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin   


sudo docker run hello-world   

sudo docker build -f Docker/Worker/Dockerfile -t my-jmeter-server --build-arg MAVEN_USER=myusername --build-arg MAVEN_PASSWORD=mypassword .   

sudo docker run -it --name jmeter-server01 -p 1099:1099 -p 50000:50000 my-jmeter-server /bin/bash   
```