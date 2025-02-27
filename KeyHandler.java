
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed, upArrowPressed, downArrowPressed, rightArrowPressed, leftArrowPressed, shiftPressed, twoPressed;
    public boolean onePressed = true;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_1) {
            if (onePressed == false) {
                twoPressed = false;
                onePressed = true;
            } else if (onePressed == true) {
                onePressed = false;
            }
        }
        if (code == KeyEvent.VK_2) {
            if (twoPressed == false) {
                onePressed = false;
                twoPressed = true;
            } else if (twoPressed == true) {
                twoPressed = false;
            }
        }
        if (code == KeyEvent.VK_UP) {
            upArrowPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrowPressed = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrowPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrowPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
        if (code == KeyEvent.VK_UP) {
            upArrowPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downArrowPressed = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightArrowPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftArrowPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
    }
}
