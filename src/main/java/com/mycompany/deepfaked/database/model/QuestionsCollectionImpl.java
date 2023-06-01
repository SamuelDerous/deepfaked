/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import com.mycompany.deepfaked.database.dao.ChoiceDao;
import com.mycompany.deepfaked.database.dao.QuestionDao;
import java.util.List;

/**
 *
 * @author ZENODotus
 */
public class QuestionsCollectionImpl implements QuestionCollection {
    
    private List<Question> questions;
    private List<QuestionChoice> choices;
    
    public QuestionsCollectionImpl(Deepfake deepfake) {
        questions = QuestionDao.getQuestionsForDeepfake(deepfake);
    }

    @Override
    public void remove(Object question) {
        if(question instanceof Question questionImp) {
            questions.remove(questionImp);
        }
    }
    
    @Override
    public Question get() {
        Question question = null;
            if(!questions.isEmpty()) {
                int min = 0;
                int max = questions.size();
                int questionNumber = (int) (Math.random() * (max - min) + min);
                question = questions.get(questionNumber); //questionNumber);
                remove(question); //questionNumber);
                //System.out.println(question.getQuestion());
                choices = ChoiceDao.getChoicesForQuestion(question);
                /*for(int i = 0; i < choices.size(); i++) {
                    System.out.println(choices.get(i).getChoice().getAnswer());
                }*/
            }
            /*if(question.getChoices() != null) {
                System.out.println(question.getChoices());
            }*/
            return question;
    }
    
    @Override
    public List<QuestionChoice> getChoices() {
        return choices;
    }

    @Override
    public Iterator iterator() {
        return new QuestionIteratorImpl(questions);
    }

    @Override
    public boolean isEmpty() {
        return questions.isEmpty();
    }

    
    
    private class QuestionIteratorImpl implements Iterator {
        
        private List<Question> questions;
        private int position;
        
        public QuestionIteratorImpl(List<Question> questions) {
            this.questions = questions;
        }

        @Override
        public boolean hasNext() {
            return position < questions.size();
        }

        @Override
        public Question next() {
            Question question = questions.get(position);
            position++;
            return question;
        }

        @Override
        public int size() {
            return questions.size();
        }
        
    }
    
    
}
