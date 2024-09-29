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
package org.assertj.core.internal.int2darrays;

import static org.assertj.core.data.Index.atIndex;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.data.Index;
import org.assertj.core.internal.Int2DArrays;
import org.assertj.core.internal.Int2DArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Int2DArrays#assertDoesNotContain(AssertionInfo, int[][], int[], Index)}</code>.
 *
 * @author Maciej Wajcht
 */
class Int2DArrays_assertDoesNotContain_at_Index_Test extends Int2DArraysBaseTest {

  @Test
  void should_delegate_to_Arrays2D() {
    // GIVEN
    int[] ints = new int[] { 0, 2, 4 };
    // WHEN
    int2DArrays.assertDoesNotContain(info, actual, ints, atIndex(1));
    // THEN
    verify(arrays2d).assertDoesNotContain(info, failures, actual, ints, atIndex(1));
  }
}
