package com.altimetrik.music.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:music_api.properties")
public class AbstractProxy {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

}
