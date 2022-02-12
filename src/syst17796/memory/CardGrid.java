package syst17796.memory;

public final class CardGrid extends Deck {
    /**Card[][] does not need to exist with the getCard method/no 2d array approach. SetCard method
     * required for this approach. Constructor could remove lines 32 - 3. CardGrid would just be
     * an Array that's viewed as a 2d array/grid by the user.
     * 
     * private Card[][] cardGrid;
     */
    private final int numOfRows;
    private final int numOfColumns;

    /**
     * Always shuffles (possibly a problem to be fixed)
     *
     * @param row
     * @param column
     */
    public CardGrid(int row, int column) {
        super();
        this.numOfRows=row;
        this.numOfColumns=column;
    }


    public Card getCard(int row, int col) {
        return super.getCard(row * numOfColumns + col);
    }

    public void displayVisibleGrid() {

        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfColumns; j++) {
                System.out.print(getCard(i, j).toString() + " ");
            }
            System.out.println();
        }
    }

    public int getNumOfRows() {
        return numOfRows;
    }

    public int getNumOfColumns() {
        return numOfColumns;
    }
}