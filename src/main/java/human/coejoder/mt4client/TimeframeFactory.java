package human.coejoder.mt4client;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TimeframeFactory {
    INSTANCE;

    private static final String REGEX = "(\\d+)([mhdwn]+)";
    private static final int GROUP_COUNT = 2;
    private static final Pattern PATTERN = Pattern.compile(REGEX, Pattern.CASE_INSENSITIVE);
    private static final String PREFIX = "PERIOD_";
    private static final String PERIOD_CURRENT = "0";

    /**
     * Parses a timeframe string.
     *
     * @param strTimeframe A timeframe string, eg. '10m', '4h', '1d', '1w', '1mn' etc.
     * @return A valid {@link Timeframe} object, or empty if timeframe is invalid or parsing fails.
     */
    public Optional<Timeframe> build(String strTimeframe) {
        if (PERIOD_CURRENT.equals(strTimeframe)) {
            return Optional.of(StandardTimeframe.PERIOD_CURRENT);
        }
        Matcher m = PATTERN.matcher(strTimeframe);
        if (m.matches() && m.groupCount() == GROUP_COUNT) {
            String name = PREFIX + m.group(2).toUpperCase() + m.group(1);
            return Optional.ofNullable(StandardTimeframe.fromName(name)
                    .orElse(NonStandardTimeframe.fromName(name)
                            .orElse(null)));
        }
        return Optional.empty();
    }
}
