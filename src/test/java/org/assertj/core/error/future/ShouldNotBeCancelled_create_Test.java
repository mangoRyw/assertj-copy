/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.error.future;

import org.assertj.core.internal.TestDescription;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.error.future.ShouldNotBeCancelled.shouldNotBeCancelled;

public class ShouldNotBeCancelled_create_Test {

  @Test
  public void should_create_error_message() throws Exception {
    CompletableFuture future = new CompletableFuture();
    future.cancel(true);

    String error = shouldNotBeCancelled(future).create(new TestDescription("TEST"));

    assertThat(error).isEqualTo("[TEST] \n" +
        "Expecting\n" +
        "  <java.util.concurrent.CompletableFuture[Cancelled]>\n" +
        "not to be cancelled");
  }

}
