package shopping;

public class IntRange { //defines an IntRange
    private int start;
        private int end;
    
        public IntRange(int start, int end) { //defines start and end
            this.start = start;
            this.end = end;
        }

        public boolean contains(int value) { //checks if value is within range
            return value >= start && value < end;
        }
}
