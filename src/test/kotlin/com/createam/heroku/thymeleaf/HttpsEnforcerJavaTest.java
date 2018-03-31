package com.createam.heroku.thymeleaf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpsEnforcerJavaTest {

    private static final String TEST_SERVER_NAME = "createam-labs.herokuapp.com";
    private static final String TEST_REQUEST_URI = "/for-sure-not-exist";

    private HttpsEnforcer enforcer;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @Mock
    private FilterConfig mockedFilterConfig;

    @Before
    public void setUp() {
        enforcer = new HttpsEnforcer();
    }

    @Test
    public void shouldEnforceHttps_onHerokuOverHttp() throws IOException {
        when(request.getHeader("x-forwarded-proto")).thenReturn("http");
        when(request.getServerName()).thenReturn(TEST_SERVER_NAME);
        when(request.getRequestURI()).thenReturn("");

        enforcer.doFilter(request, response, chain);

        ArgumentCaptor<String> redirectCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).sendRedirect(redirectCaptor.capture());
        assertThat(redirectCaptor.getValue()).isEqualTo("https://" + TEST_SERVER_NAME);
    }

    @Test
    public void shouldNotEnforceHttps_onHerokuOverHttps() {
        when(request.getHeader("x-forwarded-proto")).thenReturn("https");
        when(request.getServerName()).thenReturn(TEST_SERVER_NAME);
        when(request.getRequestURI()).thenReturn(TEST_REQUEST_URI);

        enforcer.doFilter(request, response, chain);

        verifyZeroInteractions(response);
    }

    @Test
    public void shouldDoNothing_whenHeaderIsEmpty() {
        when(request.getHeader("x-forwarded-proto")).thenReturn(null);

        enforcer.doFilter(request, response, chain);

        verifyZeroInteractions(response);
    }

    @Test
    public void shouldDoNothing_overHttps() {
        when(request.getHeader("x-forwarded-proto")).thenReturn("https");

        enforcer.doFilter(request, response, chain);

        verifyZeroInteractions(response);
    }

    @Test
    public void shouldRedirectToTheSameUrl() throws IOException {
        when(request.getHeader("x-forwarded-proto")).thenReturn("http");
        when(request.getServerName()).thenReturn(TEST_SERVER_NAME);
        when(request.getRequestURI()).thenReturn(TEST_REQUEST_URI);

        enforcer.doFilter(request, response, chain);

        ArgumentCaptor<String> redirectCaptor = ArgumentCaptor.forClass(String.class);
        verify(response).sendRedirect(redirectCaptor.capture());
        assertThat(redirectCaptor.getValue()).isEqualTo("https://" + TEST_SERVER_NAME + TEST_REQUEST_URI);
    }

    @Test
    public void shouldDoNothing_onDestroy() {
        enforcer.destroy();

        verifyZeroInteractions(mockedFilterConfig);
    }

    @Test
    public void shouldInitWithFilterConfig() {
        enforcer.init(mockedFilterConfig);

        assertThat(enforcer.filterConfig).isEqualTo(mockedFilterConfig);
    }

    @Test
    public void filterConfigShouldBeNullBeforeInit() {
        assertThat(enforcer.filterConfig).isNull();
    }
}
