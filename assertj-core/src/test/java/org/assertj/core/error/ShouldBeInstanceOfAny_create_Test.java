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
package org.assertj.core.error;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeInstanceOfAny.shouldBeInstanceOfAny;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;

import java.io.File;
import java.util.regex.Pattern;
import org.assertj.core.internal.TestDescription;
import org.assertj.core.presentation.StandardRepresentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link ShouldBeInstanceOfAny#create(org.assertj.core.description.Description, org.assertj.core.presentation.Representation)}</code>.
 *
 * @author Alex Ruiz
 */
class ShouldBeInstanceOfAny_create_Test {

  @BeforeEach
  public void setUp() {}

  @Test
  void should_create_error_message() {
    // GIVEN
    Class<?>[] types = { File.class, Pattern.class };
    ErrorMessageFactory factory = shouldBeInstanceOfAny("Yoda", types);
    // WHEN
    String message = factory.create(new TestDescription("Test"), new StandardRepresentation());
    // THEN
    then(message).isEqualTo(format("[Test] %n" +
                                   "Expecting actual:%n" +
                                   "  \"Yoda\"%n" +
                                   "to be an instance of any of:%n" +
                                   "  [java.io.File, java.util.regex.Pattern]%n" +
                                   "but was instance of:%n" +
                                   "  java.lang.String"));
  }

  @Test
  void should_create_error_message_with_stack_trace_for_throwable() {
    // GIVEN
    IllegalArgumentException throwable = new IllegalArgumentException("Not in a list");
    Class<?>[] types = { NullPointerException.class, IllegalStateException.class };
    // WHEN
    String message = shouldBeInstanceOfAny(throwable, types).create();
    // THEN
    then(message).isEqualTo(format("%nExpecting actual throwable to be an instance of any of the following types:%n" +
                                   "  [java.lang.NullPointerException, java.lang.IllegalStateException]%n" +
                                   "but was:%n" +
                                   "  %s", STANDARD_REPRESENTATION.toStringOf(throwable)));

  }
}
