/*
 * Controller
 */
package com.agenda.controller;

import com.agenda.entity.Subscriber;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elena Ionita
 */
public class CarteDeTelefon {
    private File currentFile = null;
    private List<Subscriber> listSubscribers = new ArrayList<>();
    private LoginHandler loginHandler = new LoginHandler();
    private List<Abonat> deleteList = new ArrayList<>();
    
    /**
 * Public method that evaluates if the registration key is valid.
 * @param password
 *          The String that is evaluated.
 * @return
 *          Returns <strong>true</strong> if the password is valid, otherwise returns false.
 * @throws NumberFormatException
 */
    public boolean register(String password) throws NumberFormatException{
        return loginHandler.register(password);
    }
    /**
 * Public method that evaluates if the user is registered.
 * @return 
 *      Returns <strong>true</strong> if the user is registered, otherwise returns false.
 */
    public boolean isRegistered(){
        return loginHandler.isRegistered();
    }
/**
 * Adds a new <tt>Subscriber</tt> to the <tt>List<Subscriber></tt>
 * @param  subscriber
 *         A instance of <tt>Subscriber</tt>
 * @throws  IdenticalMembersException
 *          If the given Subscriber exists in the List
 * 
 */
    public void addSubscriber(Subscriber subscriber) throws IdenticalMembersException, IllegalArgumentException{
        boolean allClear = true;
        for(Subscriber item: listSubscribers){
            if(item.getCnp().equals(subscriber.getCnp())){
                allClear = false;
                throw new IdenticalMembersException("The subscriber already exist! You can modify the registration.");
            }
        }
        if(allClear){
            listSubscribers.add(subscriber);
            historyList.add(new History(ActionTypes.INSERT, abonat));
        }
    }
}
/**
 * Undo the last <tt>Subscriber</tt> added to the <tt>List<Subscriber></tt>
 * @param  subscriber
 *         A instance of <tt>Subscriber</tt>
 * @throws  IdenticalMembersException
 *          If the given Subscriber exists in the List
 * @throws IllegalArgumentException
 * 
 */
    public void undoAddSubscriber(Subscriber subscriber) throws IdenticalMembersException, IllegalArgumentException{
        boolean allClear = true;
        for(Subscriber item: listSubscribers){
            if(item.getCnp().equals(abonat.getCnp())){
                allClear = false;
                throw new IdenticalMembersException("The subscriber already exist! You can modify the registration.");
            }
        }
        if(allClear){
            listSubscribers.add(subscriber);
        }
    }
/**
 * Delete a Subscriber from the Subscribers list.
 * @param subscriber
 *          A instance of <tt>Subscriber</tt>
 */
    public void removeSubscriber(Subscriber subscriber){
        deleteList.add(subscriber);
        listSubscribers.remove(subscriber);
        historyList.add(new History(ActionTypes.DELETED, subscriber));
    }
/**
 * Edit the Subscriber from the Subscriber list
 * @param oldSubscriber
 *          The instance of the <tt>Subscriber</tt> that is edited
 * @param newSubscriber
 *          The instance of the <tt>Subscriber</tt> with the new data.
 * @throws IdenticalMembersException 
 */
    public void editSubscriber( oldSubscriber, Subscriber newSubscriber) throws IdenticalMembersException{
        boolean allClear = true;
        for(Subscriber item: listSubscribers){
            if(item.getCnp().equals(newSubscriber.getCnp())){
                if(newSubscriber.getId() != item.getId()){
                    allClear = false;
                    throw new IdenticalMembersException("The subscriber already exist!");
                }
            }
        }
        if(allClear){
            listSubscribers.set(listSubscribers.indexOf(oldSubscriber), newSubscriber);
            historyList.add(new History(ActionTypes.EDITED, oldSubscriber));
        }
    }
/**
 * Undo the last Edited Subscriber from the list
 * @param oldSubscriber
 *              The instance of the <tt>Subscriber</tt> that is edited
 * @param newSubscriber
 *              The instance of the <tt>Subscriber</tt> with the new data.
 * @throws IdenticalMembersException 
 */
    public void undoEditSubscriber(Subscriber oldSubscriber, Subscriber newScubscriber) throws IdenticalMembersException{
        boolean allClear = true;
        for(Subscriber item: listSubscribers){
            if(item.getCnp().equals(newSubscriber.getCnp())){
                if(newSubscriber.getId() != oldSubscriber.getId()){
                    allClear = false;
                    throw new IdenticalMembersException("The subscriber already exist!");
                }
            }
        }
        if(allClear){
            listSubscribers.set(listSubscribers.indexOf(oldSubscriber), newSubscriber);
        }
    }
/**
 * Search in the Subscriber list for the content String
 * @param content
 *          The String that is searched for in the Subscriber list
 * @return 
 *          Return an Subscriber with the objects that contain the searched String
 */
    public List<Subscriber> search(String content){
        List<Subscriber> temp = new ArrayList<>();
        for(Subscriber subscriber: listSubscribers){
            if(Subscriber.getFirstName().toLowerCase().contains(content) || abonat.getLastName().toLowerCase().contains(content)
                    || abonat.getCnp().toLowerCase().contains(content) || abonat.getMobileNumber().getMobileNumber().toLowerCase().contains(content)){
                temp.add(subscriber);
            }
        }
        return temp;
    }
/**
 * Public method that imports data from the selected file.
 * @param file
 *          The data file.
 * @param append 
 *          If <strong>append</strong> is true, the imported data is added to the existing data in the Abonat list, otherwise it replaces all the data from the list
 */
    public void importAgenda(File file, boolean append){
        boolean allClear = true;
        historyList.clear();
        setCurrentFile(file);
        List<Subscriber> tempList = FileHandler.readFile(file);
        if(append){
            for(Subscriber subscriber: tempList){
                for(Subscriber item: listSubscribers){
                    if(item.getCnp().equals(subscriber.getCnp())){
                        allClear = false;
                    }
                }
                if(allClear){
                    this.listSubscribers.add(subscriber);
                }
                allClear = true;
            }
        }else {
            listSubscribers.clear();
            for(Subscriber ab: tempList){
                listSubscribers.add(ab);
            }
        }
    }
/**
 * Public method that exports the Subscriber list to the selected file.
 * @param saveFile 
 *          The file where the data is exported.
 */
    public void exportAgenda(File saveFile){
        setCurrentFile(saveFile);
        FileHandler.writeFile(saveFile, this.listSubscribers);
    }
/**
 * Public method that returns the Subscriber list.
 * @return 
 *      The Subscriber list.
 */
    public List<Subscriber> getListSubscribers() {
        return listSubscribers;
    }
/**
 * Public method that sets the Subscriber list.
 * @param listSubscribers
 *          The Subscriber list that replace the existent list.
 */
    public void setListSubscribers(List<Subscriber> listSubscribers) {
        this.listSubscribers = listSubscribers;
    }
/**
 * Public method that returns the currentFile.
 * @return 
 *       Returns the file that is currently in use.
 */
    public File getCurrentFile() {
        return currentFile;
    }
/**
 * Public method the sets the file that is currently in use.
 * @param file 
 *          The File that will be in use.
 */
    public void setCurrentFile(File file) {
        this.currentFile = file;
    }
