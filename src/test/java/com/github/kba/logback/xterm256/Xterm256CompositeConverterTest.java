package com.github.kba.logback.xterm256;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

public class Xterm256CompositeConverterTest {

	private static final Logger log = LoggerFactory.getLogger(Xterm256CompositeConverterTest.class);

	@Test
	public void testParseColorTriple() throws Exception
	{
		assertEquals("2;38;5;100;48;5;200", Xterm256CompositeConverter.parseColorTriple("100-2-200"));
		assertEquals("2;38;5;100", Xterm256CompositeConverter.parseColorTriple("100-2"));
		assertEquals("38;5;100", Xterm256CompositeConverter.parseColorTriple("100"));
	}

	@Test
	public void testLogging()
	{
		log.trace("trace should be gray italic");
		log.debug("debug should be normal");
		log.info("info should be green");
		log.warn("warn should be yellow");
		log.error("error should be white on red");
		log.debug(MarkerFactory.getMarker("XTERM:3"), "This should be yellow");
	}

}
