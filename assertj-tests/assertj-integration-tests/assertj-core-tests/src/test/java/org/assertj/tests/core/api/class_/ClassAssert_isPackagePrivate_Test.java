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
package org.assertj.tests.core.api.class_;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ClassModifierShouldBe.shouldBePackagePrivate;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import org.junit.jupiter.api.Test;

class ClassAssert_isPackagePrivate_Test {

  @Test
  void should_fail_if_actual_is_null() {
    // GIVEN
    Class<?> actual = null;
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).isPackagePrivate());
    // THEN
    then(assertionError).hasMessage(shouldNotBeNull().create());
  }

  @Test
  void should_fail_if_actual_is_not_package_private() {
    // GIVEN
    Class<?> actual = String.class;
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).isPackagePrivate());
    // THEN
    then(assertionError).hasMessage(shouldBePackagePrivate(actual).create());
  }

  @Test
  void should_pass_if_actual_is_package_private() {
    // GIVEN
    Class<?> actual = PackagePrivateClass.class;
    // WHEN/THEN
    assertThat(actual).isPackagePrivate();
  }

  static class PackagePrivateClass {
  }

}
