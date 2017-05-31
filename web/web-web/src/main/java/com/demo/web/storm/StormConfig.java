package com.demo.web.storm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class StormConfig {


    @Value("${drpc.client.host}")
    private String host;

    @Value("${drpc.client.port}")
    private Integer port;

    @Value("${drpc.client.timeout}")
    private Integer timeout;

    @Value("${drpc.client.storm.thrift.transport}")
    private String transport;

    @Value("${drpc.client.storm.nimbus.retry.times}")
    private Integer retryTimes;

    @Value("${drpc.client.storm.nimbus.retry.interval.millis}")
    private Integer intervalMillis;

    @Value("${drpc.client.storm.nimbus.retry.intervalceiling.millis}")
    private Integer intervalceilingMillis;

    @Value("${drpc.client.max_buffer_size}")
    private Integer bufferSize;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }


    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getIntervalMillis() {
        return intervalMillis;
    }

    public void setIntervalMillis(Integer intervalMillis) {
        this.intervalMillis = intervalMillis;
    }

    public Integer getIntervalceilingMillis() {
        return intervalceilingMillis;
    }

    public void setIntervalceilingMillis(Integer intervalceilingMillis) {
        this.intervalceilingMillis = intervalceilingMillis;
    }

    public Integer getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(Integer bufferSize) {
        this.bufferSize = bufferSize;
    }
}
