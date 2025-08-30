package com.w0n.payments_with_kafka.controller;

import com.w0n.payments_with_kafka.entity.PaymentEvent;
import com.w0n.payments_with_kafka.service.PaymentProducer;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/payments")
public class PaymentEventController {
    private final PaymentProducer producer;

    public PaymentEventController(PaymentProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public PaymentEvent generate(@RequestBody PaymentEvent payment) {
       payment.setPaymentId(UUID.randomUUID().toString());
       payment.setPaymentDate(OffsetDateTime.now(ZoneOffset.UTC).toLocalDateTime());

       producer.publish(payment);

       return payment;
    }
}
