logback-xterm256
================

## Configuration

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="com.github.kba.logback.xterm256.Xterm256PatternLayout">
            <Pattern>%highlightLevel(%d - %-5level %logger{0}:%L - %m%n){15-1-1}</Pattern>
        </layout>
    </appender>
    <root level="${log-level:-TRACE}">
        <appender-ref ref="CONSOLE"/>
    </root>
</configuration>
```

## Highlight by Logging Level

Everything enclosed in `%highlightLevel(...)` will be highlighted according to Level.

## Highlight by marker

```java
log.debug(Xterm256Constants.HIGHLIGHT, "I will be rendered very colorful");
```

## Highlight by generic marker

Marker must contain `XTERM:TRIPLE` where `TRIPLE` is of the form `foregroundColor-SGR-backgroundColor` where

* `foregroundColor` and `backgroundColor` are Xterm colors in the range of [0 .. 256]
* `SGR` is an integer, see [list of SGR codes](http://www.vt100.net/docs/vt510-rm/SGR)
