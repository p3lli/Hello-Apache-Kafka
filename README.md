# Hello Apache Kafka - my first attempt

This project contains my first attempt to use Apache Kafka framework.  
I am currently using Kafka version 0.11.0.0.  

## What the code does
Starting from the demo from [MapR github](https://github.com/mapr-demos/kafka-sample-programs), the project simply defines a custom  
Producer and a custom Consumer.  
* Producer class creates a message every second
* Consumer class reads the message  
* Utils class sets up the possible topics and messages that can be produced  
* Run class execute both producers and consumers  

Because I was listening to the radio while coding, there are two main topics:  
* seventy  
* eighty  

Messages are read by producers from a CSV file.
Currently, every message is an insert of music track title.    
This kind of producer has been created based on the example of [jabbaugh github](https://github.com/jabbaugh/kafka-producer).  

The idea is to get consumers to write something in a database in future.  

## Getting started
Following [Cloudurable tutorials](http://cloudurable.com/blog/kafka-tutorial/index.html), I have created a series of scripts in bash  
to start the project. I am not a guru bash programmer, so  
please have a look to the scripts before running them.  

First of all, run  
```
mvn package
```
to create the target directory which will contains the executable packages.  

From the scripts directory, run these commands in different terminals:
```
./get-kafka.sh
./run-zookeeper.sh
./run-kafka.sh
./create-topic.sh seventy
./create-topic.sh eighty
./start-producer.sh /absolute/path/to/dataset.csv
./start-consumer.sh
```
