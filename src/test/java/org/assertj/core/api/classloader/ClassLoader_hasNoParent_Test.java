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
 * Copyright 2012-2022 the original author or authors.
 */
package org.assertj.core.api.classloader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.core.error.classloader.ShouldNotHaveParent.shouldNotHaveParent;

import org.assertj.core.api.AbstractClassLoaderAssert;
import org.assertj.core.api.ClassLoaderAssert;
import org.assertj.core.api.classloader.ClassLoaderTestUtils.SimpleClassLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ClassLoaderAssert#hasNoParent()}.
 *
 * @author Ashley Scopes
 */
@DisplayName("ClassLoader hasNoParent tests")
class ClassLoader_hasNoParent_Test {

  @Test
  void should_fail_if_actual_is_null() {
    // Then
    assertThatCode(() -> assertThat((ClassLoader) null).hasNoParent())
      .isInstanceOf(AssertionError.class)
      .hasMessage(shouldNotBeNull().create());
  }

  @Test
  void should_fail_if_actual_has_parent() {
    // Given
    ClassLoader parentClassLoader = new SimpleClassLoader();
    ClassLoader childClassLoader = new SimpleClassLoader(parentClassLoader);

    // Then
    assertThatCode(() -> assertThat(childClassLoader).hasNoParent())
      .isInstanceOf(AssertionError.class)
      .hasMessage(shouldNotHaveParent(childClassLoader, parentClassLoader).create());
  }

  @Test
  void should_succeed_if_actual_has_no_parent() {
    // Given
    ClassLoader classLoader = new SimpleClassLoader();

    // Then
    assertThatNoException()
      .isThrownBy(() -> assertThat(classLoader).hasNoParent());
  }

  @Test
  void should_return_class_loader_assert_when_successful() {
    // Given
    ClassLoader classLoader = new SimpleClassLoader();
    AbstractClassLoaderAssert<?> expectedAssertions = assertThat(classLoader);

    // When
    AbstractClassLoaderAssert<?> actualAssertions = expectedAssertions.hasNoParent();

    // Then
    assertThat(actualAssertions)
      .isSameAs(expectedAssertions);
  }
}
