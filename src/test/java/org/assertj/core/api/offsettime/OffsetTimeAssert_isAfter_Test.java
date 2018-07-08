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
package org.assertj.core.api.offsettime;

import static java.time.OffsetTime.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import java.time.OffsetTime;

import org.junit.Test;

public class OffsetTimeAssert_isAfter_Test extends OffsetTimeAssertBaseTest {

  @Test
  public void test_isAfter_assertion() {
    // WHEN
    assertThat(AFTER).isAfter(REFERENCE);
    assertThat(AFTER).isAfter(REFERENCE.toString());
    // THEN
    verify_that_isAfter_assertion_fails_and_throws_AssertionError(REFERENCE, REFERENCE);
    verify_that_isAfter_assertion_fails_and_throws_AssertionError(BEFORE, REFERENCE);
  }

  @Test
  public void test_isAfter_assertion_error_message() {
    thrown.expectAssertionError("%n" +
                                "Expecting:%n" +
                                "  <03:00:05.123Z>%n" +
                                "to be strictly after:%n" +
                                "  <03:00:05.123456789Z>");
    assertThat(parse("03:00:05.123Z")).isAfter(parse("03:00:05.123456789Z"));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectException(AssertionError.class, actualIsNull());
    OffsetTime actual = null;
    assertThat(actual).isAfter(OffsetTime.now());
  }

  @Test
  public void should_fail_if_offsetTime_parameter_is_null() {
    expectException(IllegalArgumentException.class, "The OffsetTime to compare actual with should not be null");
    assertThat(OffsetTime.now()).isAfter((OffsetTime) null);
  }

  @Test
  public void should_fail_if_offsetTime_as_string_parameter_is_null() {
    expectException(IllegalArgumentException.class,
                    "The String representing the OffsetTime to compare actual with should not be null");
    assertThat(OffsetTime.now()).isAfter((String) null);
  }

  private static void verify_that_isAfter_assertion_fails_and_throws_AssertionError(OffsetTime timeToCheck,
                                                                                    OffsetTime reference) {
    try {
      assertThat(timeToCheck).isAfter(reference);
    } catch (AssertionError e) {
      // AssertionError was expected, test same assertion with String based parameter
      try {
        assertThat(timeToCheck).isAfter(reference.toString());
      } catch (AssertionError e2) {
        // AssertionError was expected (again)
        return;
      }
    }
    fail("Should have thrown AssertionError");
  }

}
