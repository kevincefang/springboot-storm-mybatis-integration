package com.demo.web.controller;

import com.demo.web.storm.StormConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: kevin.
 * @date: 2017/5/17.
 * @description:
 */
public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public StormConfig stormConfig;
}
