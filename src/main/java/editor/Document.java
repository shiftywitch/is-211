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
    LineNode lineSelected;
    CharacterDisplay display;

    public Document(CharacterDisplay display) {
        //set up data structure

        firstLine = new LineNode();
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
        updateDisplay();
    }

    public void insert(Character c) {
        firstLine.addCharacter(c);
        updateDisplay();
    }

    public void deleteNext() {
    }

    public void deletePrev() {
        firstLine.deleteCharacter();
        updateDisplay();
    }

    public void moveCursor(String direction) {
        System.out.println(direction);
        updateDisplay();
    }

    public void print(){
        lineSelected.print();
    }

    /**
    private void updateDisplay(int line) {
        // for all visible characterso
        // show them in the rightplace
        display.displayChar(c, line, column);

        // and make the cursor stand out a little
        display.displayCursor(c, line, column);
    }
     **/

    private class LineNode {
        CharNode cursor;
        CharNode front;
        CharNode end;
        LineNode prev;
        LineNode next;

        private LineNode() {
            front = new CharNode();
            end = new CharNode();
            front.next = end;
            front.prev = front;
            end.prev = front;
            cursor = front;
        }

        private void addCharacter(char character) {
            CharNode newCharacter;
            newCharacter = new CharNode(character, cursor.next, cursor);
            cursor.next = newCharacter;
            newCharacter.next.prev = newCharacter;
            cursor = cursor.next;

        }

        private void deleteCharacter() {
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

        private CharNode(char character, CharNode next, CharNode prev) {
            data = character;
            this.prev = prev;
            this.next = next;
        }
    }
}

