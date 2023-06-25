/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import com.mycompany.deepfaked.database.dao.DeepfakeDao;
import com.mycompany.deepfaked.database.dao.ProgressDeepfakeDao;
import com.mycompany.deepfaked.database.dao.QuestionDao;
import com.mycompany.deepfaked.database.dao.TaskDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ZENODotus
 */
public class DeepfakeCollectionImpl implements DeepfakeCollection {
    
    private List<Deepfake> deepfakes;
    private int progress;
    private int progressMission;
    private int progressDeepfake;
        
    public DeepfakeCollectionImpl(Mission mission, Gamer gamer) {
        deepfakes = DeepfakeDao.getDeepfakesForMission(mission);
        
        List<Deepfake> newList = new ArrayList<>();
        List<Deepfake> completedDeepfakes = ProgressDeepfakeDao.getCompletedDeepfakesforGamer(gamer);
        for(Deepfake deep : deepfakes) {
            boolean istaken = false;
            for(Deepfake completed : completedDeepfakes) {
                if(deep.getId() == completed.getId()) {
                    progress += TaskDao.getTasksForDeepfake(deep).size();
                    progress += QuestionDao.getQuestionsForDeepfake(deep).size();
                    istaken = true;
                }
                
            }
            progressMission += TaskDao.getTasksForDeepfake(deep).size();
            progressMission += QuestionDao.getQuestionsForDeepfake(deep).size();
            if(!istaken) {
                newList.add(deep);
            }
            
        }
        deepfakes.clear();
        deepfakes.addAll(newList);
    
    }

    @Override
    public void remove(Object deepfake) {
        if(deepfake instanceof Deepfake deepfake1) {
            deepfakes.remove(deepfake1);
        }
    }

    @Override
    public Deepfake get() {
        Deepfake deepfake = null;
            if(!deepfakes.isEmpty()) {
                int min = 0;
                int max = deepfakes.size();
                int deepfakeNumber = (int) (Math.random() * (max - min) + min);
                deepfake = deepfakes.get(deepfakeNumber);
                progressDeepfake = TaskDao.getTasksForDeepfake(deepfake).size();
                progressDeepfake += QuestionDao.getQuestionsForDeepfake(deepfake).size();
        
                deepfakes.remove(deepfake);
                
            }
            return deepfake;
        
    }

    @Override
    public Iterator iterator() {
        return new DeepfakeIteratorImpl(deepfakes);
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public int getProgressMission() {
        return progressMission;
    }
    
    @Override
    public int getProgressDeepfake() {
        return progressDeepfake;
    }

    @Override
    public boolean isEmpty() {
        return deepfakes.isEmpty();
    }
    
     private class DeepfakeIteratorImpl implements Iterator {
        
        private List<Deepfake> deepfakes;
        private int position;
        
        public DeepfakeIteratorImpl(List<Deepfake> deepfakes) {
            this.deepfakes = deepfakes;
        }

        @Override
        public boolean hasNext() {
            return position < deepfakes.size();
        }

        @Override
        public Deepfake next() {
            Deepfake deepfake = deepfakes.get(position);
            position++;
            return deepfake;
        }

        @Override
        public int size() {
            return deepfakes.size();
        }
        
    }
}
