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
package org.assertj.core.api;

import java.time.temporal.Temporal;
import org.assertj.core.data.TemporalOffset;

public class TemporalAssert extends AbstractTemporalAssert<TemporalAssert, Temporal> {

  public TemporalAssert(Temporal actual) {
    super(actual, TemporalAssert.class);
  }

  @Override
  public Temporal parse(String temporalAsString) {
    throw new UnsupportedOperationException("This is not supported because there is no unique String representation of Temporal, this is available in concrete assertion temporal class like ZonedDateTimeAssert");
  }

  @Override
  public TemporalAssert isCloseTo(String otherAsString, TemporalOffset<? super Temporal> offset) {
    throw new UnsupportedOperationException("This is not supported because there is no unique String representation of Temporal, this is available in concrete assertion temporal class like ZonedDateTimeAssert");
  }
}
