package org.assertj.core.architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.AccessTarget;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.properties.HasName.AndFullName;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import java.net.URL;
import java.util.Optional;
import java.util.function.Predicate;

import static com.tngtech.archunit.base.DescribedPredicate.alwaysTrue;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.architecture.ArchitectureTest.ROOT_PACKAGE;

@AnalyzeClasses(packages = ROOT_PACKAGE)
public class ArchitectureTest {
  static final String ROOT_PACKAGE = "org.assertj.core.";

  private static final String ANNOTATIONS_LAYER = "annotations";
  private static final String API_LAYER = "api";
  private static final String CONDITION_LAYER = "condition";
  private static final String CONFIGURATION_LAYER = "configuration";
  private static final String DATA_LAYER = "data";
  private static final String DESCRIPTION_LAYER = "description";
  private static final String ERROR_LAYER = "error";
  private static final String EXTRACTOR_LAYER = "extractor";
  private static final String GROUPS_LAYER = "groups";
  private static final String INTERNAL_LAYER = "internal";
  private static final String MATCHER_LAYER = "matcher";
  private static final String PRESENTATION_LAYER = "presentation";
  private static final String UTIL_LAYER = "util";

  private static final String API_PACKAGE = ROOT_PACKAGE + API_LAYER;

  //@formatter:off
  @ArchTest
  private static final ArchRule layerRule = layeredArchitecture()
    .layer(ANNOTATIONS_LAYER).definedBy(ROOT_PACKAGE + ANNOTATIONS_LAYER)
    .layer(API_LAYER).definedBy(API_PACKAGE)
    .layer(CONDITION_LAYER).definedBy(ROOT_PACKAGE + CONDITION_LAYER)
    .layer(CONFIGURATION_LAYER).definedBy(ROOT_PACKAGE + CONFIGURATION_LAYER)
    .layer(DATA_LAYER).definedBy(ROOT_PACKAGE + DATA_LAYER)
    .layer(DESCRIPTION_LAYER).definedBy(ROOT_PACKAGE + DESCRIPTION_LAYER)
    .layer(ERROR_LAYER).definedBy(ROOT_PACKAGE + ERROR_LAYER)
    .layer(EXTRACTOR_LAYER).definedBy(ROOT_PACKAGE + EXTRACTOR_LAYER)
    .layer(GROUPS_LAYER).definedBy(ROOT_PACKAGE + GROUPS_LAYER)
    .layer(INTERNAL_LAYER).definedBy(ROOT_PACKAGE + INTERNAL_LAYER + ".(*)..")
    .layer(MATCHER_LAYER).definedBy(ROOT_PACKAGE + MATCHER_LAYER)
    .layer(PRESENTATION_LAYER).definedBy(ROOT_PACKAGE + PRESENTATION_LAYER)
    .layer(UTIL_LAYER).definedBy(ROOT_PACKAGE + UTIL_LAYER)
    .ignoreDependency(isTest(), alwaysTrue())

    .whereLayer(INTERNAL_LAYER).mayOnlyBeAccessedByLayers(
      ANNOTATIONS_LAYER,
      API_LAYER,
      CONDITION_LAYER,
      CONFIGURATION_LAYER,
      DATA_LAYER,
      DESCRIPTION_LAYER,
      ERROR_LAYER,
      EXTRACTOR_LAYER,
      GROUPS_LAYER,
      MATCHER_LAYER,
      PRESENTATION_LAYER);
      // UTIL_LAYER can't access INTERNAL_LAYER
  //@formatter:on

  private static DescribedPredicate<AndFullName> isTest() {
    return new DescribedPredicate<AndFullName>("is test") {

      @Override
      public boolean apply(AndFullName fullName) {
        return isTestPredicate().test(fullName);
      }
    };
  }

  private static Predicate<AndFullName> isTestPredicate() {
    return fullName -> getFile(getOwner(fullName)).map(f -> f.contains("target/test-classes")).orElse(false);
  }

  private static Optional<String> getFile(AndFullName fullName) {
    String urlStr = '/' + fullName.getFullName().replace(".", "/") + ".class";
    URL url = ArchitectureTest.class.getResource(urlStr);
    return (url != null) && (url.getFile() != null) ? of(url.getFile()) : empty();
  }

  private static AndFullName getOwner(AndFullName fullName) {
    // find the owner JavaClass
    if (fullName instanceof JavaMember) {
      // it's a well-known member (field, constructor, method, static initializer)
      fullName = ((JavaMember) fullName).getOwner();
    } else if (fullName instanceof AccessTarget) {
      // The target (field, constructor, method) can't be precisely defined from bytecode.
      // That could, for example, be the case for multiple inheritance. (see AccessTarget javadoc for further details)
      fullName = ((AccessTarget) fullName).getOwner();
    }
    // else fullName is a JavaClass

    // here fullName is always a JavaClass, but it's better to depend on the interface AndFullName
    return fullName;
  }
}
