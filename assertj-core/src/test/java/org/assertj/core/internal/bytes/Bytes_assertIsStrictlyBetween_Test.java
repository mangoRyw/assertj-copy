/*
 * Copyright © 2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.assertj.core.internal.bytes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldBeBetween.shouldBeBetween;
import static org.assertj.core.testkit.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Bytes;
import org.assertj.core.internal.BytesBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Bytes#assertIsStrictlyBetween(AssertionInfo, Byte, Byte, Byte)}</code>.
 * 
 * @author William Delanoue
 */
class Bytes_assertIsStrictlyBetween_Test extends BytesBaseTest {

  private static final Byte ZERO = (byte) 0;
  private static final Byte ONE = (byte) 1;
  private static final Byte TWO = (byte) 2;
  private static final Byte TEN = (byte) 10;

  @Test
  void should_fail_if_actual_is_null() {
    assertThatExceptionOfType(AssertionError.class).isThrownBy(() -> bytes.assertIsStrictlyBetween(someInfo(), null, ZERO, ONE))
                                                   .withMessage(actualIsNull());
  }

  @Test
  void should_fail_if_start_is_null() {
    assertThatNullPointerException().isThrownBy(() -> bytes.assertIsStrictlyBetween(someInfo(), ONE, null, ONE));
  }

  @Test
  void should_fail_if_end_is_null() {
    assertThatNullPointerException().isThrownBy(() -> bytes.assertIsStrictlyBetween(someInfo(), ONE, ZERO, null));
  }

  @Test
  void should_pass_if_actual_is_in_range() {
    bytes.assertIsStrictlyBetween(someInfo(), ONE, ZERO, TEN);
  }

  @Test
  void should_fail_if_actual_is_equal_to_range_start() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> bytes.assertIsStrictlyBetween(info, ONE, ONE, TEN));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldBeBetween(ONE, ONE, TEN, false, false));
  }

  @Test
  void should_fail_if_actual_is_equal_to_range_end() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> bytes.assertIsStrictlyBetween(info, ONE, ZERO, ONE));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldBeBetween(ONE, ZERO, ONE, false, false));
  }

  @Test
  void should_fail_if_actual_is_not_in_range_start() {
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> bytes.assertIsStrictlyBetween(info, ONE, TWO, TEN));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldBeBetween(ONE, TWO, TEN, false, false));
  }

  @Test
  void should_fail_if_actual_is_not_in_range_end() {
    assertThatIllegalArgumentException().isThrownBy(() -> bytes.assertIsStrictlyBetween(someInfo(), ONE, ZERO, ZERO))
                                        .withMessage("The end value <0> must not be less than or equal to the start value <0>!");
  }
}
