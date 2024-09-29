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
package org.assertj.tests.guava.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldHaveSize.shouldHaveSize;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.guava.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * @author David Harris
 */
public class TableAssert_hasSize_Test extends TableAssertBaseTest {

  @Test
  public void should_pass_if_actual_has_0_cells() {
    actual.clear();
    assertThat(actual).hasSize(0);
  }

  @Test
  public void should_pass_if_actual_has_4_cells() {
    assertThat(actual).hasSize(3);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    // GIVEN
    actual = null;
    // WHEN
    Throwable throwable = catchThrowable(() -> assertThat(actual).hasSize(3));
    // THEN
    assertThat(throwable).isInstanceOf(AssertionError.class)
                         .hasMessage(actualIsNull());
  }

  @Test
  public void should_fail_if_expected_is_negative() {
    // WHEN
    Throwable throwable = catchThrowable(() -> assertThat(actual).hasSize(-1));
    // THEN
    assertThat(throwable).isInstanceOf(IllegalArgumentException.class)
                         .hasMessage("The expected size should not be negative.");
  }

  @Test
  public void should_fail_if_actual_does_not_have_3_cells() {
    // GIVEN
    actual.clear();
    // WHEN
    Throwable throwable = catchThrowable(() -> assertThat(actual).hasSize(3));
    // THEN
    assertThat(throwable).isInstanceOf(AssertionError.class)
                         .hasMessage(shouldHaveSize(actual, actual.size(), 3).create());
  }

}
