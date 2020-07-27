package com.wn.wangzheng.chapter34.step2;

import com.wn.wangzheng.chapter34.step4.IdGenerationFailureException;

public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
