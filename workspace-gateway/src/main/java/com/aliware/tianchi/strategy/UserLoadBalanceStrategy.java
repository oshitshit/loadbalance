package com.aliware.tianchi.strategy;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;

import java.util.List;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-07-15 16:30:29
 */
public interface UserLoadBalanceStrategy {

    int select(URL url, Invocation invocation);



}
