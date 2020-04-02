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
package org.assertj.core.api.abstract_; // Make sure that package-private access is lost

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractStandardSoftAssertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

/**
 * This tests that classes extended from {@link AbstractStandardSoftAssertions} will have access to the list of
 * collected errors that the various proxies have collected.
 */
public class SoftAssertionsErrorsCollectedTest {
  private final Object objectForTesting = null;
  private final SoftAssertions softly = new SoftAssertions();

  @Test
  public void return_empty_list_of_errors() {
    softly.assertThat(objectForTesting).isNull(); // No errors to collect
    assertThat(softly.errorsCollected()).isEmpty();
    assertThat(softly.errorsCollected()).isEqualTo(softly.assertionErrorsCollected());
  }

  @Test
  public void returns_nonempty_list_of_errors() {
    softly.assertThat(objectForTesting).isNotNull(); // This should allow something to be collected
    assertThat(softly.errorsCollected()).hasAtLeastOneElementOfType(Throwable.class);
    assertThat(softly.errorsCollected()).isEqualTo(softly.assertionErrorsCollected());
  }
}
