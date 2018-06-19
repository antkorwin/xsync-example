package com.antkorwin.xsyncexamples;

import com.antkorwin.xsync.XSync;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XsyncExamplesApplicationTests {

    private static final int ITERATION_CNT = 1000000;

    @Autowired
    @Qualifier("stringXSync")
    private XSync<String> xSync;


    @Test
    public void stringKeyTest() throws InterruptedException {
        // Arrange
        NonAtomicInt nonAtomicInt = new NonAtomicInt(0);

        // Act
        IntStream.range(0, ITERATION_CNT)
                 .boxed()
                 .parallel()
                 .forEach(i -> xSync.execute("sync-key", nonAtomicInt::increment));

        Thread.sleep(1000);

        // Asserts
        Assertions.assertThat(nonAtomicInt.getValue())
                  .isEqualTo(ITERATION_CNT);
    }

    @TestConfiguration
    public static class TestConfig {

        @Bean("stringXSync")
        public XSync<String> stringXSync() {
            return new XSync<>();
        }
    }
}
