package com.antkorwin.xsyncexamples;

import com.jupiter.tools.stress.test.concurrency.ExecutionMode;
import com.jupiter.tools.stress.test.concurrency.StressTestRunner;

import java.util.concurrent.TimeUnit;


/**
 * see https://github.com/jupiter-tools/stress-test
 * @author Arthur Kupriyanov on 08.02.2020
 */
public class StressTestIteration {
    public static StressTestRunner getIterations(int range){
        return StressTestRunner.test()
                .iterations(range)                          // set count of runs
                .mode(ExecutionMode.EXECUTOR_MODE)          // ThreadPoolExecutor based test runner strategy
                .timeout(1, TimeUnit.MINUTES);     // time limit for tests passing
    }
}
