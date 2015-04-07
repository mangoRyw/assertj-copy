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
package org.assertj.core.internal.urls;

import org.assertj.core.internal.UrisBaseTest;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.util.FailureMessages.actualIsNull;

/**
 * Tests for <code>{@link org.assertj.core.internal.Uris#assertHasFragment(org.assertj.core.api.AssertionInfo, java.net.URI, String)}  </code>.
 *
 * @author Alexander Bischof
 */
public class Uris_assertHasFragment_Test extends UrisBaseTest {

    @Test
    public void should_fail_if_actual_is_null() {
        thrown.expectAssertionError(actualIsNull());
        uris.assertHasFragment(info, null, "http://www.helloworld.org/index.html#print");
    }

    @Test
    public void should_fail_if_actual_is_not_null_but_expected_is_null() throws URISyntaxException {
        thrown.expectAssertionError("expected:<null> but was:<\"print\">");
        uris.assertHasFragment(info, new URI("http://www.helloworld.org/index.html#print"), null);
    }

    @Test
    public void should_throw_error_if_actual_uri_has_no_fragment() throws URISyntaxException {
        thrown.expectNullPointerException(null);
        uris.assertHasFragment(info, new URI("http://www.helloworld.org/index.html"), "print");
    }

    @Test
    public void should_pass_if_actual_uri_has_no_fragment_and_given_is_null() throws URISyntaxException {
        uris.assertHasFragment(info, new URI("http://www.helloworld.org/index.html"), null);
    }

    @Test
    public void should_pass_if_actual_uri_has_the_given_authority_with_pages() throws URISyntaxException {
        uris.assertHasFragment(info, new URI("http://www.helloworld.org/pages/index.html#print"), "print");
    }

    @Test
    public void should_throw_error_if_actual_uri_has_no_scheme() throws URISyntaxException {
        thrown.expectNullPointerException(null);
        uris.assertHasFragment(info, new URI("helloworld.org/pages"), "helloworld.org");
    }

    @Test
    public void should_throw_error_if_urisyntax_is_not_valid() throws URISyntaxException {
        thrown.expect(URISyntaxException.class);
        uris.assertHasFragment(info, new URI("http://finance.yahoo.com/q/h?s=^IXIC"), "http");
    }
}
