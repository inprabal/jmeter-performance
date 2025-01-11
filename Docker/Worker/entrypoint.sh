#!/bin/bash

# Check if HOST_IP is set, otherwise exit with an error
if [ -z "$HOST_IP" ]; then
    echo "Error: HOST_IP environment variable is not set. Pass it when running the container."
    exit 1
fi

echo "Using HOST_IP: $HOST_IP"

# Start the JMeter server with HOST_IP as the RMI hostname
exec jmeter-server \
    -Dserver.rmi.localport=50000 \
    -Dserver_port=1099 \
    -Djava.rmi.server.hostname=$HOST_IP
