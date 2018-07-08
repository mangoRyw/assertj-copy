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
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.core.internal.floatarrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.error.ShouldStartWith.shouldStartWith;
import static org.assertj.core.internal.ErrorMessages.*;
import static org.assertj.core.test.FloatArrays.*;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.FloatArrays;
import org.assertj.core.internal.FloatArraysBaseTest;
import org.junit.Test;

/**
 * Tests for <code>{@link FloatArrays#assertStartsWith(AssertionInfo, float[], float[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class FloatArrays_assertStartsWith_Test extends FloatArraysBaseTest {

  @Override
  protected void initActualArray() {
    actual = arrayOf(6f, 8f, 10f, 12f);
  }

  @Test
  public void should_throw_error_if_sequence_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arrays.assertStartsWith(someInfo(), actual, null);
  }

  @Test
  public void should_pass_if_actual_and_given_values_are_empty() {
    actual = emptyArray();
    arrays.assertStartsWith(someInfo(), actual, emptyArray());
  }

  @Test
  public void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertStartsWith(someInfo(), actual, emptyArray()));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertStartsWith(someInfo(), null, arrayOf(6f));
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual() {
    float[] sequence = { 6f, 8f, 10f, 12f, 20f, 22f };
    try {
      AssertionInfo info = someInfo();
      arrays.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    AssertionInfo info = someInfo();
    float[] sequence = { 8f, 10f };
    try {
      arrays.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_first_elements_of_sequence_only() {
    AssertionInfo info = someInfo();
    float[] sequence = { 6f, 20f };
    try {
      arrays.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    arrays.assertStartsWith(someInfo(), actual, arrayOf(6f, 8f, 10f));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal() {
    arrays.assertStartsWith(someInfo(), actual, arrayOf(6f, 8f, 10f, 12f));
  }

  @Test
  public void should_throw_error_if_sequence_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arraysWithCustomComparisonStrategy.assertStartsWith(someInfo(), actual, null);
  }

  @Test
  public void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arraysWithCustomComparisonStrategy.assertStartsWith(someInfo(), actual, emptyArray()));
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    arraysWithCustomComparisonStrategy.assertStartsWith(someInfo(), null, arrayOf(6f));
  }

  @Test
  public void should_fail_if_sequence_is_bigger_than_actual_according_to_custom_comparison_strategy() {
    float[] sequence = { 6f, -8f, 10f, 12f, 20f, 22f };
    try {
      AssertionInfo info = someInfo();
      arraysWithCustomComparisonStrategy.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence, absValueComparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    float[] sequence = { -8f, 10f };
    try {
      arraysWithCustomComparisonStrategy.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence, absValueComparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_fail_if_actual_starts_with_first_elements_of_sequence_only_according_to_custom_comparison_strategy() {
    AssertionInfo info = someInfo();
    float[] sequence = { 6f, 20f };
    try {
      arraysWithCustomComparisonStrategy.assertStartsWith(info, actual, sequence);
    } catch (AssertionError e) {
      verify(failures).failure(someInfo(), shouldStartWith(actual, sequence, absValueComparisonStrategy));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertStartsWith(someInfo(), actual, arrayOf(6f, -8f, 10f));
  }

  @Test
  public void should_pass_if_actual_and_sequence_are_equal_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertStartsWith(someInfo(), actual, arrayOf(6f, -8f, 10f, 12f));
  }
}
