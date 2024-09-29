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
package org.assertj.tests.core.api.recursive.comparison;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.tests.core.util.AssertionsUtil.expectAssertionError;

import java.util.OptionalInt;

import org.assertj.tests.core.api.recursive.data.Person;
import org.junit.jupiter.api.Test;

class RecursiveComparisonAssert_isEqualTo_withErrorMessageForType_Test extends RecursiveComparisonAssert_isEqualTo_BaseTest {

  private static final String ERROR_MESSAGE_DESCRIPTION_FOR_TYPE = "- these types had overridden error messages:";

  @Test
  void should_be_able_to_set_custom_error_message_for_specific_type() {
    // GIVEN
    Person actual = new Person("Valera");
    actual.age = OptionalInt.of(15);
    Person expected = new Person("John");
    expected.age = OptionalInt.of(15);
    String message = "Name must be the same";
    Class<String> type = String.class;
    // WHEN
    AssertionError assertionError = expectAssertionError(() -> assertThat(actual).usingRecursiveComparison()
                                                                                 .withErrorMessageForType(message, type)
                                                                                 .isEqualTo(expected));
    // THEN
    then(assertionError).hasMessageContainingAll(message,
                                                 ERROR_MESSAGE_DESCRIPTION_FOR_TYPE,
                                                 "- " + type.getName());
  }

}
