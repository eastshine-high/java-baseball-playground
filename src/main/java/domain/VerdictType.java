package domain;

/**
 * 판정 타입
 */
public enum VerdictType {
    STRIKE("스트라이크"),
    BALL("볼"),
    NOTHING("낫싱"),
    FOUR_BALL("포볼");

    private final String name;

    VerdictType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
