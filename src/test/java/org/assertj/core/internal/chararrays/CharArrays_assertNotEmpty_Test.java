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
package org.assertj.core.internal.chararrays;

import static org.assertj.core.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.assertj.core.test.CharArrays.*;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;


import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.CharArrays;
import org.assertj.core.internal.CharArraysBaseTest;
import org.junit.Test;


/**
 * Tests for <code>{@link CharArrays#assertNotEmpty(AssertionInfo, char[])}</code>.
 * 
 * @author Alex Ruiz
 */
public class CharArrays_assertNotEmpty_Test extends CharArraysBaseTest {

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertNotEmpty(someInfo(), null);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    AssertionInfo info = someInfo();
    try {
      arrays.assertNotEmpty(info, emptyArray());
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldNotBeEmpty());
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_not_empty() {
    arrays.assertNotEmpty(someInfo(), arrayOf('a'));
  }
}
