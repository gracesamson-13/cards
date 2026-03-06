import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;

public class CardGame {
    // Core game components
    ArrayList<Card> deck = new ArrayList<>();
    Hand playerOneHand;
    Hand playerTwoHand;
    ArrayList<Card> discardPile = new ArrayList<>();
    Card selectedCard;
    int selectedCardRaiseAmount = 15;

    // Game state
    boolean playerOneTurn = true;
    Card lastPlayedCard;
    boolean gameActive;

    // UI
    ClickableRectangle drawButton;
    int drawButtonX = 250;
    int drawButtonY = 400;
    int drawButtonWidth = 100;
    int drawButtonHeight = 35;

ClickableRectangle knockButton;
int knockButtonX = 100;
int knockButtonY = 400;
int knockButtonWidth = 100;
int knockButtonHeight = 35;

ClickableRectangle discardButton;
    int discardButtonX = 350;
    int discardButtonY = 400;
    int discardButtonWidth = 100;
    int discardButtonHeight = 35;


String gameMessage = "";


    public CardGame() {
        initializeGame();
        dealCards(9);
    }

    protected void initializeGame() {
        // Initialize draw button
        drawButton = new ClickableRectangle();
        drawButton.x = drawButtonX;
        drawButton.y = drawButtonY;
        drawButton.width = drawButtonWidth;
        drawButton.height = drawButtonHeight;

        knockButton = new ClickableRectangle();
knockButton.x = knockButtonX;
knockButton.y = knockButtonY;
knockButton.width = knockButtonWidth;
knockButton.height = knockButtonHeight;

        // Initialize knock button
    knockButton = new ClickableRectangle();
    knockButton.x = knockButtonX;
    knockButton.y = knockButtonY;
    knockButton.width = knockButtonWidth;
    knockButton.height = knockButtonHeight;


    //initialize discard
    discardButton = new ClickableRectangle();
    discardButton.x = discardButtonX;
    discardButton.y = discardButtonY;
    discardButton.width = discardButtonWidth;
    discardButton.height = discardButtonHeight;


        // Initialize decks and hands
        deck = new ArrayList<>();
        discardPile = new ArrayList<>();
        playerOneHand = new Hand();
        playerTwoHand = new Hand();
        gameActive = true;

        createDeck();
    }

    protected void createDeck() {
        // Create a standard deck of cards (for simplicity, using numbers and suits)
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A" };
        for (String suit : suits) {
            for (String value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }

    protected void dealCards(int numCards) {
        Collections.shuffle(deck);
        for (int i = 0; i < numCards; i++) {
            playerOneHand.addCard(deck.remove(0));
            Card card = deck.remove(0);
            card.setTurned(true);
            playerTwoHand.addCard(card);
        }

        // position cards
        playerOneHand.positionCards(50, 450, 80, 120, 20);
        playerTwoHand.positionCards(50, 50, 80, 120, 20);
    }

    protected boolean isValidPlay(Card card) {
        return true;
    }

    public void drawCard(Hand hand) {
        if (deck != null && !deck.isEmpty()) {
            hand.addCard(deck.remove(0));
        } else if (discardPile != null && discardPile.size() > 1) {
            // Reshuffle discard pile into deck if deck is empty
            lastPlayedCard = discardPile.remove(discardPile.size() - 1);
            deck.addAll(discardPile);
            discardPile.clear();
            discardPile.add(lastPlayedCard);
            Collections.shuffle(deck);

            if (!deck.isEmpty()) {
                hand.addCard(deck.remove(0));
            }
        }
    }

    public void handleDrawButtonClick(int mouseX, int mouseY) {
        if (drawButton.isClicked(mouseX, mouseY) && playerOneTurn) {
            drawCard(playerOneHand);
            // Switch turns after drawing
            switchTurns();
        } 
    }

    public boolean playCard(Card card, Hand hand) {
        // Check if card is valid to play
        if (!isValidPlay(card)) {
            System.out.println("Invalid play: " + card.value + " of " + card.suit);
            return false;
        }
        // Remove card from hand
        hand.removeCard(card);
        card.setTurned(false);
        // Add to discard pile
        discardPile.add(card);
        lastPlayedCard = card;
        // Switch turns
        switchTurns();
        return true;
    }

    public void switchTurns() {
        playerOneTurn = !playerOneTurn;
        playerOneHand.positionCards(50, 450, 80, 120, 20);
        playerTwoHand.positionCards(50, 50, 80, 120, 20);
    }

    public String getCurrentPlayer() {
        return playerOneTurn ? "YOU" : "COMPUTER";
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public int getDeckSize() {
        return deck != null ? deck.size() : 0;
    }

    public Hand getPlayerOneHand() {
        return playerOneHand;
    }

    public Hand getPlayerTwoHand() {
        return playerTwoHand;
    }

    public void handleComputerTurn() {
        drawCard(playerTwoHand);
        switchTurns();
    }

    public void handleCardClick(int mouseX, int mouseY) {
        if (!playerOneTurn) {
            return;
        }
        Card clickedCard = getClickedCard(mouseX, mouseY);
        if (clickedCard == null) {
            return;
        }
        // this is for the first time
        if (selectedCard == null) {
            selectedCard = clickedCard;
            selectedCard.setSelected(true, selectedCardRaiseAmount);
            return;
        }

        if (selectedCard == clickedCard) {
            System.out.println("playing card: " + selectedCard.value + " of " + selectedCard.suit);
            if (playCard(selectedCard, playerOneHand)) {
                selectedCard.setSelected(false, selectedCardRaiseAmount);
                selectedCard = null;
            }
            return;
        }
        // change selection
        selectedCard.setSelected(false, selectedCardRaiseAmount);
        selectedCard = clickedCard;
        selectedCard.setSelected(true, selectedCardRaiseAmount);
    }

    // return the card that is clicked!
    public Card getClickedCard(int mouseX, int mouseY) {
        for (int i = playerOneHand.getSize() - 1; i >= 0; i--) {
            Card card = playerOneHand.getCard(i);
            if (card != null && card.isClicked(mouseX, mouseY)) {
                return card;
            }
        }
        return null;
    }

    public void drawChoices(PApplet app) {
    drawButton.draw(app);
    }

    public boolean hasThreeOfAKind(Hand hand) {
    for (int i = 0; i < hand.getSize(); i++) {
        Card card1 = hand.getCard(i);
        int count = 1;
        for (int g = i + 1; g < hand.getSize(); g++) {
            Card card2 = hand.getCard(g);
            if (card1.value.equals(card2.value)) {
                count++;
            }
        }
        if (count >= 3) {
            return true;
        }
    }
    return false;
}

   public void handleKnockButtonClick(int mouseX, int mouseY) {
    if (knockButton.isClicked(mouseX, mouseY)) {
        boolean playerWin = hasThreeOfAKind(playerOneHand);
        boolean computerWin = hasThreeOfAKind(playerTwoHand);

        gameActive = false;
        if (playerWin) {
            gameMessage = "You win!";} 
        else if (computerWin) {
            gameMessage = "Computer wins!"; } 
        System.out.println(gameMessage); }
}

public void handleDiscardButtonClick(int mouseX, int mouseY) {
    if (discardButton.isClicked(mouseX, mouseY)) {
        if (playerOneHand.getSize() > 0) {
            int randomIndex = (int)(Math.random() * playerOneHand.getSize());
            Card removed = playerOneHand.getCard(randomIndex);
            playerOneHand.removeCard(removed);
            System.out.println("Discarded: " + removed.value);
        }
    }
}
public void restartGame() {
    deck.clear();
    discardPile.clear();
    playerOneHand = new Hand();
    playerTwoHand = new Hand();

    selectedCard = null;
    lastPlayedCard = null;
    playerOneTurn = true;
    gameMessage = "";

    createDeck();
    dealCards(9);
}


}



