package iu.edu.lukemeng.ducksservice.model;

public enum DuckType {
    MALLARD, REDHEAD, RUBBER, DECOY;

    @Override
    public String toString() {
        switch (this) {
            case MALLARD: return "Mallard";
            case REDHEAD: return "Redhead";
            case DECOY: return "Decoy";
            case RUBBER: return "Rubber";
            default: return "unspecified";
        }
    }


    public static DuckType toEnum(String value) {
        switch (value.toLowerCase()) {
            case "mallard": return DuckType.MALLARD;
            case "redhead": return DuckType.REDHEAD;
            case "decoy": return DuckType.DECOY;
            case "rubber": return DuckType.RUBBER;
            default: return null;
        }
    }
}
