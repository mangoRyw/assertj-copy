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
package org.assertj.core.error.future;

import static java.lang.String.format;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.future.ShouldBeCompleted.shouldBeCompleted;
import static org.assertj.core.error.future.Warning.WARNING;

import java.util.concurrent.CompletableFuture;

import org.assertj.core.internal.TestDescription;
import org.junit.jupiter.api.Test;

public class ShouldBeCompleted_create_Test {

  @Test
  public void should_create_error_message() {
    // WHEN
    String error = shouldBeCompleted(new CompletableFuture<>()).create(new TestDescription("TEST"));
    // THEN
    then(error).isEqualTo(format("[TEST] %n" +
                                 "Expecting%n" +
                                 "  <CompletableFuture[Incomplete]>%n" +
                                 "to be completed.%n%s",
                                 WARNING));
  }

}
