
#### Running the GUI
-----

You will be able to invoke the JMeter GUI using the following command:

```
mvn jmeter:configure jmeter:gui -Pperformance
```

If you want to preload a test, you can specify it on the command line:  

```
mvn jmeter:configure jmeter:gui -DguiTestFile=src/test/jmeter/V5_DNS_Test.jmx -Pperformance
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

