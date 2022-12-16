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
package com.ionutbalosin.jvm.performance.benchmarks.macro.wordfrequency.parallelstream;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.util.function.Function.identity;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingByConcurrent;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Pattern;

public class ParallelStreamWordFrequency {

  private static final Pattern PATTERN = Pattern.compile("[\\W]+");

  public static Map<String, Long> frequencies(String fileName) throws IOException {
    final Map<String, Long> wordFrequencies;

    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(new FileInputStream(fileName), ISO_8859_1))) {
      wordFrequencies =
          reader
              .lines()
              .parallel()
              .flatMap(PATTERN::splitAsStream)
              .filter(not(String::isBlank))
              .collect(groupingByConcurrent(identity(), counting()));
    }

    return wordFrequencies;
  }
}