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
 * Copyright 2012-2023 the original author or authors.
 */
package org.assertj.core.api.abstract_;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Disabled("only run on demand")
@Tag("performance")
@Tag("flaky")
class AbstractAssert_withFailMessage_performance_Test {

  @Test
  void withFailMessage_performance_improved_test() {
    int i = 1000000;
    while (i != 0) {
      AtomicReference<String> actual = new AtomicReference<>("foo");
      try {
        assertThat(actual).withFailMessage(() -> {
          return "error";
        }).hasValue("foo");
      } catch (AssertionError e) {

      }
      --i;
    }
  }

  @Test
  void withFailMessage_performance_test() {
    int i = 1000000;
    while (i != 0) {
      AtomicReference<String> actual = new AtomicReference<>("foo");
      try {
        String error = "error";
        assertThat(actual).withFailMessage(error).hasValue("foo");
      } catch (AssertionError e) {

      }
      --i;
    }
  }

}
