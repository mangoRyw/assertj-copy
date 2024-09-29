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
package org.assertj.core.internal.boolean2darrays;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Boolean2DArrays;
import org.assertj.core.internal.Boolean2DArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Boolean2DArrays#assertEmpty(AssertionInfo, boolean[][])}</code>.
 *
 * @author Maciej Wajcht
 */
class Boolean2DArrays_assertEmpty_Test extends Boolean2DArraysBaseTest {

  @Test
  void should_delegate_to_Arrays2D() {
    // WHEN
    boolean2dArrays.assertEmpty(info, actual);
    // THEN
    verify(arrays2d).assertEmpty(info, failures, actual);
  }
}
