package by.itclass.model.enums;

public class EnumManager {
    public static NewsAction getKindNewsAction(String action) {
        try {
            return NewsAction.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NewsAction.OTHER;
        }
    }

    public static RatingAction getKindRatingAction(String action) {
        try {
            return RatingAction.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return RatingAction.OTHER;
        }
    }
}
