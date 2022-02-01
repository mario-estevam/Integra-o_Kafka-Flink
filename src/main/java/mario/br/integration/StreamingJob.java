/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mario.br.integration;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.time.Duration;
import java.util.Properties;

/**
 * Skeleton for a Flink Streaming Job.
 *
 * <p>For a tutorial how to write a Flink streaming application, check the
 * tutorials and examples on the <a href="https://flink.apache.org/docs/stable/">Flink Website</a>.
 *
 * <p>To package your application into a JAR file for execution, run
 * 'mvn clean package' on the command line.
 *
 * <p>If you change the name of the main class (with the public static void main(String[] args))
 * method, change the respective entry in the POM.xml file (simply search for 'mainClass').
 */
public class StreamingJob {

	public static void main(String[] args) throws Exception {
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

//		Properties properties = new Properties();
//		properties.setProperty("bootstrap.servers", "localhost:9092");
//		properties.setProperty("group.id", "test");
//		DataStream<String> stream = env
//			.addSource(new FlinkKafkaConsumer<>("test2", new SimpleStringSchema(), properties));
//		
//		KafkaSource<String> source = KafkaSource.<String>builder()
//			    .setBootstrapServers(brokers)
//			    .setTopics("test2")
//			    .setGroupId("my-group")
//			    .setStartingOffsets(OffsetsInitializer.earliest())
//			    .setValueOnlyDeserializer(new SimpleStringSchema())
//			    .build();
//
//			env.fromSource(source, WatermarkStrategy.noWatermarks(), "Kafka Source");
			
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("group.id", "test");

		FlinkKafkaConsumer<String> myConsumer =
		    new FlinkKafkaConsumer<>("topic", new SimpleStringSchema(), properties);
		myConsumer.assignTimestampsAndWatermarks(
		    WatermarkStrategy
		        .forBoundedOutOfOrderness(Duration.ofSeconds(20)));

		DataStream<String> stream = env.addSource(myConsumer);


		// execute program
		env.execute("Flink Streaming Java API Skeleton");
	}
}
