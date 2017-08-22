package org.realityforge.gwt.appcache.server.gwtp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public final class MobileUserAgentProviderTest
{
    @DataProvider(name = "AgentMapping")
    public Object[][] getAgentMapping()
    {
        return new Object[][]{
                {UserAgents.FENNEC_USER_AGENT, "mobile"},
                {UserAgents.OPERA_USER_AGENT, "mobile"},
                {UserAgents.ANDROID_TABLET_USER_AGENT, "tablet"},
                {UserAgents.DESKTOP_USER_AGENT_SAFARI, "desktop"},
                {UserAgents.DESKTOP_USER_AGENT_CHROME, "desktop"},
        };
    }

    @Test(dataProvider = "AgentMapping")
    public void userAgentExtraction(final String userAgent, final String value)
    {
        final MobileUserAgentProvider provider = new MobileUserAgentProvider();
        assertEquals(provider.getPropertyName(), "gwtp.formfactor");
        final HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("User-Agent")).thenReturn(userAgent);
        assertEquals(provider.getPropertyValue(request), value);
    }
}
