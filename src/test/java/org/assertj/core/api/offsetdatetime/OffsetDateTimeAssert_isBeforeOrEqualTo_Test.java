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
 * Copyright 2012-2019 the original author or authors.
 */
package org.assertj.core.api.offsetdatetime;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

import org.assertj.core.api.AbstractOffsetDateTimeAssertBaseTest;
import org.assertj.core.api.OffsetDateTimeAssert;
import org.junit.jupiter.api.Test;

/**
 * @author Paweł Stawicki
 * @author Joel Costigliola
 * @author Marcin Zajączkowski
 */
public class OffsetDateTimeAssert_isBeforeOrEqualTo_Test extends AbstractOffsetDateTimeAssertBaseTest {

  @Override
  protected OffsetDateTimeAssert invoke_api_method() {
    return assertions.isBeforeOrEqualTo(now).isBeforeOrEqualTo(tomorrow.toString());
  }

  @Override
  protected void verify_internal_effects() {
    verify(comparables).assertIsBeforeOrEqualTo(getInfo(assertions), getActual(assertions), now);
    verify(comparables).assertIsBeforeOrEqualTo(getInfo(assertions), getActual(assertions), tomorrow);
  }

  @Test
  public void should_fail_if_given_offsetdatetime_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertions.isBeforeOrEqualTo((OffsetDateTime) null))
      .withMessage("The OffsetDateTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_given_string_parameter_is_null() {
    assertThatIllegalArgumentException().isThrownBy(() -> assertions.isBeforeOrEqualTo((String) null))
      .withMessage("The String representing the OffsetDateTime to compare actual with should not be null");
  }

  @Test
  public void should_fail_if_given_string_parameter_cant_be_parsed() {
    assertThatThrownBy(() -> assertions.isBeforeOrEqualTo("not an OffsetDateTime")).isInstanceOf(DateTimeParseException.class);
  }

}
