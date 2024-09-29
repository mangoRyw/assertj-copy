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
package org.assertj.core.api.float2darray;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.Float2DArrayAssert;
import org.assertj.core.api.Float2DArrayAssertBaseTest;

/**
 * Tests for <code>{@link Float2DArrayAssert#isNotEmpty()}</code>.
 * 
 * @author Maciej Wajcht
 */
class Float2DArrayAssert_isNotEmpty_Test extends Float2DArrayAssertBaseTest {

  @Override
  protected Float2DArrayAssert invoke_api_method() {
    return assertions.isNotEmpty();
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertNotEmpty(getInfo(assertions), getActual(assertions));
  }
}
