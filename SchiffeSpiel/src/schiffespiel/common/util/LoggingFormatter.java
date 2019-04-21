package schiffespiel.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formatter für den {@link LoggingHandler}
 * @author Niklas
 */
class LoggingFormatter extends Formatter {

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Override
	public String format(LogRecord record) {
		
		String result = "[" + LocalDateTime.now().format(dateTimeFormatter) + "] [" + record.getLevel()
		+ " | " + record.getSourceClassName() + "] " + record.getMessage() + "\n";
		
		Throwable thrown = record.getThrown();
		if (thrown != null) result += thrown + "\n";
		
		return result;
	}
	
}
