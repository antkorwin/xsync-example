package com.antkorwin.xsyncexamples;

import com.antkorwin.xsync.XSync;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created on 20.06.2018.
 *
 * @author Korovin Anatolii
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdKeySyncTest {

    private static final int ITERATION_CNT = 5000;

    @Autowired
    @Qualifier("idXSync")
    private XSync<UUID> xSync;

    @Test
    public void testSync() throws InterruptedException {
        String idStr = UUID.randomUUID().toString();
        NonAtomicInt nonAtomicInt = new NonAtomicInt(0);

        StressTestIteration.getIterations(ITERATION_CNT)
                .threads(8)
                .run(() -> xSync.execute(UUID.fromString(idStr), nonAtomicInt::increment));

        Thread.sleep(1000L);

        Assertions.assertThat(nonAtomicInt.getValue())
                .isEqualTo(ITERATION_CNT);
    }
}
