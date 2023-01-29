package com.gucardev.mqqtpoc.service;

import com.gucardev.mqqtpoc.model.ResponseData;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MessageService {


  public Flux<ServerSentEvent<List<ResponseData>>> getById(String id) {

    var data = List.of(new ResponseData("message" + Math.random()));

    return Flux.interval(Duration.ofSeconds(1))
        .map(sequence -> ServerSentEvent.<List<ResponseData>>builder()
            .id(String.valueOf(sequence)).event("user-list-event").data(data).build());
  }

}
