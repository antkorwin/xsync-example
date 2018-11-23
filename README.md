# XSync Library examples

XSync is a thread-safe mutex factory, that provide 
ability to synchronize by the value of the object(not by the instance of object).

You can read about XSync in more details here:
- Article: [Synchronized by the value of the object](http://antkorwin.com/concurrency/synchronization_by_value.html)
- GitHub page: [XSync](https://github.com/antkorwin/xsync)

# Add a dependency

```xml
<dependency>
    <groupId>com.antkorwin</groupId>
    <artifactId>xsync</artifactId>
    <version>1.1</version>
</dependency>
```

# Create synchronization primitives

```java
@Configuration
public class XSyncConfig {

    @Bean
    public XSync<UUID> idXSync(){
        return new XSync<>();
    }

    @Bean
    public XSync<Integer> intXSync(){
        return new XSync<>();
    }
}
```

# The sample of usages

```java
@Test
public void testSync() throws InterruptedException {
    String idStr = UUID.randomUUID().toString();
    NonAtomicInt nonAtomicInt = new NonAtomicInt(0);

    IntStream.range(0, ITERATION_CNT)
             .boxed()
             .parallel()
             .forEach(i -> xSync.execute(UUID.fromString(idStr), nonAtomicInt::increment));

    Thread.sleep(1000L);

    Assertions.assertThat(nonAtomicInt.getValue())
              .isEqualTo(ITERATION_CNT);
}

@AllArgsConstructor
@Getter
public class NonAtomicInt {

    private int value;

    public void increment() {
        this.value++;
    }
}
```

You can see a full example in the source code of this repository.
