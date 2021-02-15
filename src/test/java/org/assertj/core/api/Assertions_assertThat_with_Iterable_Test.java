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
package org.assertj.core.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newLinkedHashSet;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;

/**
 * Tests for <code>{@link Assertions#assertThat(Iterable)}</code>.
 * 
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
class Assertions_assertThat_with_Iterable_Test {

  @Test
  void should_create_Assert() {
    final Iterable<Object> actual = newLinkedHashSet();
    AbstractIterableAssert<?, Iterable<? extends Object>, Object, ObjectAssert<Object>> assertThat = Assertions.assertThat(actual);
    assertThat(assertThat).isNotNull();
  }

  @Test
  void should_pass_actual() {
    Iterable<String> names = newLinkedHashSet("Luke");
    assertThat(Assertions.assertThat(names).actual).isSameAs(names);
  }
}
