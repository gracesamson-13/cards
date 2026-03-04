import processing.core.PApplet;

public class GinRummy extends CardGame {
  
   
    ClickableRectangle discardButton;
    int discardButtonX = 350;
    int discardButtonY = 400;
    int discardButtonWidth = 100;
    int discardButtonHeight = 35;

    ClickableRectangle knockButton;
    int knockButtonX = 100;
    int knockButtonY = 400;
    int knockButtonWidth = 100;
    int knockButtonHeight = 35;

    public GinRummy() {
    discardButton = new ClickableRectangle();
    discardButton.x = 400;
    discardButton.y = 400;
    discardButton.width = 100;
    discardButton.height = 35;

    // Initialize knock button
    int knockButtonX = 100;
    int knockButtonY = 400;
    int knockButtonWidth = 100;
    int knockButtonHeight = 35;

    knockButton = new ClickableRectangle();
    knockButton.x = knockButtonX;
    knockButton.y = knockButtonY;
    knockButton.width = knockButtonWidth;
    knockButton.height = knockButtonHeight;

            //initialize discard button
    discardButton = new ClickableRectangle();
    discardButton.x = 400;
    discardButton.y = 400;
    discardButton.width = 100;
    discardButton.height = 35;

        // Initialize knock button
    knockButton = new ClickableRectangle();
    knockButton.x = knockButtonX;
    knockButton.y = knockButtonY;
    knockButton.width = knockButtonWidth;
    knockButton.height = knockButtonHeight;

        initializeGame();
        dealCards(9);
    }


    @Override 
    public void drawChoices(PApplet app) {
//discard text

     app.fill(201, 232, 192);
     discardButton.draw(app);
     // draw discard button 
        app.fill(0);
        discardButton.draw(app);
     app.noStroke();
     app.fill(201, 232, 192);
    app.rect(400,400,100,35);

     app.textSize(16);
      app.noStroke();
      app.fill(0);
      app.text("Discard", 450, 417);

    }

    //     knockButton.draw(app);
    //    //Draw knock button
    //     app.fill(236, 241, 179);
    //     app.rect(100,400,100,35);
    //     app.fill(0);
    //     app.text("Knock", knockButton.x + knockButton.width / 2, knockButton.y + knockButton.height / 2);
    // }
    
   public void handleDrawButtonClick(int mouseX, int mouseY) {
        if (drawButton.isClicked(mouseX, mouseY) && playerOneTurn) {
            drawCard(playerOneHand);
            // Switch turns after drawing
            switchTurns();

             handleDrawButtonClick(mouseX, mouseY);
            handleCardClick(mouseX, mouseY);
       // cardGame.KnockButtonClick(mouseX, mouseY);
        } 
        //FIX THIS SECTIONS
    
   }



    public void youWin(PApplet app) {
        app.fill(200);
        app.rect(0,0,600,600);




    }

}
