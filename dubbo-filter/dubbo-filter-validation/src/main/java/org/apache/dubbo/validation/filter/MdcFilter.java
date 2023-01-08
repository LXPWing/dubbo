package org.apache.dubbo.validation.filter;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * <p>
 * </p>
 *
 * @author lxp
 * @data 2023-1-5 14:27
 */
@Activate(group = {PROVIDER, CONSUMER})
public class MdcFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(MdcFilter.class);

    private final static Integer MAX_LOG_LENGTH=5000;

    private final static Integer REMAINING_LOG_LENGTH=1000;

    public static final String REQ_ID = "REQ_ID";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("mdc测试");
        String methodName = invocation.getMethodName();
        Object[] arguments = invocation.getArguments();
        String className = invoker.getInterface().getName();
        String callMethod = className + "." + methodName;
        String argsJson = JSON.toJSONString(arguments);
        System.out.println((String.format("rpc接口callMethod: %s >>入参: %s", callMethod, argsJson)));
        long start = System.currentTimeMillis();
        AsyncRpcResult result = (AsyncRpcResult)invoker.invoke(invocation);
        if(result.hasException()){
            System.out.println((String.format("rpc接口callMethod: %s,接口耗时: %s,异常: %s,", callMethod,  System.currentTimeMillis() - start,result.getException().getMessage())));
        } else {
            Object resultString = JSON.toJSON(result.getAppResponse().getValue());
            if(resultString!=null&&resultString.toString().length()>MAX_LOG_LENGTH){
                resultString=resultString.toString().substring(0,REMAINING_LOG_LENGTH)+"...";
            }
            System.out.println(String.format("rpc接口callMethod: %s,出参: %s,接口耗时: %s", callMethod,resultString , System.currentTimeMillis() - start));
        }
        System.out.println(logger == null);

//        MDC.put(REQ_ID, UUID.randomUUID() + "这里噢");
        MDC.put("traceId", "lxp123+++");


        //System.out.println(MDC.get("traceId"));
        logger.info("MdcTest");
        //MDC.clear();
        //logger.info("clear test");
        //MDC.remove("traceId");

        return result;
    }
}
