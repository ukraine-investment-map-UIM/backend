# backend

### Kafka

To run kafka with a single partition you need to create a special topic by typing:

```shell
docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --create --topic __consumer_offsets --partitions 50 --replication-factor 1
```