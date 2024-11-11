/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2024 the original author or authors.
 */
package org.assertj.core.internal.maps;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonMap;
import static java.util.Collections.unmodifiableMap;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.data.MapEntry.entry;
import static org.assertj.core.error.ShouldContainValues.shouldContainValues;
import static org.assertj.core.testkit.Maps.mapOf;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.Arrays.array;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Sets.set;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.collections4.map.SingletonMap;
import org.assertj.core.internal.MapsBaseTest;
import org.assertj.core.testkit.jdk11.Jdk11;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.google.common.collect.ImmutableMap;

/**
 * @author Nicolas François
 * @author Joel Costigliola
 * @author Alexander Bischof
 */
class Maps_assertContainsValues_Test extends MapsBaseTest {

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    String[] values = { "Yoda" };
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> maps.assertContainsValues(someInfo(), null, values));
    // THEN
    then(assertionError).hasMessage(actualIsNull());
  }

  @Test
  void should_fail_if_given_values_array_is_null() {
    // GIVEN
    String[] values = null;
    // WHEN
    Throwable thrown = catchThrowable(() -> maps.assertContainsValues(someInfo(), actual, values));
    // THEN
    then(thrown).isInstanceOf(NullPointerException.class).hasMessage("The array of values to look for should not be null");
  }

  @Test
  void should_fail_if_given_values_array_is_empty() {
    // GIVEN
    String[] values = array();
    // WHEN
    Throwable thrown = catchThrowable(() -> maps.assertContainsValues(someInfo(), actual, values));
    // THEN
    then(thrown).isInstanceOf(IllegalArgumentException.class).hasMessage("The array of values to look for should not be empty");
  }

  @ParameterizedTest
  @MethodSource({
      "unmodifiableMapsSuccessfulTestCases",
      "modifiableMapsSuccessfulTestCases",
  })
  void should_pass(Map<String, String> actual, String[] expected) {
    // WHEN/THEN
    assertThatNoException().as(actual.getClass().getName())
                           .isThrownBy(() -> maps.assertContainsValues(info, actual, expected));
  }

  private static Stream<Arguments> unmodifiableMapsSuccessfulTestCases() {
    return Stream.of(arguments(emptyMap(), emptyKeys()),
                     arguments(singletonMap("name", "Yoda"), array("Yoda")),
                     arguments(new SingletonMap<>("name", "Yoda"), array("Yoda")),
                     arguments(unmodifiableMap(mapOf(entry("name", "Yoda"), entry("job", "Jedi"))), array("Yoda", "Jedi")),
                     arguments(unmodifiableMap(mapOf(entry("name", "Yoda"), entry("job", "Jedi"))), array("Jedi", "Yoda")),
                     arguments(ImmutableMap.of("name", "Yoda", "job", "Jedi"), array("Yoda", "Jedi")),
                     arguments(ImmutableMap.of("name", "Yoda", "job", "Jedi"), array("Jedi", "Yoda")),
                     arguments(Jdk11.Map.of("name", "Yoda", "job", "Jedi"), array("Yoda", "Jedi")),
                     arguments(Jdk11.Map.of("name", "Yoda", "job", "Jedi"), array("Jedi", "Yoda")));
  }

  private static Stream<Arguments> modifiableMapsSuccessfulTestCases() {
    return Stream.of(MODIFIABLE_MAPS)
                 .flatMap(supplier -> Stream.of(arguments(mapOf(supplier, entry("name", "Yoda"), entry("job", "Jedi")),
                                                          array("Yoda")),
                                                arguments(mapOf(supplier, entry("name", "Yoda"), entry("job", "Jedi")),
                                                          array("Yoda", "Jedi")),
                                                arguments(mapOf(supplier, entry("name", "Yoda"), entry("job", "Jedi")),
                                                          array("Jedi", "Yoda"))));
  }

  @ParameterizedTest
  @MethodSource({
      "unmodifiableMapsFailureTestCases",
      "modifiableMapsFailureTestCases",
  })
  void should_fail(Map<String, String> actual, String[] expected, Set<String> notFound) {
    // WHEN
    assertThatExceptionOfType(AssertionError.class).as(actual.getClass().getName())
                                                   .isThrownBy(() -> maps.assertContainsValues(info, actual, expected))
                                                   // THEN
                                                   .withMessage(shouldContainValues(actual, notFound).create());
  }

  private static Stream<Arguments> unmodifiableMapsFailureTestCases() {
    return Stream.of(arguments(emptyMap(),
                               array("Yoda"),
                               set("Yoda")),
                     arguments(singletonMap("name", "Yoda"),
                               array("green"),
                               set("green")),
                     arguments(new SingletonMap<>("name", "Yoda"),
                               array("green"),
                               set("green")),
                     arguments(unmodifiableMap(mapOf(entry("name", "Yoda"), entry("job", "Jedi"))),
                               array("Yoda", "green"),
                               set("green")),
                     arguments(ImmutableMap.of("name", "Yoda", "job", "Jedi"),
                               array("Yoda", "green"),
                               set("green")),
                     arguments(Jdk11.Map.of("name", "Yoda", "job", "Jedi"),
                               array("Yoda", "green"),
                               set("green")),
                     arguments(Jdk11.Map.of("name", "Yoda"),
                               array((String) null), // implementation not permitting null keys
                               set((String) null)));
  }

  private static Stream<Arguments> modifiableMapsFailureTestCases() {
    return Stream.of(MODIFIABLE_MAPS)
                 .flatMap(supplier -> Stream.of(arguments(mapOf(supplier, entry("name", "Yoda")),
                                                          array("Yoda", "green"),
                                                          set("green")),
                                                arguments(mapOf(supplier, entry("name", "Yoda"), entry("job", "Jedi")),
                                                          array("Yoda", "green"),
                                                          set("green"))));
  }

}
