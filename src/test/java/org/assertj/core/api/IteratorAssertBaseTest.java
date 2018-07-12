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

import static java.util.Collections.emptyList;
import static org.assertj.core.test.ExpectedException.none;
import static org.mockito.Mockito.mock;

import org.assertj.core.internal.Iterators;
import org.assertj.core.test.ExpectedException;
import org.junit.Rule;

import java.util.Iterator;


/**
 * Tests for <code>{@link AbstractIteratorAssert#hasNext()}</code>.
 * 
 * @author Stephan Windmüller
 */
public abstract class IteratorAssertBaseTest extends BaseTestTemplate<IteratorAssert<Object>, Iterator<?>> {

  @Rule
  public ExpectedException thrown = none();

  protected Iterators iterators;

  @Override
  protected IteratorAssert<Object> create_assertions() {
    return new IteratorAssert<>(emptyList().iterator());
  }

  @Override
  protected void inject_internal_objects() {
    super.inject_internal_objects();
    iterators = mock(Iterators.class);
    assertions.iterators = iterators;
  }

  protected Iterators getIterators(IteratorAssert<Object> assertions) {
    return assertions.iterators;
  }
}
