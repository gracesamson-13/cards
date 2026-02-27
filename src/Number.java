public class Number extends CardType {

public Number() { 
    super("NumberType"); 
} 

@Override 
public String getDescription() { 
    return "A card with a numeric value (2–10)."; 
}

}
