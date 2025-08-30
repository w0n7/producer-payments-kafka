package com.w0n.payments_with_kafka.service;

import com.w0n.payments_with_kafka.entity.PaymentEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private  final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String,PaymentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(PaymentEvent paymentEvent) {
        kafkaTemplate.send("payments-topic", paymentEvent );
        System.out.println("Send payment to kafka " + paymentEvent.getPaymentId());
    }
}
