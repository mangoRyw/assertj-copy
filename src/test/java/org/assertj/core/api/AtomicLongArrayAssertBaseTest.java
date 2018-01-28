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
 * Copyright 2012-2018 the original author or authors.
 */
package org.assertj.core.api;

import static org.assertj.core.test.LongArrays.emptyArray;
import static org.assertj.core.util.Arrays.array;
import static org.mockito.Mockito.mock;

import java.util.concurrent.atomic.AtomicLongArray;

import org.assertj.core.internal.LongArrays;

public abstract class AtomicLongArrayAssertBaseTest extends BaseTestTemplate<AtomicLongArrayAssert, AtomicLongArray> {
  protected LongArrays arrays;

  @Override
  protected AtomicLongArrayAssert create_assertions() {
    return new AtomicLongArrayAssert(new AtomicLongArray(emptyArray()));
  }

  @Override
  protected void inject_internal_objects() {
    super.inject_internal_objects();
    arrays = mock(LongArrays.class);
    assertions.arrays = arrays;
  }
  
  protected LongArrays getArrays(AtomicLongArrayAssert someAssertions) {
    return someAssertions.arrays;
  }

  protected long[] internalArray() {
    return array(getActual(assertions));
  }

}
