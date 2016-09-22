package org.librucha.spring.test.websocket.service;

import org.apache.commons.beanutils.LazyDynaMap;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessagingService {

    private static Logger log = LoggerFactory.getLogger(MessagingService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private static String[] USERS = new String[]{"admin", "user", "analyst", "viewer"};

    @Scheduled(fixedRate = 1000)
    public void generateMessage() {
        String user = USERS[RandomUtils.nextInt(0, 4)];
        log.info("Sending message to {}", user);
        LazyDynaMap dynaBean = new LazyDynaMap();
        dynaBean.set("receiver", user);
        dynaBean.set("count", RandomUtils.nextInt(5, 1000));
        dynaBean.set("time", System.currentTimeMillis());
        messagingTemplate.convertAndSendToUser(user, "/updates", dynaBean.getMap());
    }
}
