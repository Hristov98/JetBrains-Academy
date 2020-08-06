package analyzer;

public class Pattern {
    private final int priority;
    private final String pattern;
    private final String fileType;

    public Pattern(int priority, String pattern, String fileType) {
        this.priority = priority;
        this.pattern = pattern.substring(1, pattern.lastIndexOf("\""));
        this.fileType = fileType.substring(1, fileType.lastIndexOf("\""));
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getFileType() {
        return fileType;
    }
}
