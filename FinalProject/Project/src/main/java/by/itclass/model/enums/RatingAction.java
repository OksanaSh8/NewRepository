package by.itclass.model.enums;

import by.itclass.model.db.SQLRequest;

public enum RatingAction {
    UPP(SQLRequest.UPP_RATING_VALUE),
    DOWN(SQLRequest.DOWN_RATING_VALUE),
    OTHER(SQLRequest.OTHER_RATING_VALUE);

    private int value;

    RatingAction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
