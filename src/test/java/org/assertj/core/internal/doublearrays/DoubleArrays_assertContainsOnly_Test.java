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
package org.assertj.core.internal.doublearrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.error.ShouldContainOnly.shouldContainOnly;
import static org.assertj.core.internal.ErrorMessages.valuesToLookForIsNull;
import static org.assertj.core.test.DoubleArrays.arrayOf;
import static org.assertj.core.test.DoubleArrays.emptyArray;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Lists.newArrayList;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.DoubleArrays;
import org.assertj.core.internal.DoubleArraysBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link DoubleArrays#assertContainsOnly(AssertionInfo, double[], double[])}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class DoubleArrays_assertContainsOnly_Test extends DoubleArraysBaseTest {

  @Test
  public void should_pass_if_actual_contains_given_values_only() {
    arrays.assertContainsOnly(someInfo(), actual, arrayOf(6d, 8d, 10d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_in_different_order() {
    arrays.assertContainsOnly(someInfo(), actual, arrayOf(10d, 8d, 6d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_more_than_once() {
    actual = arrayOf(6d, 8d, 10d, 8d, 8d, 8d);
    arrays.assertContainsOnly(someInfo(), actual, arrayOf(6d, 8d, 10d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_even_if_duplicated() {
    arrays.assertContainsOnly(someInfo(), actual, arrayOf(6d, 8d, 10d, 6d, 8d, 10d));
  }

  @Test
  public void should_pass_if_actual_and_given_values_are_empty() {
    actual = emptyArray();
    arrays.assertContainsOnly(someInfo(), actual, emptyArray());
  }
  
  @Test
  public void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arrays.assertContainsOnly(someInfo(), actual, emptyArray()));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arrays.assertContainsOnly(someInfo(), actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertContainsOnly(someInfo(), null, arrayOf(8d));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values_only() {
    double[] expected = { 6d, 8d, 20d };
    thrown.expectAssertionError(shouldContainOnly(actual, expected, newArrayList(20d), newArrayList(10d)));
    arrays.assertContainsOnly(someInfo(), actual, expected);
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, arrayOf(6d, -8d, 10d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_in_different_order_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, arrayOf(10d, -8d, 6d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_more_than_once_according_to_custom_comparison_strategy() {
    actual = arrayOf(6d, -8d, 10d, -8d, -8d, -8d);
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, arrayOf(6d, -8d, 10d));
  }

  @Test
  public void should_pass_if_actual_contains_given_values_only_even_if_duplicated_according_to_custom_comparison_strategy() {
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, arrayOf(6d, -8d, 10d, 6d, -8d, 10d));
  }

  @Test
  public void should_fail_if_array_of_values_to_look_for_is_empty_and_actual_is_not_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, emptyArray()));
  }

  @Test
  public void should_throw_error_if_array_of_values_to_look_for_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectNullPointerException(valuesToLookForIsNull());
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, null);
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    thrown.expectAssertionError(actualIsNull());
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), null, arrayOf(-8d));
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values_only_according_to_custom_comparison_strategy() {
    double[] expected = { 6d, -8d, 20d };
    thrown.expectAssertionError(shouldContainOnly(actual, expected, newArrayList(20d), newArrayList(10d),
                                                  absValueComparisonStrategy));
    arraysWithCustomComparisonStrategy.assertContainsOnly(someInfo(), actual, expected);
  }
}
