import processing.core.PApplet;

public class App extends PApplet {

    CardGame cardGame = new GinRummy();
    private int timer;

    public static void main(String[] args) {
        PApplet.main("App");
    }
    @Override
    public void settings() {
        size(600, 600);   
        
    }

    @Override
    public void draw() {
        background(185, 143, 178);
        // Draw player hands
        for (int i = 0; i < cardGame.playerOneHand.getSize(); i++) {
            Card card = cardGame.playerOneHand.getCard(i);
            if (card != null) {
                card.draw(this);
            }
        }
        // Draw computer hand
        for (int i = 0; i < cardGame.playerTwoHand.getSize(); i++) {
            Card card = cardGame.playerTwoHand.getCard(i);
            if (card != null) {
                card.draw(this);
            }
        }

        //Gin Rummy Text
        noStroke();
        fill(189, 104, 177);
        rect(370, 200, 200, 60);
        fill(60, 25, 55);
        strokeWeight(1);
        textAlign(CENTER, CENTER);
        textSize(36);
        text("GIN RUMMY", 320, 72, 300, 300);

        //gin rummy instructions
        noStroke();
        fill(189, 104, 177);
        rect(370, 260, 200, 100);
        fill(85, 25, 55);
        strokeWeight(1);
        textAlign(CENTER, CENTER);
        textSize(20);
        text("GOAL:", 320, 80, 300, 350);
        textSize(16);
        text("Draw or discard your cards", 320, 130, 300, 300);
        text("until you get 3 of the same.", 320, 150, 300, 300);
        text("Click Knock to win. ", 320, 170, 300, 300);
        text("Try and beat the computer!", 320, 190, 300, 300);
        
        // Draw draw button
        textSize(16);
        fill(160, 206, 222);
        cardGame.drawButton.draw(this);
        fill(0);
        strokeWeight(5);
        textAlign(CENTER, CENTER);
        text("Draw", cardGame.drawButton.x + cardGame.drawButton.width / 2, cardGame.drawButton.y + cardGame.drawButton.height / 2);


        fill(236, 241, 179);
        cardGame.knockButton.draw(this);

        fill(0);
        textAlign(CENTER, CENTER);
        text("Knock",cardGame.knockButton.x + cardGame.knockButton.width / 2, cardGame.knockButton.y + cardGame.knockButton.height / 2);


        // Display current player
        fill(0);
        textSize(16);
        text("Player Turn: " + cardGame.getCurrentPlayer(), width / 2, 20);

        // Display deck size
        text("Card deck Size: " + cardGame.getDeckSize(), width / 2, height - 20);

    
        // Display last played card
        if (cardGame.getLastPlayedCard() != null) {
            cardGame.getLastPlayedCard().setPosition(width / 2 - 40, height / 2 - 60, 80, 120);
            cardGame.getLastPlayedCard().draw(this);
        }
        if (cardGame.getCurrentPlayer() == "COMPUTER") {
            fill(0);
            textSize(16);
            text("Computer is planning move...", width / 2, height / 2 + 80);
            timer++;
            if (timer == 100) {
                cardGame.handleComputerTurn();
                timer = 0;
            }
        }

        cardGame.drawChoices(this);
    
        fill(0);
        textSize(20);
        textAlign(CENTER, CENTER);
        text(cardGame.gameMessage, 200, height / 2);
        
        fill(181, 5, 79);
        textSize(20);
        text("Click 'R' to Restart", 155, 343);
    }

    @Override
    public void mousePressed() {
        cardGame.handleDrawButtonClick(mouseX, mouseY);
        cardGame.handleCardClick(mouseX, mouseY);
        cardGame.handleKnockButtonClick(mouseX, mouseY);
        cardGame.handleDiscardButtonClick(mouseX, mouseY);

    }

    @Override
    public void keyPressed() {
    if (key == 'R' || key == 'r') {
        cardGame.restartGame();
    }
}

}

