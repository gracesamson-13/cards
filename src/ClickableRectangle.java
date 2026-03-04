import processing.core.PApplet;

public class ClickableRectangle {
    int x;
    int y;
    int width;
    int height;
    String buttonText = "";

    boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width &&
               mouseY >= y && mouseY <= y + height;
    }

    public void draw(PApplet app) {
        app.rect(x, y, width, height);
        app.fill(0);
        app.textAlign(app.CENTER, app.CENTER);
        app.text(buttonText, x + width / 2, y + height / 2);
    } 
      

}
