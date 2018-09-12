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
package org.assertj.core.error;

import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
import org.assertj.core.presentation.HexadecimalRepresentation;
import org.assertj.core.presentation.Representation;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.ShouldHaveSizeGreaterThan.shouldHaveSizeGreaterThan;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * Tests for <code>{@link ShouldHaveSizeGreaterThan#create(Description, Representation)}</code>.
 * 
 * @author Sandra Parsick
 * @author Georg Berky
 */
class ShouldHaveSizeGreaterThan_create_Test {

  private ErrorMessageFactory factory;

  @BeforeEach
  void setUp() {
    factory = shouldHaveSizeGreaterThan(newArrayList('a', 'b'), 4, 2);
  }

  @Test
  void should_create_error_message() {
    String message = factory.create(new TextDescription("Test"), new StandardRepresentation());
    assertThat(message).isEqualTo(String.format("[Test] %nExpected size to be greater than:<2> but was:<4> in:%n<['a', 'b']>"));
  }

  @Test
  void should_create_error_message_with_hexadecimal_representation() {
    String message = factory.create(new TextDescription("Test"), new HexadecimalRepresentation());
    assertThat(message).isEqualTo(String.format("[Test] %nExpected size to be greater than:<2> but was:<4> in:%n<['0x0061', '0x0062']>"));
  }
}
