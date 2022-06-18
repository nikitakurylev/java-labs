package itmo.tech.main.controller;
import itmo.tech.main.entity.CatDTO;
import itmo.tech.main.entity.RequestInfo;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class CatController {
    @Value("${kafka.request.topic}")
    private String requestTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, RequestInfo, CatDTO> replyingKafkaTemplate;

    @Autowired
    private ReplyingKafkaTemplate<String, RequestInfo, List<CatDTO> > replyingKafkaTemplateList;

    @GetMapping("/cat/{id}")
    public ResponseEntity<CatDTO> getCat(@PathVariable String id) throws InterruptedException, ExecutionException{
        RequestInfo catRequest = new RequestInfo("FindById", Integer.parseInt(id));
        ProducerRecord<String, RequestInfo> record = new ProducerRecord<>(requestTopic, catRequest);
        RequestReplyFuture<String, RequestInfo, CatDTO> future = replyingKafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, CatDTO> response = future.get();
        return new ResponseEntity<>(response.value(), HttpStatus.OK);
    }

    @GetMapping("/cats")
    public ResponseEntity<List<CatDTO>> getCats() throws InterruptedException, ExecutionException {
        RequestInfo catRequest = new RequestInfo("FindAll");
        ProducerRecord<String, RequestInfo> record = new ProducerRecord<>(requestTopic, catRequest);
        RequestReplyFuture<String, RequestInfo, List<CatDTO>> future = replyingKafkaTemplateList.sendAndReceive(record);
        ConsumerRecord<String, List<CatDTO>> response = future.get();
        return new ResponseEntity<>(response.value(), HttpStatus.OK);
    }
}