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
 * Copyright 2012-2021 the original author or authors.
 */
package org.assertj.core.condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenNullPointerException;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

public class VerboseConditionTest {

  private static Condition<String> verboseConditionWithoutTransformations() {
    return VerboseCondition.verbose(
        4,
        (String actual, Integer expected) -> actual.length() < expected,
        "word must be shorter than");
  }

  private static Condition<String> verboseConditionWithTransformations() {
    return VerboseCondition.verbose(
        4,
        (String actual, Integer expected) -> actual.length() < expected,
        "word must be shorter than",
        (i) -> i + " (maximum word length)",
        (s) -> String.format("%s (original word: %s)", s.length(), s));

  }
  

  @Test
  public static void test_verboseCondition_WithoutTransformations_maches_not_executed_description() {

    Condition<String> condition=verboseConditionWithoutTransformations();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4>");
  }

  @Test
  public static void test_verboseCondition_WithoutTransformations_maches_executed_description_when_matching() {
    Condition<String> condition=verboseConditionWithoutTransformations();
    assertThat(condition.matches("foo")).isTrue();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4>");
  }

  @Test
  public static void test_verboseCondition_WithoutTransformations_maches_executed_description_when_not_matching() {
    Condition<String> condition=verboseConditionWithoutTransformations();
    assertThat(condition.matches("foooo")).isFalse();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4> but was <foooo>");
  }
  
  @Test
  public static void test_verboseCondition_WitTransformations_maches_not_executed_description() {

    Condition<String> condition=verboseConditionWithTransformations();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4 (maximum word length)>");
  }

  @Test
  public static void test_verboseCondition_WithTransformations_maches_executed_description_when_matching() {
    Condition<String> condition=verboseConditionWithTransformations();
    assertThat(condition.matches("foo")).isTrue();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4 (maximum word length)>");
  }

  @Test
  public static void test_verboseCondition_WithTransformations_maches_executed_description_when_not_matching() {
    Condition<String> condition=verboseConditionWithTransformations();
    assertThat(condition.matches("foooo")).isFalse();
    assertThat(condition.toString()).isEqualTo("word must be shorter than <4 (maximum word length)> but was <5 (original word: foooo)>");
  }

  @Test
  public static void test_matchesBiPredicate_null_throws_exception() {
  
  	thenNullPointerException().isThrownBy(() -> {
   VerboseCondition.verbose(
      null,
      null);}).withMessage("The matchesBiPredicate should not be null");
  }
}
