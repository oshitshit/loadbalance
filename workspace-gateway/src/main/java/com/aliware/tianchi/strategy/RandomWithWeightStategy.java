package com.aliware.tianchi.strategy;

import com.aliware.tianchi.Constants;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;

/**
 * Author: eamon
 * Email: eamon@eamon.cc
 * Time: 2019-07-15 16:46:35
 */
public class RandomWithWeightStategy extends AbstractStrategy {
    static {
        strategy = new RandomWithWeightStategy();
    }

    public static UserLoadBalanceStrategy getInstance() {
        return strategy;
    }

    public static UserLoadBalanceStrategy getInstance(String dataFrom) {
        strategy.dataFrom = dataFrom;
        return strategy;
    }

    @Override
    public int select(URL url, Invocation invocation) {
        int smallActiveCount;
        int mediumActiveCount;
        int largeActiveCount;

        if (dataFrom.equals("client")) {
            smallActiveCount = (int) Constants.longAdderSmall.longValue();
            mediumActiveCount = (int) Constants.longAdderMedium.longValue() << 1;
            largeActiveCount = (int) Constants.longAdderLarge.longValue() * 3;
        } else {
            smallActiveCount = Constants.activeThreadCount.get("small");
            mediumActiveCount = Constants.activeThreadCount.get("medium") << 1;
            largeActiveCount = Constants.activeThreadCount.get("large") * 3;
        }

        int randNumber = rand.nextInt(smallActiveCount + mediumActiveCount + largeActiveCount);
        if (randNumber < smallActiveCount) {
            return 0;
        } else if (randNumber >= smallActiveCount && randNumber < smallActiveCount + mediumActiveCount) {
            return 1;
        } else {
            return 2;
        }
    }

}
