package net.bcsoft.com.Reddet.model;


import net.bcsoft.com.Reddet.exception.SpringReddetException;

import java.util.Arrays;

public enum VoteType {
        UP_VOTE(1),
        DOWN_VOTE(-1);
        private int direction;

    VoteType(int direction) {
    }

    public static VoteType lookup(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new SpringReddetException("Vote not found"));
    }

       public Integer getDirection() {
            return direction;
        }
}
