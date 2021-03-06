package editor;

import editor.display.CharacterDisplay;

/**
 * This class represents the document being edited. Using a 2d array
 * to hold the document content is probably not a very good choice.
 * Fixing this class is the main focus of the exercise. In addition to
 * designing a better data model, you must add methods to do at least
 * basic editing: write and delete text, and moving the cursor
 *
 * Implement the following commands
 *
 * @author evenal
 */

public class Document {

    LineNode firstLine;
    LineNode lineHolder;
    LineNode lineSelected;
    CharacterDisplay display;

    public Document(CharacterDisplay display) {
        //sets up data structure for the lineNodes (lines).

        firstLine = new LineNode();
        lineHolder = new LineNode();
        firstLine.next = lineHolder;
        firstLine.prev = firstLine;
        lineHolder.prev = firstLine;
        lineSelected = firstLine;
        this.display = display;
    }

    private void updateDisplay() {
        // should be called at the end of the functionality
        // and should update the display
    }

    /*
     * The following methods are called from the actions. Decide on
     * the data structure(s) for Document first. Then finish these
     * methods
     */
    public void insertLine() {
        LineNode newLine = new LineNode(lineSelected, lineSelected.next);
        lineSelected.next = newLine;
        if (newLine.next != null) {
            newLine.next.prev = newLine;
        }
        lineSelected = newLine;
        updateDisplay();
    }

    public void insert(Character c) {
        lineSelected.addCharacter(c);
        updateDisplay();
    }

    public void deleteNext() {
    }

    public void deletePrev() {
        lineSelected.deletePrev();
        updateDisplay();
    }

    public void moveCursor(String direction) {
        switch (direction) {
            case "RIGHT":
            case "LEFT":
                lineSelected.moveCursor(direction);
                break;
            case "UP":
                lineSelected = lineSelected.prev;
                break;
            case "DOWN":
                if (lineSelected.next != null) {
                    lineSelected = lineSelected.next;
                }
                break;
        }
        updateDisplay();
    }

    public void print(){
        LineNode n = firstLine;
        while (n.next != null) {
            n.print();
            n = n.next;
        }
    }


    /**
     * This class represents the lines.
     * It holds the data structure for the characters and defines a start, end and cursor in the lines.
     * It have methods for deleting and inserting characters, moving the cursor left and right and printing the line
     * which iterate through the doubly linked list of charNodes.
     */
    private class LineNode {
        CharNode cursor;
        CharNode front;
        CharNode end;
        LineNode prev;
        LineNode next;

        private LineNode() {
            lineStart();
        }

        /**
         * The idea behind this constructor is if one makes a lines between some other lines it link it with them.
         * Then continuous with setting up the data structure for the charNodes.
         * @param prev
         * @param next
         */
        private LineNode(LineNode prev, LineNode next) {
            this.prev = prev;
            this.next = next;
            this.prev.next = this;
            this.next.prev = this;
            lineStart();
        }

        private void lineStart() {
            front = new CharNode();
            end = new CharNode();
            front.next = end;
            front.prev = front;
            end.prev = front;
            cursor = front;
        }

        private void addCharacter(char character) {
            CharNode newCharacter = new CharNode(character, cursor, cursor.next);
            cursor.next = newCharacter;
            newCharacter.next.prev = newCharacter;
            cursor = cursor.next;

        }

        private void moveCursor(String direction) {
            switch(direction) {
                case "LEFT":
                    cursor = cursor.prev;
                    break;
                case "RIGHT":
                    if (cursor.next != null) {
                        cursor = cursor.next;
                    }
                    break;
            }
        }

        private void deletePrev() {
            if (cursor != front) {
                cursor.prev.next = cursor.next;
                cursor.next.prev = cursor.prev;
                cursor = cursor.prev;
            }
        }

        private void print() {
            StringBuilder sb = new StringBuilder();
            CharNode n = front;
            while (n.next != null) {
                sb.append(n.data);
                n = n.next;
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * The charNode class represents the characters in a line.
     * The characters or CharNodes are connected with each other by referencing to previous and next characters/charNodes.
     */
    private class CharNode {
        char data;
        CharNode prev;
        CharNode next;

        private CharNode() {
            data = 0;
            prev = null;
            next = null;
        }

        private CharNode(char character, CharNode prev, CharNode next) {
            data = character;
            this.prev = prev;
            this.next = next;
        }
    }
}

