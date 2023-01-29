package com.gucardev.mqqtpoc.controller;

import com.gucardev.mqqtpoc.model.ResponseData;
import com.gucardev.mqqtpoc.service.StateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class MqqtSSEController {

  private final StateService stateService;

  @GetMapping("/{clientId}")
  public Flux<ServerSentEvent<List<ResponseData>>> streamLastMessage(
      @PathVariable String clientId) {
    return stateService.getById(clientId);
  }

}
