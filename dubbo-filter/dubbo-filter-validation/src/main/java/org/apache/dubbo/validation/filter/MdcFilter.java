package org.apache.dubbo.validation.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

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
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("mdc测试");
        return invoker.invoke(invocation);
    }
}
