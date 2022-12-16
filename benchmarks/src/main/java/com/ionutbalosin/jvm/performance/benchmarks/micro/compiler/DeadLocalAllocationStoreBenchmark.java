/**
 *  JVM Performance Benchmarks
 *
 *  Copyright (C) 2019 - 2022 Ionut Balosin
 *  Website: www.ionutbalosin.com
 *  Twitter: @ionutbalosin
 *
 *  Co-author: Florin Blanaru
 *  Twitter: @gigiblender
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.ionutbalosin.jvm.performance.benchmarks.micro.compiler;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/*
 * Dead Store Elimination (DSE) intends to remove all the assignments of a variable that are not read by any subsequent instructions.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.SECONDS)
@Fork(value = 5)
@State(Scope.Benchmark)
public class DeadLocalAllocationStoreBenchmark {

  // $ java -jar */*/benchmarks.jar ".*DeadLocalAllocationStoreBenchmark.*"
  // JMH opts: -prof gc

  @Param({"64"})
  private int size;

  // allocates 16 bytes
  @Benchmark
  public Object obj_alloc_baseline() {
    return new Object();
  }

  // DSE: optimized method should allocate 16 bytes per instance
  @Benchmark
  public Object obj_dse() {
    Object obj;
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    obj = new Object();
    return obj;
  }

  // DSE: optimized method should allocate 16 bytes per instance
  @Benchmark
  public Object obj_dse_inter_procedural() {
    Object obj;
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    sink();
    obj = new Object();
    return obj;
  }

  // allocates 16 bytes
  @Benchmark
  public Wrapper wrapper_obj_baseline() {
    return new Wrapper();
  }

  // allocates 16 bytes
  @Benchmark
  public Wrapper wrapper_obj_dse_inter_procedural() {
    Object obj1 = new Object();
    Object obj2 = new Object();
    Object obj3 = new Object();
    Object obj4 = new Object();
    Object obj5 = new Object();
    Object obj6 = new Object();
    Object obj7 = new Object();
    Object obj8 = new Object();
    return new Wrapper(obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
  }

  // allocates 64 bytes
  @Benchmark
  public byte[] array_baseline() {
    return new byte[size];
  }

  // DSE: optimized method should allocate 64 bytes per array
  @Benchmark
  public byte[] array_dse() {
    byte[] array;
    array = new byte[size];
    array = new byte[size];
    array = new byte[size];
    array = new byte[size];
    return array;
  }

  // DSE: optimized method should allocate 64 bytes per array
  @Benchmark
  public Object array_dse_inter_procedural() {
    byte[] array;
    array = new byte[size];
    sink();
    array = new byte[size];
    sink();
    array = new byte[size];
    sink();
    array = new byte[size];
    return array;
  }

  @CompilerControl(CompilerControl.Mode.DONT_INLINE)
  private void sink() {
    // Intentionally empty method
  }

  private static class Wrapper {
    private Object obj1, obj2, obj3, obj4, obj5, obj6, obj7, obj8;

    public Wrapper() {
      this.obj1 = new Object();
      this.obj2 = new Object();
      this.obj3 = new Object();
      this.obj4 = new Object();
      this.obj5 = new Object();
      this.obj6 = new Object();
      this.obj7 = new Object();
      this.obj8 = new Object();
    }

    public Wrapper(
        Object obj1,
        Object obj2,
        Object obj3,
        Object obj4,
        Object obj5,
        Object obj6,
        Object obj7,
        Object obj8) {

      // inlining of the default constructor should enable the DSE
      this();

      this.obj1 = obj1;
      this.obj2 = obj2;
      this.obj3 = obj3;
      this.obj4 = obj4;
      this.obj5 = obj5;
      this.obj6 = obj6;
      this.obj7 = obj7;
      this.obj8 = obj8;
    }
  }
}