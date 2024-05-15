package game;
import packCard.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
class GameController {
    private ArrayList<Player> PlayerList;
    private Discard discard;
    private Deck deck;


    public GameController(){
        this.deck = new Deck(1);
        this.discard = new Discard();
    }


    public void startGame(){ // method ran at the start of the game, its role is to initialise all attributes, do some verification on these
        //but also to initialise the GUIs and ask the players for the first two cards they need to reveal
        // at its end, the game can really start, thus gameLoop being called.
        this.deck.shuffleDeck();
        this.discard.setFirst(this.deck.stripFirst());
        this.discard.getFirst().setState(true);
        Scanner scan = new Scanner(System.in);
        short playercount;
        System.out.println("Enter the number of players :");
        playercount = scan.nextShort();
        if(deck.getSize() < playercount*12 + 2 ){
            System.out.println("Too many players, not enough cards in deck !");
            System.exit(0);
        }
        this.initPlayerList(playercount);
        Vector temp = new Vector(-1,0);
        Vector temp2 = new Vector(-1,0);
        this.UpdateAllPlayerGUIs();
        for (Player player : PlayerList){
            temp.setX(-1);
            temp2.setX(-1);
            player.UpdatePlayerGUI(discard.getFirst());
            player.setTask("Select the first 2 cards to reveal");
            while(temp == null || temp.getX() < 0){ // this kind of while loop will be repeated a couple times in the program,
                //they are used to wait for the player's decision, which means him clicking on the gui
                temp = player.getDecision();
                System.out.print(""); // This right here is a headache of a problem, when we remove this print,
                // the program doesn't work anymore, it may be due to some synchronizing problem, but it is the only way
                // we have found to make it work...

            }
            player.getBoard().setCardStateAtCoord(temp,true);
            player.UpdatePlayerGUI(this.discard.getFirst());
            //this.player.UpdateTask("Selectionnez Votre deuxieme case a réveler");

            player.resetDecision();

            while(temp2 == null || temp2.getX() < 0 || temp2.compare(temp)){
                temp2 = player.getDecision();
                System.out.print("");
            }

            player.setScore(player.getBoard().getByCoor(temp).getVal() + player.getBoard().getByCoor(temp2).getVal());
            player.getBoard().setCardStateAtCoord(temp2,true);
            player.setTask("Wait for other players turn");
            player.UpdatePlayerGUI(this.discard.getFirst());

        }



        gameLoop();

    }


    public void gameLoop(){ // Main method of the entire project, it is the structure of our code. How it works is very simple,
        // we have a while loop, running until someone has finished the game,inside this while loop, which may represent
        // a turn (not one player's turn but all players turn) is a foreach loop, which goes iteratively over every player in the playerlist
        // and makes them do something, there are controls at the start and
        this.sortPlayerListByScore();
        boolean gameFinished=false;
        int playerHasFinished = -1;
        while(gameFinished == false){

            for(Player player : this.PlayerList){
                if(deck.isEmpty()){
                    deck.fillWith(discard);
                }
                if(PlayerList.indexOf(player)==playerHasFinished){
                    gameFinished = true;
                    break;
                }
                player.UpdatePlayerGUI(this.discard.getFirst());
                player.setTask("← Deck     Choose      Discard →");
                while(player.getDecision() == null || (player.getDecision().getX() != -2 && player.getDecision().getX() != -3)){
                    System.out.print("");
                }
                if(player.getDecision().getX() == -2){
                    player.setHand(this.deck.stripFirst());
                    player.setThrowVisible(true);
                }else if(player.getDecision().getX() == -3){
                    player.setHand(this.discard.stripFirst());
                }
                player.setTask("Where do you want to place your HAND ?");
                player.UpdatePlayerGUI(this.discard.getFirst());
                while(player.getDecision() == null || (player.getDecision().getX() < 0 && player.getDecision().getX() != -1)){
                    System.out.print("");
                }
                if(player.getDecision().getX() == -1){
                    this.discard.setFirst(player.getHand());
                    player.emptyHand();
                    player.UpdatePlayerGUI(this.discard.getFirst());
                    player.setTask("HAND has been thrown, reveal one of your cards");

                    while(player.getDecision() == null || player.getDecision().getX() < 0 || player.getBoard().getByCoor(player.getDecision()).getState()){
                        System.out.print("");
                    }
                    player.getBoard().setCardStateAtCoord(player.getDecision(),true);
                }else if (player.getDecision().getX() >= 0){
                    this.discard.setFirst(player.getBoard().getByCoor(player.getDecision()));
                    player.getBoard().setByCoor(player.getDecision(),player.getHand());
                    player.emptyHand();

                }
                player.setThrowVisible(false);
                player.setTask("Wait for other players turn");
                player.deleteSameRows(this.discard);
                this.UpdateAllPlayerGUIs();
                player.resetDecision();

                if(player.getBoard().allReveal() && playerHasFinished == -1){ // this checks if no one has finished yet
                    // and if you have finished, and if yes, then it starts another turn, until you are reached, at which point it truly
                    // ends the game.
                    playerHasFinished = PlayerList.indexOf(player);
                }


            }
        }
        for (Player player : PlayerList){ // This loop is in charge of revealing all cards at the end of the game, so that if
            // someone else finishes, you still get to see the cards you hadn't revealed yet.
            player.revealBoard();
            player.UpdatePlayerGUI(this.discard.getFirst());
            player.setTask("Your score : " + player.getTotalScore());
        }



    }
    public void initPlayerList(short playercount){
        this.PlayerList = new ArrayList<Player>();
        for(int i = 0; i < playercount;i++){
            this.PlayerList.add(new Player(this.deck,this.discard.getFirst()));
        }
    }

    public void UpdateAllPlayerGUIs(){
        for(Player player : PlayerList){
            player.UpdatePlayerGUI(this.discard.getFirst());
        }
    }

    public void sortPlayerListByScore(){
        Collections.sort(PlayerList, new ScoreComparator());
    }


}

