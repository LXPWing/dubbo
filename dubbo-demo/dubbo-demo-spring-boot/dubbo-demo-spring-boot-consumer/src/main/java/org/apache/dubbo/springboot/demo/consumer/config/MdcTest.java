package org.apache.dubbo.springboot.demo.consumer.config;

import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

public class MdcTest {

    private static Logger logger = LoggerFactory.getLogger(MdcTest.class);

    public static void main(String[] args) {
        MDC.put("traceId", UUID.randomUUID().toString());
        logger.info("MdcTest");
        MDC.clear();
        logger.info("clear test");
        MDC.remove("traceId");
    }
}
