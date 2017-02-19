/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2017 the original author or authors.
 */
package org.assertj.core.internal.classes;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.ClassesBaseTest;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.error.ShouldHaveMethods.shouldHaveMethods;
import static org.assertj.core.error.ShouldHaveMethods.shouldNotHaveMethods;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.assertj.core.util.Sets.newTreeSet;

/**
 * Tests for
 * <code>{@link org.assertj.core.internal.Classes#assertHasDeclaredMethods(AssertionInfo, Class, String...)}</code>
 */
public class Classes_assertHasDeclaredMethods_Test extends ClassesBaseTest {

  @Before
  public void setupActual() {
    actual = MethodsClass.class;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    actual = null;
    thrown.expectAssertionError(actualIsNull());
    classes.assertHasDeclaredMethods(someInfo(), actual);
  }

  @Test
  public void should_succeed_if_no_methods_are_expected_and_no_declared_methods_are_available() {
    actual = Jedi.class;
    classes.assertHasDeclaredPublicMethods(someInfo(), actual);
  }

  @Test
  public void should_fail_if_no_methods_are_expected_and_declared_methods_are_available() {
    thrown.expectAssertionError(shouldNotHaveMethods(actual, true, newTreeSet("publicMethod", "protectedMethod", "privateMethod")));
    classes.assertHasDeclaredMethods(someInfo(), actual);
  }

  @Test
  public void should_pass_if_methods_are_public() {
    classes.assertHasDeclaredMethods(someInfo(), actual, "publicMethod");
  }

  @Test()
  public void should_fail_if_methods_are_missing() {
    String[] expected = new String[] { "missingMethod", "publicMethod" };
    thrown.expectAssertionError(shouldHaveMethods(actual, true,
      newTreeSet(expected),
      newTreeSet("missingMethod")));
      classes.assertHasDeclaredMethods(someInfo(), actual, expected);
  }
}
