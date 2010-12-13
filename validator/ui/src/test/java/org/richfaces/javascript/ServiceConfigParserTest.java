package org.richfaces.javascript;

import static org.junit.Assert.*;

import java.util.Map;

import javax.faces.FacesException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.richfaces.resource.ResourceKey;

import com.google.common.collect.Iterables;

public class ServiceConfigParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParseConfig() {
        Map<Class<?>, LibraryFunction> parseConfig = ClientServiceConfigParser.parseConfig("csv.xml");
        assertEquals(2, parseConfig.size());
        assertTrue(parseConfig.containsKey(String.class));
        LibraryFunction libraryFunction = parseConfig.get(String.class);
        assertEquals("stringConverter", libraryFunction.getName());
        ResourceKey resource = Iterables.getOnlyElement(libraryFunction.getResources());
        assertEquals("csv.js", resource.getResourceName());
        assertEquals("org.richfaces", resource.getLibraryName());
    }

    @Test(expected=FacesException.class)
    public void testParseBadConfig() {
        Map<Class<?>, LibraryFunction> parseConfig = ClientServiceConfigParser.parseConfig("badcsv.xml");
    }
    @Test()
    public void testParseNoConfig() {
        Map<Class<?>, LibraryFunction> parseConfig = ClientServiceConfigParser.parseConfig("non-exists-csv.xml");
        assertEquals(0, parseConfig.size());
    }
}
