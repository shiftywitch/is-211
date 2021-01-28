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

    /** The doc is a list of lines */
    Line sentinel;
    Line currentLine;
    Line firstVisible;

    CharacterDisplay display;

    public Document(CharacterDisplay display) {
        sentinel = new Line();
        currentLine = new Line();
        firstVisible = currentLine;
        sentinel.next = currentLine;
        currentLine.next = sentinel;
        sentinel.prev = currentLine;
        currentLine.prev = sentinel;

        this.display = display;
    }

    public void print() {
        for (Line line = sentinel.next;
             line != sentinel;
             line = line.next) {
            line.print();
        }
    }

    private void updateDisplay() {
        int lineCount = 0;

        for (Line line = firstVisible;
             line != sentinel
             && lineCount < display.getHeight();
             line = line.next) {
            lineCount++;
            line.updateDisplay(lineCount);
        }
    }

    private void insertLine() {

    }

    public void insert(Character c) {
        currentLine.insert(c);
        updateDisplay();
    }

    public void deleteNext() {
        currentLine.deleteNext();
    }

    public void deletePrev() {
        currentLine.deletePrev();

    }

    /**
     * There is one instance of this class for every character in the
     * buffer. Each Line object contains a double linked list of
     * CharNodes (using the prev and next pointers in this class
     */
    private class CharNode {

        CharNode prev, next;
        char c;

        public CharNode() {
            c = 0;
            prev = this;
            next = this;
        }

        public CharNode(char c,
                        CharNode prev,
                        CharNode next) {
            this.c = c;
            this.prev = prev;
            this.next = next;
        }

        public void insert(char c) {
            CharNode n = new CharNode(c, this, next);
            this.next = n;
            n.next.prev = n;
        }

        private void deletePrev() {
            if (prev == currentLine.sentinel) {
                // careful....
            }
            else {
                prev = prev.prev;
                prev.next = this;
            }
        }

        private void deleteNext() {

        }
    }

    /**
     * Each Line object contains a double linked list of CharNode
     * objects, which hold the characters in the line.
     *
     * One of the lines contains the cursor. Logically the cursor is
     * always between two characters. This is not possible, so the
     * cursor field points to the character (CharNode object) just
     * behind the cursor. Characters are always inserted between the
     * cursor and the next node.
     */
    private class Line {

        CharNode sentinel;
        CharNode cursor;
        Line prev;
        Line next;

        public Line() {
            sentinel = new CharNode();
            cursor = sentinel;
            prev = this;
            next = this;
        }

        private void insert(char c) {
            assert cursor != null;
            cursor.insert(c);
            cursor = cursor.next;
        }

        private void deletePrev() {
            cursor.deletePrev();
        }

        private void deleteNext() {
            cursor.deleteNext();
        }

        private void print() {
            for (CharNode cn = sentinel.next;
                 cn != sentinel;
                 cn = cn.next) {
                System.out.format("%c", cn.c);
            }
        }

        private void updateDisplay(int line) {
            int col = 0;
            for (CharNode cn = sentinel.next;
                 cn != sentinel;
                 cn = cn.next) {
                if (cn == cursor)
                    display.displayCursor(cn.c, line, col);
                else
                    display.displayChar(cn.c, line, col);
                col++;
            }
        }
    }

}
