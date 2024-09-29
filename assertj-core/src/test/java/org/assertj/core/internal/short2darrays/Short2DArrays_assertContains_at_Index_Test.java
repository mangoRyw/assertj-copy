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
package org.assertj.core.internal.short2darrays;

import static org.assertj.core.data.Index.atIndex;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.data.Index;
import org.assertj.core.internal.Short2DArrays;
import org.assertj.core.internal.Short2DArraysBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link Short2DArrays#assertContains(AssertionInfo, short[][], short[], Index)}</code>.
 *
 * @author Maciej Wajcht
 */
class Short2DArrays_assertContains_at_Index_Test extends Short2DArraysBaseTest {

  @Test
  void should_delegate_to_Arrays2D() {
    // GIVEN
    short[] shorts = new short[] { 6, 8, 10 };
    // WHEN
    short2DArrays.assertContains(info, actual, shorts, atIndex(1));
    // THEN
    verify(arrays2d).assertContains(info, failures, actual, shorts, atIndex(1));
  }
}
