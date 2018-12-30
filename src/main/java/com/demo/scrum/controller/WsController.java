package com.demo.scrum.controller;

import com.demo.scrum.dto.request.WsRequest;
import com.demo.scrum.dto.response.WsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public WsResponse greeting(WsRequest message) throws Exception {
        return new WsResponse("This is an action", "It works!");
    }

    @MessageMapping("/world")
    public void greeting2(WsRequest message) throws Exception {
        simpMessagingTemplate.convertAndSend("/aaaa", message);
    }

}