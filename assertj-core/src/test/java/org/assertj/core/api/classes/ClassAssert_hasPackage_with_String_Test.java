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
 * Copyright 2012-2023 the original author or authors.
 */
package org.assertj.core.api.classes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldHavePackage.shouldHavePackage;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.core.util.AssertionsUtil.expectAssertionError;
import static org.assertj.core.util.FailureMessages.actualIsNull;

import org.junit.jupiter.api.Test;

/**
 * @author Matteo Mirk
 */
class ClassAssert_hasPackage_with_String_Test {

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    Class<?> actual = null;
    String expected = "java.lang";
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).hasPackage(expected));
    // THEN
    then(assertionError).hasMessage(actualIsNull());
  }

  @Test
  void should_pass_if_expected_package_is_null() {
    // GIVEN
    Class<?> actual = Integer.class;
    String expected = null;
    // WHEN
    Throwable thrown = catchThrowable(() -> assertThat(actual).hasPackage(expected));
    // THEN
    then(thrown).isInstanceOf(NullPointerException.class).hasMessage(shouldNotBeNull("expected").create());
  }

  @Test
  void should_fail_if_actual_has_not_expected_package() {
    // GIVEN
    Class<?> actual = Object.class;
    String expected = "java.util";
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).hasPackage(expected));
    // THEN
    then(assertionError).hasMessage(shouldHavePackage(actual, expected).create());
  }

  @Test
  void should_pass_if_actual_has_expected_package() {
    // GIVEN
    Class<?> actual = Object.class;
    String expected = "java.lang";
    // WHEN/THEN
    assertThat(actual).hasPackage(expected);
  }

}
