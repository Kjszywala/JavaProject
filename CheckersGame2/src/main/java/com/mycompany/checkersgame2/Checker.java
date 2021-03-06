package com.mycompany.checkersgame2;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import javax.swing.JComponent;

/**

* @author kamil.szywala
* Class checker which is object of our pawn. Implementing Serializable
* interface from the reason to be able to send it through socket to the server.
*/
public class Checker implements Serializable {
    /**
     * Variables
     * positionX,positionY our position x and y of pawn when mouse is released.
     * x,y position of the checker when the mouse is dragged.
     * checkers - linked list of our pawns.
     * white - is pawn white?
     * isQueen - is pawn Queen?
     * opponentMove - is that opponent move?
     */
    protected int positionX;
    protected int positionY;
    protected int x;
    protected int y;
    LinkedList<Checker> checkers;
    protected boolean white = false;
    protected boolean isQueen = false;
    protected boolean opponentMove = false;
    NewGame dialog;
    /**
     * Constructor
     * @param positionX
     * @param positionY
     * @param white
     * @param checkers
     * @param isQueen 
     */
    public Checker(int positionX,
                    int positionY,
                    boolean white,
                    LinkedList<Checker> checkers,
                    boolean isQueen){
        this.positionX = positionX;
        this.positionY = positionY;
        x = positionX*100+5;
        y = positionY*100+5;
        this.white = white;
        this.checkers = checkers;
        this.isQueen = isQueen;
        checkers.add(this);
    }
    /**
    * This function include all moves that basic pawns and queen pawn can do at the moment.
    */
    public void Move(int positionX, int positionY){
        try{
            if(!NewGame.selectedChecker.white==false && NewGame.selectedChecker.positionY == 0){
                this.isQueen = true;
            }
            if(!NewGame.selectedChecker.white==true && NewGame.selectedChecker.positionY == 7){
                this.isQueen = true;
            }
            /**
             * pawns moves
             */
            if(NewGame.selectedChecker.isQueen==false){
                if((NewGame.selectedChecker.white==false)){ 
                    if((positionY-NewGame.selectedChecker.positionY)>=2 ||
                    (NewGame.selectedChecker.positionX-positionX)>=2    || 
                    (positionX-NewGame.selectedChecker.positionX)>=2    ||
                    (positionY<NewGame.selectedChecker.positionY)){
                        x = this.positionX*100+5;
                        y = this.positionY*100+5;
                        return;
                    }
                    if(NewGame.getChecker(positionX*100, positionY*100)!=null){
                        if(NewGame.getChecker(positionX*100, positionY*100).white==white){
                            x = this.positionX*100+5;
                            y = this.positionY*100+5;
                            return;
                        }
                        if(NewGame.getChecker(positionX*100, positionY*100).white!=white){
                            if(NewGame.selectedChecker.positionX<positionX){
                                if(this.positionY+2<8 && NewGame.getChecker((this.positionX+2)*100, (this.positionY+2)*100)==null){
                                    if(this.positionX+2<8){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX+=2;
                                        this.positionY+=2;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                }
                            }
                            if(NewGame.selectedChecker.positionX>positionX){
                                if(this.positionX-2>-1 && NewGame.getChecker((this.positionX-2)*100, (this.positionY+2)*100)==null){
                                    NewGame.getChecker(positionX*100, positionY*100).kill();
                                    this.positionX-=2;
                                    this.positionY+=2;
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }else{
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }
                            }else{
                                x = this.positionX*100+5;
                                y = this.positionY*100+5;
                                return;
                            }
                        }
                    }
                }
                if((NewGame.selectedChecker.white==true)){ 
                    if(((NewGame.selectedChecker.positionY-positionY)>=2   ||
                        ((NewGame.selectedChecker.positionX-positionX)>=2) || 
                        ((positionX-NewGame.selectedChecker.positionX)>=2) ||
                        (positionY>NewGame.selectedChecker.positionY))){
                            x = this.positionX*100+5;
                            y = this.positionY*100+5;
                            return;
                    }
                    if(NewGame.getChecker(positionX*100, positionY*100)!=null){
                        if(NewGame.getChecker(positionX*100, positionY*100).white==white){
                            x = this.positionX*100+5;
                            y = this.positionY*100+5;
                            return;
                        }
                        if(NewGame.getChecker(positionX*100, positionY*100).white!=white){
                            if(NewGame.selectedChecker.positionY-2>-1){
                                if(NewGame.selectedChecker.positionX<positionX){
                                    if(this.positionX+2<8 && NewGame.getChecker((this.positionX+2)*100, (this.positionY-2)*100)==null){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX+=2;
                                        this.positionY-=2;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                }
                                if(NewGame.selectedChecker.positionX>positionX){
                                    if(this.positionX-2>=0 && NewGame.getChecker((this.positionX-2)*100, (this.positionY-2)*100)==null){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX-=2;
                                        this.positionY-=2;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                }
                            }else{
                                x = this.positionX*100+5;
                                y = this.positionY*100+5;
                                return;    
                            }
                        }else{
                            x = this.positionX*100+5;
                            y = this.positionY*100+5;
                            return; 
                        }
                    }
                }
            }
            /**
             * Queen moves
             */
            if(NewGame.selectedChecker.isQueen==true){
                if((NewGame.selectedChecker.positionX <= positionX && NewGame.selectedChecker.positionY <= positionY) ||
                    (NewGame.selectedChecker.positionX >= positionX && NewGame.selectedChecker.positionY >= positionY)){
                     if((NewGame.selectedChecker.positionX-positionX) != (NewGame.selectedChecker.positionY-positionY)){
                         x = this.positionX*100+5;
                         y = this.positionY*100+5;
                         return;
                     } 
                }
                if(positionX < 0 || positionX > 7 || positionY < 0 || positionY > 7){
                    x = this.positionX*100+5;
                    y = this.positionY*100+5;
                    return;
                }
                if((NewGame.selectedChecker.positionX <= positionX && NewGame.selectedChecker.positionY >= positionY) ||
                    (NewGame.selectedChecker.positionX >= positionX && NewGame.selectedChecker.positionY <= positionY)){
                     if((NewGame.selectedChecker.positionX-positionX) != -(NewGame.selectedChecker.positionY-positionY)){
                         x = this.positionX*100+5;
                         y = this.positionY*100+5;
                         return;
                     } 
                }
                if(NewGame.getChecker(positionX*100, positionY*100)!=null){
                    if(NewGame.getChecker(positionX*100, positionY*100).white==white){
                        x = this.positionX*100+5;
                        y = this.positionY*100+5;
                        return;
                    }
                    if(NewGame.getChecker(positionX*100, positionY*100).white!=white){
                        if(NewGame.selectedChecker.positionY<positionY){
                            if(NewGame.selectedChecker.positionX<positionX){
                                if(positionY+1<8 && NewGame.getChecker((positionX+1)*100, (positionY+1)*100)==null){
                                    if(positionX+1<8){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX = positionX + 1;
                                        this.positionY = positionY + 1;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                } else {
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }
                            }
                        }
                        if(NewGame.selectedChecker.positionY>positionY){
                            if(NewGame.selectedChecker.positionX>positionX){
                                if(positionY-1>-1 &&  NewGame.getChecker((positionX-1)*100, (positionY-1)*100)==null){
                                    if(positionX-1>-1){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX = positionX - 1;
                                        this.positionY = positionY - 1;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                } else {
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }
                            }
                        }
                        if(NewGame.selectedChecker.positionY>positionY){
                            if(NewGame.selectedChecker.positionX<positionX){
                                if(positionY-1>-1 &&  NewGame.getChecker((positionX+1)*100, (positionY-1)*100)==null){
                                    if(positionX+1<8){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX = positionX + 1;
                                        this.positionY = positionY - 1;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                } else {
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }
                            }
                        }
                        if(NewGame.selectedChecker.positionY<positionY){
                            if(NewGame.selectedChecker.positionX>positionX){
                                if(positionY+1<8 &&  NewGame.getChecker((positionX-1)*100, (positionY+1)*100)==null){
                                    if(positionX-1>-1){
                                        NewGame.getChecker(positionX*100, positionY*100).kill();
                                        this.positionX = positionX - 1;
                                        this.positionY = positionY + 1;
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }else{
                                        x = this.positionX*100+5;
                                        y = this.positionY*100+5;
                                        return;
                                    }
                                } else {
                                    x = this.positionX*100+5;
                                    y = this.positionY*100+5;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            /**
             * If the position is white square go back to previous position.
             */
            if((positionX+positionY)%2==0){
                x = this.positionX*100+5;
                y = this.positionY*100+5;
                return;
            }
            this.positionX = positionX;
            this.positionY = positionY;
            x = positionX*100+5;
            y = positionY*100+5;
        }catch(ConcurrentModificationException e){
        }
    }
    /**
     * Removing the pawn.
     */
    public void kill(){
        checkers.remove(this);
    }
}
