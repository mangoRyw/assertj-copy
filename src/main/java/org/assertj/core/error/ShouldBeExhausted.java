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

import java.util.Iterator;

import org.assertj.core.util.VisibleForTesting;

/**
 * Creates an error message indicating that an assertion that verifies that an {@link Iterator} has no more elements
 * failed.
 * 
 * @author Stephan Windmüller
 */
public class ShouldBeExhausted extends BasicErrorMessageFactory {

  @VisibleForTesting
  public static final String SHOULD_BE_EXHAUSTED = "%nExpecting iterator to be exhausted.";

  /**
   * Creates a new <code>{@link ShouldBeExhausted}</code>.
   *
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeExhausted(Iterator<?> actual) {
	return new ShouldBeExhausted(actual);
  }

  private ShouldBeExhausted(Iterator<?> actual) {
    super(SHOULD_BE_EXHAUSTED, actual);
  }

}
