# kafka-tutorial
Kafka Tutorial From Udemy

https://www.udemy.com/apache-kafka/learn/v4/content

# Instructions
First run kafka-start.bat to run zookeeper and kafka server

Then run chrome-start.bat to launch twitter and bonsai hosted elastic search

Run create-topic.bat to create the twitter_tweets topic if you havent already done so

Edit TwitterProducer.java to include consumerKey, consumerSecret, token and secret from your twitter developer account

Edit ElasticSearchConsumer.java to include the hostName, userName and password to you elastic search endpoint

In bonsai create a twitter index: PUT /twitter

Run TwitterProducer.java and TwitterConsumer.java.

When you send a tweet with a term you are following the producer will send it to kafka. Then the consumer will read it from kafka and send it to elastic search to the index "twitter" and type "tweets" and give you and id in the java console.  

Use the id to search for the tweet in elastic search using:

GET /twitter/tweets/utTFEmYBCvQCJkn9X4Yl

