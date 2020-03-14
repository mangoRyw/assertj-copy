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
 * Copyright 2012-2020 the original author or authors.
 */
package org.assertj.core.error;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldHaveToString.shouldHaveToString;
import static org.assertj.core.presentation.StandardRepresentation.STANDARD_REPRESENTATION;

import org.assertj.core.internal.TestDescription;
import org.junit.jupiter.api.Test;

public class ShouldHaveToString_create_Test {

  @Test
  public void should_create_error_message() {
    // GIVEN
    String actual = "c++";
    String expectedToString = "java";
    // WHEN
    String errorMessage = shouldHaveToString(actual, expectedToString).create(new TestDescription("TEST"),
                                                                              STANDARD_REPRESENTATION);
    // THEN
    then(errorMessage).isEqualTo(format("[TEST] %n" +
                                        "Expecting actual's toString() to return:%n" +
                                        "  <\"java\">%n" +
                                        "but was:%n" +
                                        "  <\"c++\">"));
  }

}
