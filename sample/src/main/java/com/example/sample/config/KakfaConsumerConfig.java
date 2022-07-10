package com.example.sample.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.sample.bean.CourseDetailsMail;

@Configuration
public class KakfaConsumerConfig {
	
	
	//@Value("localhost:9092")
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;		//value to hold server value-9092
	
	
	
	   @Bean
	    public ConsumerFactory<String, CourseDetailsMail> consumerConfiguration() {
		   JsonDeserializer<CourseDetailsMail> deserializer = new JsonDeserializer<>(CourseDetailsMail.class);
	        deserializer.setRemoveTypeHeaders(false);
	        deserializer.addTrustedPackages("*");
	        deserializer.setUseTypeMapperForKey(true);
	        
	        Map<String, Object> configurations = new HashMap<>();
	        
	        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, "groupId");
	        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
	        return new DefaultKafkaConsumerFactory<>(configurations, new StringDeserializer(), deserializer);
	    }

	   
	   @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, CourseDetailsMail> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, CourseDetailsMail> factory = new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerConfiguration());
	        return factory;
	    }
}
