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
package org.assertj.core.api.byte2darray;

import static org.assertj.core.testkit.TestData.someIndex;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.Byte2DArrayAssert;
import org.assertj.core.api.Byte2DArrayAssertBaseTest;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.DisplayName;

/**
 * Tests for <code>{@link Byte2DArrayAssert#doesNotContain(byte[], Index)}</code>.
 * 
 * @author Maciej Wajcht
 */
@DisplayName("Byte2DArrayAssert doesNotContain")
class Byte2DArrayAssert_doesNotContain_at_Index_Test extends Byte2DArrayAssertBaseTest {
  private final Index index = someIndex();

  @Override
  protected Byte2DArrayAssert invoke_api_method() {
    return assertions.doesNotContain(new byte[] { 8, 9 }, index);
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertDoesNotContain(getInfo(assertions), getActual(assertions), new byte[] { 8, 9 }, index);
  }
}
