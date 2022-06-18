package itmo.tech.main.config;


import itmo.tech.main.entity.CatDTO;
import itmo.tech.main.entity.RequestInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {
    @Value("${kafka.group.id}")
    private String groupId;

    @Value("${kafka.reply.topic}")
    private String replyTopic;

    @Bean
    public <T,V> ReplyingKafkaTemplate<String, T, V> replyingKafkaTemplate(ProducerFactory<String, T> pf,
                                                                                    ConcurrentKafkaListenerContainerFactory<String, V> factory) {
        ConcurrentMessageListenerContainer<String, V> replyContainer = factory.createContainer(replyTopic);
        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
        replyContainer.getContainerProperties().setGroupId(groupId);
        return new ReplyingKafkaTemplate<>(pf, replyContainer);
    }

    @Bean
    public <T> KafkaTemplate<String, T> replyTemplate(ProducerFactory<String, T> pf,
                                                       ConcurrentKafkaListenerContainerFactory<String, T> factory) {
        KafkaTemplate<String, T> kafkaTemplate = new KafkaTemplate<>(pf);
        factory.getContainerProperties().setMissingTopicsFatal(false);
        factory.setReplyTemplate(kafkaTemplate);
        return kafkaTemplate;
    }

}
