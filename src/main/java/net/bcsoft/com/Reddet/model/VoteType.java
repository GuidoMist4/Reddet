package net.bcsoft.com.Reddet.model;


public enum VoteType {
        UP_VOTE(1),
        DOWN_VOTE(-1);

        private int direction;

        VoteType(int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return direction;
        }
}
