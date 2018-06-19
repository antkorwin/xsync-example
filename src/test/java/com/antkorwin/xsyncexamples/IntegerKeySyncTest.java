package com.antkorwin.xsyncexamples;

import com.antkorwin.xsync.XSync;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

/**
 * Created on 20.06.2018.
 *
 * @author Korovin Anatolii
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IntegerKeySyncTest {

    private static final int ITERATION_CNT = 100000;

    @Autowired
    @Qualifier("intXSync")
    private XSync<Integer> xSync;

    @Test
    public void testSync() throws InterruptedException {
        // Arrange
        NonAtomicInt nonAtomicInt = new NonAtomicInt(0);

        // Act
        IntStream.range(0, ITERATION_CNT)
                 .boxed()
                 .parallel()
                 .forEach(i -> xSync.execute(123, nonAtomicInt::increment));

        Thread.sleep(1000);

        // Asserts
        Assertions.assertThat(nonAtomicInt.getValue())
                  .isEqualTo(ITERATION_CNT);
    }
}
