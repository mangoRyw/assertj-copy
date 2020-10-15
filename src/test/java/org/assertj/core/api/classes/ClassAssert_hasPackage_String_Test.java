package org.assertj.core.api.classes;

import static org.mockito.Mockito.verify;

import org.assertj.core.api.ClassAssert;
import org.assertj.core.api.ClassAssertBaseTest;
import org.junit.jupiter.api.DisplayName;

/**
 * Tests for <code>{@link ClassAssert#hasPackage(String)}</code>.
 *
 * @author Matteo Mirk
 */
@DisplayName("Classes hasPackage")
class ClassAssert_hasPackage_String_Test extends ClassAssertBaseTest {

  static final String PACKAGE = "org.assertj.core.api";

  @Override
  protected ClassAssert invoke_api_method() {
    return assertions.hasPackage(PACKAGE);
  }

  @Override
  protected void verify_internal_effects() {
    verify(classes).assertHasPackage(getInfo(assertions), getActual(assertions), PACKAGE);
  }
}
