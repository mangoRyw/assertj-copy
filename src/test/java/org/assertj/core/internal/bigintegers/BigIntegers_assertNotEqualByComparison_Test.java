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
 * Copyright 2012-2020 the original author or authors.
 */
package org.assertj.core.internal.bigintegers;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.BigIntegers;
import org.assertj.core.internal.BigIntegersBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link BigIntegers#assertNotEqualByComparison(AssertionInfo, BigInteger, BigInteger)}</code>.
 */
public class BigIntegers_assertNotEqualByComparison_Test extends BigIntegersBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> numbers.assertNotEqualByComparison(someInfo(), null, ONE))
                                                   .withMessage(actualIsNull());
  }

  @Test
  public void should_pass_if_big_integers_are_not_equal_by_comparison() {
    numbers.assertNotEqualByComparison(someInfo(), TEN, ONE);
  }

  @Test
  public void should_fail_if_big_integers_are_equal_by_comparison() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> numbers.assertNotEqualByComparison(info, ONE, ONE));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotBeEqual(ONE, ONE));
  }

  @Test
  public void should_fail_if_actual_is_null_whatever_custom_comparison_strategy_is() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> numbersWithAbsValueComparisonStrategy.assertNotEqualByComparison(someInfo(), null, ONE))
                                                   .withMessage(actualIsNull());
  }

  @Test
  public void should_pass_if_big_integers_are_not_equal_by_comparison_whatever_custom_comparison_strategy_is() {
    numbersWithAbsValueComparisonStrategy.assertNotEqualByComparison(someInfo(), TEN, ONE);
  }

  @Test
  public void should_fail_if_big_integers_are_equal_by_comparison_whatever_custom_comparison_strategy_is() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> numbersWithAbsValueComparisonStrategy.assertNotEqualByComparison(info, ONE, ONE));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldNotBeEqual(ONE, ONE));
  }
}
