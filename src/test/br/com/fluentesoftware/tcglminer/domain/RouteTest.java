package br.com.fluentesoftware.tcglminer.domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class RouteTest {
    private Route route;
    @Before
    public void setUp() throws Exception {
       this.route = new Route("305", "UEL");
    }

    @Test
    public void testGetCode() throws Exception {
        assertEquals("305", this.route.getCode());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("UEL", this.route.getName());
    }
}