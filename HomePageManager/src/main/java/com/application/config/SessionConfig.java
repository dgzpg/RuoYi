package com.application.config;

import java.util.Map;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=100)

public class SessionConfig implements HttpSessionListener{
	
	
	@EventListener
	public String   onSessionExpired(SessionExpiredEvent event)
	{
		String sessionId=event.getSessionId();
		System.out.println("session:"+sessionId+"已经过期！");
		return "sessionout";	
	}
	
	@EventListener

	public void  onSessionDeleted(SessionDeletedEvent event)
	{
		String sessionId=event.getSessionId();
		System.out.println("session :"+sessionId+"已经删除！");
	
	}
	
	@EventListener
	
	public void onSessionCreated(SessionCreatedEvent event)
	{
		String sessionId=event.getSessionId();
		System.out.println("session "+sessionId+"已经创建！");
	}
	
	

}
