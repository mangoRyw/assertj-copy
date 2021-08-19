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
 * Copyright 2012-2021 the original author or authors.
 */
package org.assertj.core.api.doublearray;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.core.test.DoubleArrays.arrayOf;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.DoubleArrayAssert;
import org.assertj.core.api.DoubleArrayAssertBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link DoubleArrayAssert#containsAnyOf(Double[])}</code>.
 *
 * @author Omar Morales Ortega
 */
class DoubleArrayAssert_containsAnyOf_with_Double_array_Test extends DoubleArrayAssertBaseTest {

  @Test
  void should_fail_if_values_is_null() {
    // GIVEN
    Double[] values = null;
    // WHEN
    Throwable thrown = catchThrowable(() -> assertions.containsAnyOf(values));
    // THEN
    then(thrown).isInstanceOf(NullPointerException.class)
                .hasMessage(shouldNotBeNull("values").create());
  }

  @Override
  protected DoubleArrayAssert invoke_api_method() {
    return assertions.containsAnyOf(new Double[] { 1.0, 2.0 });
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertContainsAnyOf(getInfo(assertions), getActual(assertions), arrayOf(1.0, 2.0));
  }

}
