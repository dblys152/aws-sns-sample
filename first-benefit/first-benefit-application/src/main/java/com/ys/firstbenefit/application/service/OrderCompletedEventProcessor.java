package com.ys.firstbenefit.application.service;

import com.ys.firstbenefit.application.event.OrderCompletedEvent;
import com.ys.firstbenefit.application.port.in.CreateFirstBenefitUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
public class OrderCompletedEventProcessor implements Consumer<OrderCompletedEvent> {

    private final CreateFirstBenefitByOrderCommandFactory commandFactory;
    private final CreateFirstBenefitUseCase createFirstBenefitUseCase;

    @Override
    public void accept(OrderCompletedEvent event) {
        String orderId = event.getOrderId();
        createFirstBenefitUseCase.create(commandFactory.create(orderId));
    }
}
