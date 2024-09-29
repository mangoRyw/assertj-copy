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
package org.assertj.core.internal.char2darrays;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Char2DArraysBaseTest;
import org.assertj.core.internal.Int2DArrays;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Int2DArrays#assertHasSameDimensionsAs(AssertionInfo, int[][], Object)}}</code>.
 *
 * @author Maciej Wajcht
 */
class Char2DArrays_assertHasSameDimensionsAs_Test extends Char2DArraysBaseTest {

  @Test
  void should_delegate_to_Arrays2D() {
    // GIVEN
    char[][] other = new char[][] { { 'a', 'b' }, { 'c', 'd' } };
    // WHEN
    char2DArrays.assertHasSameDimensionsAs(info, actual, other);
    // THEN
    verify(arrays2d).assertHasSameDimensionsAs(info, actual, other);
  }

}
