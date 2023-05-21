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
package org.assertj.core.tests.java17;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldBeRecord.shouldNotBeRecord;
import static org.assertj.core.error.ShouldHaveRecordComponents.shouldHaveRecordComponents;
import static org.assertj.core.util.Sets.set;

import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * @author Louis Morgan
 */
class Assertions_assertThat_with_Record_Test {

  @Test
  void isRecord_should_pass_if_actual_is_a_record() {
    // WHEN/THEN
    assertThat(MyRecord.class).isRecord();
  }

  @Test
  void isNotRecord_should_fail_if_actual_is_a_record() {
    // WHEN
    Throwable thrown = catchThrowable(() -> assertThat(MyRecord.class).isNotRecord());
    // THEN
    then(thrown).isInstanceOf(AssertionError.class)
                .hasMessage(shouldNotBeRecord(MyRecord.class).create());
  }

  @Test
  void hasRecordComponents_should_pass_if_record_has_expected_component() {
    // WHEN/THEN
    assertThat(MyRecord.class).hasRecordComponents("componentOne");
  }

  @Test
  void hasRecordComponents_should_pass_if_record_has_expected_components() {
    // WHEN/THEN
    assertThat(MyRecord.class).hasRecordComponents("componentOne", "componentTwo");
  }

  @Test
  void hasRecordComponents_should_fail_if_record_components_are_missing() {
    // WHEN
    Throwable thrown = catchThrowable(() -> assertThat(MyRecord.class).hasRecordComponents("componentOne", "missing"));
    // THEN
    then(thrown).isInstanceOf(AssertionError.class)
                .hasMessage(shouldHaveRecordComponents(MyRecord.class,
                                                       set("componentOne", "missing"),
                                                       Set.of("missing")).create());
  }

  record MyRecord(String componentOne, String componentTwo) {
  }

}
