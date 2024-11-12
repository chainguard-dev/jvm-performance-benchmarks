/*
 * JVM Performance Benchmarks
 *
 * Copyright (C) 2019 - 2024 Ionut Balosin
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.ionutbalosin.jvm.performance.benchmarks.api.string;

import com.ionutbalosin.jvm.performance.benchmarks.api.string.utils.StringUtils.Coder;
import org.openjdk.jmh.annotations.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.ionutbalosin.jvm.performance.benchmarks.api.string.utils.StringUtils.generateCharArray;

// This is a clone of public class StringContainsBenchmark { containing only string template related benchmarks

/**
 * Benchmark measuring the performance of various concatenation methods using different data types
 * (e.g., String, int, float, char, long, double, boolean, Object):
 * - StringBuilder
 * - StringBuffer
 * - String.concat()
 * - plus operator
 * - StringTemplate
 *
 * The input String and char contain characters encoded in either Latin-1 or UTF-16.
 *
 * Note: The benchmark might encompass different allocations, potentially impacting the overall results.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(value = 5)
@State(Scope.Benchmark)
public class StringConcatenationTemplateBenchmark {

  // $ java -jar */*/benchmarks.jar ".*StringConcatenationBenchmark.*"
  // Recommended command line options:
  // - JMH options: -prof gc

  private final Random random = new Random(16384);

  private String aString;
  private int anInt;
  private float aFloat;
  private char aChar;
  private long aLong;
  private double aDouble;
  private boolean aBool;
  private Object anObject;

  @Param({"128"})
  private int length;

  @Param private Coder coder;

  @Setup
  public void setup() {
    aString = new String(generateCharArray(length, coder));
    anInt = random.nextInt();
    aFloat = random.nextFloat();
    aChar = generateCharArray(1, coder)[0];
    aLong = random.nextLong();
    aDouble = random.nextDouble();
    aBool = random.nextBoolean();
    anObject = random.nextLong();
  }

  @Benchmark
  public String string_template() {
    return STR."\{aString}\{anInt}\{aFloat}\{aChar}\{aLong}\{aDouble}\{aBool}\{anObject}";
  }
}