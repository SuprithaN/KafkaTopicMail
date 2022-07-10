package com.example.sample.config;

import java.util.HashMap;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.example.sample.bean.CourseDetailsMail;

@Configuration
public class KafkaProducerConfig {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;		//value to hold server value-9092
	
	
	
	   @Bean
	    public ProducerFactory<String, CourseDetailsMail> producerConfiguration() {
	        HashMap<String, Object> configurations = new HashMap<>();
	        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
	        return new DefaultKafkaProducerFactory<>(configurations);
	    }
	   
	   
	    @Bean
	    public KafkaTemplate<String, CourseDetailsMail> kafkaTemplate() {
	        return new KafkaTemplate<>(producerConfiguration());
	    }

}
