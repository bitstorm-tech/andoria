import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender

import static ch.qos.logback.classic.Level.DEBUG

def logPattern = "%d{HH:mm:ss.SSS} %level %logger - %msg%n"

appender("CONSOLE_APPENDER", ConsoleAppender) {
	encoder(PatternLayoutEncoder) {
		pattern = logPattern
	}
}

appender("FILE_APPENDER", FileAppender) {
	file = "client-log.txt"
	append = false
	encoder(PatternLayoutEncoder) {
		pattern = logPattern
	}
}

root(DEBUG, ["CONSOLE_APPENDER", "FILE_APPENDER"])