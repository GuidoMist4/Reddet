package net.bcsoft.com.Reddet.model;


public enum TipoVoto {
        UP_VOTE(1),
        DOWN_VOTE(-1);

        private int direction;

        TipoVoto (int direction) {
            this.direction = direction;
        }

        public int getDirection() {
            return direction;
        }
}
