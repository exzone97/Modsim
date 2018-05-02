/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modsim;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exzon
 */
 public class Antrian {

    private Queue<String> q;

    public Antrian() {
        this.q = new LinkedList<>();
    }

    public Queue<String> getQ() {
        return q;
    }

    public void setQ(Queue<String> q) {
        this.q = q;
    }

    public void addToQ(String s){
        this.q.add(s);
    }
    public String pollFromQ(){
        return this.q.poll();
    }
    public boolean isEmpty(){
        return q.isEmpty();
    }
}
