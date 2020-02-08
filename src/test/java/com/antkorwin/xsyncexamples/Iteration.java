package com.antkorwin.xsyncexamples;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Arthur Kupriyanov on 08.02.2020
 */
public class Iteration {
    public static IntStream getIterations(int range){
        return IntStream.range(0 ,range);
    }

    public static Stream<Integer> getParallelIterations(int range){
        return getIterations(range).boxed().parallel();
    }
}
