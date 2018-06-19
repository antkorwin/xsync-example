package com.antkorwin.xsyncexamples;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created on 20.06.2018.
 *
 * @author Korovin Anatolii
 */
@AllArgsConstructor
@Getter
public class NonAtomicInt {

    private int value;

    public void increment() {
        this.value++;
    }
}

