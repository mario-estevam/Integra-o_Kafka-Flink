package mario.br.integration;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

public class Receptor_e_Remetente {

    public static void main(String[] args) throws Exception {

        String server = "localhost:9092";
        String inputTopic = "test1";
        String outputTopic = "test2";
        StramStringOperation(server,inputTopic,outputTopic );

    }

    public static class StringCapitalizer implements MapFunction<String, String> {
        @Override
        public String map(String data) throws Exception {
            return data.toUpperCase();
        }
    }

    public static void StramStringOperation(String server,String inputTopic, String outputTopic ) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        FlinkKafkaConsumer<String> flinkKafkaConsumer = createStringConsumerForTopic(inputTopic, server);
        FlinkKafkaProducer<String> flinkKafkaProducer = createStringProducer(outputTopic, server);
        DataStream<String> stringInputStream = environment.addSource(flinkKafkaConsumer);

        stringInputStream.map(new StringCapitalizer()).addSink(flinkKafkaProducer);

        environment.execute();
    }

    public static FlinkKafkaConsumer<String> createStringConsumerForTopic(
            String topic, String kafkaAddress) {

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", kafkaAddress);
        //props.setProperty("group.id",kafkaGroup);
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                topic, new SimpleStringSchema(), props);

        return consumer;
    }

    public static FlinkKafkaProducer<String> createStringProducer(
            String topic, String kafkaAddress){

        return new FlinkKafkaProducer<>(kafkaAddress,
                topic, new SimpleStringSchema());
    }

}
