package mario.br.integration;

import java.util.Properties;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;


public class Receptor {
	
	public static void main(String[] args) throws Exception {
		
	    String inputTopic = "test2";
	    String server = "localhost:9092";
	    
	    StramConsumrer(inputTopic, server);
	}
	
	public static void StramConsumrer(String inputTopic, String server) throws Exception {
	    StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
	    FlinkKafkaConsumer<String> flinkKafkaConsumer = createStringConsumerForTopic(inputTopic, server);
	    DataStream<String> stringInputStream = environment.addSource(flinkKafkaConsumer);

	   
	    stringInputStream.map(new MapFunction<String, String>() {
	        private static final long serialVersionUID = -999736771747691234L;
	   
	        @Override
	        public String map(String value) throws Exception {
	          return "Receiving from Kafka : " + value;
	        }
	      }).print();
	   
	    environment.execute();
	}
	
	public static FlinkKafkaConsumer<String> createStringConsumerForTopic(
			  String topic, String kafkaAddress) {
			 
			    Properties props = new Properties();
			    props.setProperty("bootstrap.servers", kafkaAddress);
			    //props.setProperty("group.id",kafkaGroup);
			    FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(topic, new SimpleStringSchema(), props);

			    return consumer;
	}

}