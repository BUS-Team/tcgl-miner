package br.com.fluentesoftware.tcglminer.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BusTypeTest {
    private BusType busType;

    @Before
    public void setUp() throws Exception {
        this.busType = new BusType("Linhas Convencionais", 0.1f);
    }

    @Test
    public void testGetBuscarlinha() throws Exception {
        assertEquals("Linhas Convencionais", this.busType.getBuscarlinha());
    }

    @Test
    public void testGetRnd() throws Exception {
        assertEquals(0.1f, this.busType.getRnd(), 0.001f);
    }
}
