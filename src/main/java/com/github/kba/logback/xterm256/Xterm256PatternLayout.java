package com.github.kba.logback.xterm256;

import ch.qos.logback.classic.PatternLayout;

public class Xterm256PatternLayout extends PatternLayout {
	static
	{
		PatternLayout.defaultConverterMap.put(
				"highlightLevel", Highlighting256CompositeConverter.class.getName());
	}

}
