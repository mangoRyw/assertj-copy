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
package org.assertj.core.internal.conditions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.error.ShouldHave.shouldHave;
import static org.assertj.core.test.TestData.someInfo;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.api.Condition;
import org.assertj.core.internal.Conditions;
import org.assertj.core.internal.ConditionsBaseTest;
import org.junit.jupiter.api.Test;


/**
 * Tests for <code>{@link Conditions#assertHas(AssertionInfo, Object, Condition)}</code>.
 * 
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class Conditions_assertHas_Test extends ConditionsBaseTest {

  @Test
  public void should_throw_error_if_Condition_is_null() {
    assertThatNullPointerException().isThrownBy(() -> conditions.assertHas(someInfo(), actual, null))
                                    .withMessage("The condition to evaluate should not be null");
  }

  @Test
  public void should_pass_if_Condition_is_met() {
    condition.shouldMatch(true);
    conditions.assertHas(someInfo(), actual, condition);
  }

  @Test
  public void should_fail_if_Condition_is_not_met() {
    condition.shouldMatch(false);
    AssertionInfo info = someInfo();

    Throwable error = catchThrowable(() -> conditions.assertHas(info, actual, condition));

    assertThat(error).isInstanceOf(AssertionError.class);
    verify(failures).failure(info, shouldHave(actual, condition));
  }
}
