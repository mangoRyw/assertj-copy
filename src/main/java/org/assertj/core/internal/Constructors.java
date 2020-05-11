package org.assertj.core.internal;

import static org.assertj.core.error.ShouldHaveParameter.shouldHaveParameter;

import java.lang.reflect.Constructor;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.error.ShouldBePublic;

/**
 * Reusable assertions for <code>{@link Constructor}</code>s.
 *
 * @author phx
 */
public class Constructors {

  private static final Constructors INSTANCE = new Constructors();
  private final Failures failures = Failures.instance();

  /**
   * Returns the singleton instance of this constructor.
   *
   * @return the singleton instance of this constructor.
   */
  public static Constructors instance() {
    return INSTANCE;
  }

  private static void assertNotNull(AssertionInfo info, Constructor actual) {
    Objects.instance().assertNotNull(info, actual);
  }

  /**
   * Verifies that the actual {@code Constructor} is public.
   *
   * @param info   contains information about the assertion.
   * @param actual the "actual" {@code Constructor}.
   * @throws AssertionError if {@code actual} is {@code null}.
   * @throws AssertionError if the actual {@code Constructor} is not public.
   */
  public <ACTUAL extends Constructor> void assertIsPublic(AssertionInfo info, Constructor actual) {
    assertModifier(info, actual, "PUBLIC");
  }

  /**
   * Verifies that the actual {@code Constructor} is private.
   *
   * @param info   contains information about the assertion.
   * @param actual the "actual" {@code Constructor}.
   * @throws AssertionError if {@code actual} is {@code null}.
   * @throws AssertionError if the actual {@code Constructor} is not private.
   */
  public <ACTUAL extends Constructor> void assertIsPrivate(AssertionInfo info, ACTUAL actual) {
    assertModifier(info, actual, "PRIVATE");
  }

  /**
   * Verifies that the actual {@code Constructor} is protected.
   *
   * @param info   contains information about the assertion.
   * @param actual the "actual" {@code Constructor}.
   * @throws AssertionError if {@code actual} is {@code null}.
   * @throws AssertionError if the actual {@code Constructor} is not protected.
   */
  public <ACTUAL extends Constructor> void assertIsProtected(AssertionInfo info, ACTUAL actual) {
    assertModifier(info, actual, "PROTECTED");
  }

  private String getModifier(Constructor actual) {
    switch (actual.getModifiers()) {
      case 1:
        return "PUBLIC";
      case 2:
        return "PRIVATE";
      case 4:
        return "PROTECTED";
      default:
        return null;
    }
  }

  private void assertModifier(AssertionInfo info, Constructor actual, String exp) {
    assertNotNull(info, actual);
    String modifier = getModifier(actual);
    Objects.instance().assertNotNull(info, modifier);
    if (modifier.equals(exp)) {
      return;
    }
    throw failures.failure(info, ShouldBePublic.shouldBePublic(actual, modifier));
  }

  /**
   * Verifies that the actual {@code Constructor} has the arguments classes.
   *
   * @param info      contains information about the assertion.
   * @param actual    the "actual" {@code Class}.
   * @param arguments arguments who to invoke the Constructor.
   * @throws AssertionError if {@code actual} is {@code null}.
   * @throws AssertionError if the actual {@code Constructor} do not contain these arguments.
   */
  public <ACTUAL extends Constructor> void hasArguments(AssertionInfo info, ACTUAL actual,
    Class<?>... arguments) {
    assertNotNull(info, actual);
    Class<?>[] parameterTypes = actual.getParameterTypes();
//    Class<?>[] missingParameter = new Class<?>[parameterTypes.length];
    if (arguments.length != parameterTypes.length) {
      throw failures.failure(info, shouldHaveParameter(actual, arguments, parameterTypes));
    }
    for (int i = 0; i < arguments.length; i++) {
      if (!parameterTypes[i].getName().equals(arguments[i].getName())) {
        throw failures.failure(info, shouldHaveParameter(actual, arguments, parameterTypes));
      }
    }
  }
}
